package com.aselmind.fsme.rest.company;

import com.aselmind.fsme.rest.JpaProperties;
import lombok.AllArgsConstructor;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@AllArgsConstructor
public class CompanyEntityManagerFactory {
    private final CompanyDatasourceAdapter companyDatasourceAdapter;

    public LocalContainerEntityManagerFactoryBean createLocalContainerEntityManagerFactoryBean(boolean createTables) {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(companyDatasourceAdapter);
        em.setPackagesToScan(
                "com.aselmind.fsme.rest.company");

        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(JpaProperties.get(createTables));
        return em;
    }

    public LocalSessionFactoryBuilder createLocalSessionFactoryBuilder() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(companyDatasourceAdapter);
        builder.scanPackages(
                "com.aselmind.fsme.rest.company");

        Properties properties = new Properties();
        properties.putAll(JpaProperties.get(true));
        builder.addProperties(properties);
        return builder;
    }
}
