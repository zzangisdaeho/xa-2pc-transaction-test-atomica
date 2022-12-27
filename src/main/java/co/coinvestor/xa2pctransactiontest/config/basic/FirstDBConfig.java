package co.coinvestor.xa2pctransactiontest.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import javax.sql.XADataSource;
import java.util.HashMap;
import java.util.Properties;

@Profile("no")
@Configuration
@EnableJpaRepositories(
        basePackages = "co.coinvestor.xa2pctransactiontest.firstDB",
        entityManagerFactoryRef = "firstDBEntityManager",
        transactionManagerRef = "firstDBTransactionManager"
)
@RequiredArgsConstructor
public class FirstDBConfig {

    private final Environment env;

    @Bean
    public DataSource firstDBDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("datasource.first.url"));
        dataSource.setUsername(env.getProperty("datasource.first.username"));
        dataSource.setPassword(env.getProperty("datasource.first.password"));

        return dataSource;
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
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager firstDBTransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(firstDBEntityManager().getObject());
        return transactionManager;
    }

}
