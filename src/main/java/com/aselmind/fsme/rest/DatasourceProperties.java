package com.aselmind.fsme.rest;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Primary
@Configuration
@Getter
public class DatasourceProperties {

    @Value("${core.settings.db.username}")
    private String username;
    @Value("${core.settings.db.password}")
    private String password;
    @Value("${core.settings.db.master_schema}")
    private String masterDatabaseName;
    @Value("${core.settings.db.company_schema_prefix}")
    private String companyDataSourcePrefix;
    @Value("${core.settings.db.serverName}")
    private String dbUrl;
}