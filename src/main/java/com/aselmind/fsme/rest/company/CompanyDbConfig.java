package com.aselmind.fsme.rest.company;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.aselmind.fsme.rest.company",
        entityManagerFactoryRef = "companyEntityManager",
        transactionManagerRef = "companyTransactionManager"
)
@AllArgsConstructor
public class CompanyDbConfig {
    private final CompanyEntityManagerFactory companyEntityManagerFactory;

    @Bean
    public LocalContainerEntityManagerFactoryBean companyEntityManager() {
        return companyEntityManagerFactory.createLocalContainerEntityManagerFactoryBean(false);
    }

    @Bean
    public PlatformTransactionManager companyTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                companyEntityManager().getObject());
        return transactionManager;
    }
}