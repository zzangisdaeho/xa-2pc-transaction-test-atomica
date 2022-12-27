package co.coinvestor.xa2pctransactiontest.config;

import lombok.RequiredArgsConstructor;
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
import java.util.HashMap;

@Profile("no")
@Configuration
@EnableJpaRepositories(
        basePackages = "co.coinvestor.xa2pctransactiontest.secondDB",
        entityManagerFactoryRef = "secondDBEntityManager",
        transactionManagerRef = "secondDBTransactionManager"
)
@RequiredArgsConstructor
public class SecondDBConfig {

    private final Environment env;

    @Bean
    public DataSource secondDBDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("datasource.second.url"));
        dataSource.setUsername(env.getProperty("datasource.second.username"));
        dataSource.setPassword(env.getProperty("datasource.second.password"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean secondDBEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(secondDBDataSource());
        em.setPackagesToScan("co.coinvestor.xa2pctransactiontest.secondDB.entity");

        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.format_sql", "true");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager secondDBTransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(secondDBEntityManager().getObject());
        return transactionManager;
    }
}
