package com.aselmind.fsme.rest.company.user;

import com.aselmind.fsme.rest.common.CrudController;
import com.aselmind.fsme.rest.config.security.annotations.IsCompanyOwner;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@IsCompanyOwner
public class UserController implements CrudController<CompanyUserDto, CreateCompanyUserRequest, UpdateCompanyUserRequest> {

    private final CompanyUserManager companyUserManager;

    @Override
    public Page<CompanyUserDto> findAll(Pageable pageable) {
        return companyUserManager.findAll(pageable).map(CompanyUserDto::new);
    }

    @Override
    public CompanyUserDto create(@Valid CreateCompanyUserRequest request) {
        return null;
    }

    @Override
    public CompanyUserDto read(String id) {
        return null;
    }

    @Override
    public void update(String id, @Valid UpdateCompanyUserRequest request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException();
    }
}
