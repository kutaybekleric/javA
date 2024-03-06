package com.aselmind.fsme.rest.master;

import com.aselmind.fsme.rest.DatasourceProperties;
import com.aselmind.fsme.rest.JpaProperties;
import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.aselmind.fsme.rest.master",
        entityManagerFactoryRef = "masterEntityManager",
        transactionManagerRef = "masterTransactionManager"
)
@RequiredArgsConstructor
public class MasterDbConfig {

    private final DatasourceProperties dbSettings;

    @Bean
    public LocalContainerEntityManagerFactoryBean masterEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(masterDataSource());
        em.setPackagesToScan(
                "com.aselmind.fsme.rest.master");

        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(JpaProperties.get(true));
        em.afterPropertiesSet();
        return em;
    }

    @Bean("masterDataSource")
    public DataSource masterDataSource() {
        final String url = dbSettings.getDbUrl() + dbSettings.getMasterDatabaseName() + "?createDatabaseIfNotExist=true";
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(url);
        dataSource.setUser(dbSettings.getUsername());
        dataSource.setPassword(dbSettings.getPassword());
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager masterTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                masterEntityManager().getObject());
        return transactionManager;
    }
}