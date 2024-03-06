package com.aselmind.fsme.rest.master.company;

import com.aselmind.fsme.rest.common.CrudController;
import com.aselmind.fsme.rest.config.security.annotations.IsSuperUser;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/companies")
@IsSuperUser
public class CompanyController implements CrudController<CompanyEntity, CreateCompanyRequest, UpdateCompanyRequest> {
    private final CompanyManager companyManager;

    @Override
    public Page<CompanyEntity> findAll(Pageable pageable) {
        return companyManager.findAll(pageable);
    }

    @Override
    public CompanyEntity create(@Valid CreateCompanyRequest request) {
        return companyManager.create(request);
    }

    @Override
    public CompanyEntity read(String id) {
        return companyManager.read(id);
    }

    @Override
    public void update(String id, @Valid UpdateCompanyRequest request) {
        companyManager.update(id, request);
    }

    @Override
    public void delete(String id) {
        throw new NotImplementedException();
    }
}
