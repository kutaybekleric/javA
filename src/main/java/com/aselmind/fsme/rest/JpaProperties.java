package com.aselmind.fsme.rest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;

import java.util.HashMap;
import java.util.Map;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JpaProperties {

    public static Map<String, Object> get() {
        return get(false);
    }

    public static Map<String, Object> get(boolean update) {
        Map<String, Object> properties = new HashMap<>();
        if (update) {
            properties.put("hibernate.hbm2ddl.auto", "update");
        }

        properties.put("hibernate.show_sql", "true");
        properties.put("logging.level.org.hibernate.SQL", "warning");

        properties.put("hibernate.naming.physical-strategy", PhysicalNamingStrategyStandardImpl.class.getName());
        properties.put("hibernate.naming.implicit-strategy", ImplicitNamingStrategyComponentPathImpl.class.getName());

        properties.put("hibernate.physical_naming_strategy", PhysicalNamingStrategyStandardImpl.class.getName());
        properties.put("hibernate.implicit_naming_strategy", ImplicitNamingStrategyComponentPathImpl.class.getName());

        properties.put("spring.jpa.hibernate.naming.physical-strategy", PhysicalNamingStrategyStandardImpl.class.getName());
        properties.put("spring.jpa.hibernate.naming.implicit-strategy", ImplicitNamingStrategyComponentPathImpl.class.getName());

        return properties;
    }
}