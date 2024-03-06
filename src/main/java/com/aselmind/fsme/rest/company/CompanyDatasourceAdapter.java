package com.aselmind.fsme.rest.company;

import com.aselmind.fsme.rest.DatasourceProperties;
import com.aselmind.fsme.rest.master.company.CompanyEntity;
import com.aselmind.fsme.rest.utils.RequestUtils;
import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.datasource.DelegatingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CompanyDatasourceAdapter extends DelegatingDataSource {
    private final DatasourceProperties dbSettings;
    private final CompanyDbExecute companyDbExecute;


    @Override
    public DataSource getTargetDataSource() {
        Optional<String> dbNameFromRequest = RequestUtils.getCompany().map(CompanyEntity::getDbName);
        Optional<String> dbNameFromDbExecute = companyDbExecute.getCompanyEntity().map(CompanyEntity::getDbName);
        String dbName = dbNameFromRequest.orElse(
                dbNameFromDbExecute.orElse(
                        dbSettings.getCompanyDataSourcePrefix() + "default")
        );
        String url = dbSettings.getDbUrl() + dbName + "?createDatabaseIfNotExist=true";
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(url);
        dataSource.setUser(dbSettings.getUsername());
        dataSource.setPassword(dbSettings.getPassword());
        return dataSource;
    }
}
