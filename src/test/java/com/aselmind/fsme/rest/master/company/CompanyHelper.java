package com.aselmind.fsme.rest.master.company;

import com.aselmind.fsme.rest.master.company.CompanyEntity;
import com.aselmind.fsme.rest.master.company.CompanyManager;
import com.aselmind.fsme.rest.master.company.CreateCompanyRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CompanyHelper {
    private final CompanyManager companyManager;

    public CompanyEntity getOrCreateTestCompany() {
        final String testCompany = "testCompany";
        return companyManager.findByCode(testCompany).orElseGet(() ->
                companyManager.create(CreateCompanyRequest.builder()
                        .title(testCompany)
                        .code(testCompany)
                        .dbNameSuffix(testCompany)
                        .build()));
    }

    public CompanyEntity createRandomCompany() {
        final String ts = String.valueOf(System.currentTimeMillis());
        final String testCompany = "testCompany_" + ts;
        return companyManager.create(CreateCompanyRequest.builder()
                .title(testCompany)
                .code(testCompany)
                .dbNameSuffix(testCompany)
                .build());
    }
}
