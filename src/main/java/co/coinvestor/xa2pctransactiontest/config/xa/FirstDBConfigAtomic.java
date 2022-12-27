package co.coinvestor.xa2pctransactiontest.config.xa;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;

@Profile("at")
@Configuration
@EnableJpaRepositories(
        basePackages = "co.coinvestor.xa2pctransactiontest.firstDB",
        entityManagerFactoryRef = "firstDBEntityManager",
        transactionManagerRef = "globalTransactionManager"
)
@RequiredArgsConstructor
public class FirstDBConfigAtomic {

    private final Environment env;

    @Bean
    public DataSource firstDBDataSource() {

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();

        atomikosDataSourceBean.setUniqueResourceName("firstDataSource");
        atomikosDataSourceBean.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");

        Properties properties = new Properties();
        properties.setProperty("url", env.getProperty("datasource.first.url"));
        properties.setProperty("user", env.getProperty("datasource.first.username"));
        properties.setProperty("password", env.getProperty("datasource.first.password"));

        atomikosDataSourceBean.setXaProperties(properties);

        return atomikosDataSourceBean;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean firstDBEntityManager() {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(firstDBDataSource());
        em.setPackagesToScan("co.coinvestor.xa2pctransactiontest.firstDB.entity");

        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.format_sql", "true");

//        properties.put("hibernate.current_session_context_class", "jta");
        properties.put("javax.persistence.transactionType", "jta");
//        properties.put("hibernate.transaction.manager_lookup_class", "com.atomikos.icatch.jta.hibernate3.TransactionManagerLookup");

        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();

        return em;
    }

}
