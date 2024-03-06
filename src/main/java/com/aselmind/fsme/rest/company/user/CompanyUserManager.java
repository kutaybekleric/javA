package com.aselmind.fsme.rest.company.user;

import com.aselmind.fsme.rest.common.CrudOperations;
import com.aselmind.fsme.rest.config.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyUserManager implements CrudOperations<CompanyUserEntity, CreateCompanyUserRequest, UpdateCompanyUserRequest> {
    private final CompanyUserRepository companyUserRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Page<CompanyUserEntity> findAll(Pageable pageable) {
        return companyUserRepository.findAll(pageable);
    }

    @Override
    public CompanyUserEntity create(CreateCompanyUserRequest request) {
        return companyUserRepository.save(map(request));
    }

    private CompanyUserEntity map(CreateCompanyUserRequest request) {
        CompanyUserEntity entity = new CompanyUserEntity();
        entity.setUsername(request.getUsername());
        entity.setUserPassword(passwordEncoder.encode(request.getUserPassword()));
        entity.setSecurityRole(request.getSecurityRole());
        return entity;
    }

    @Override
    public CompanyUserEntity read(String id) {
        return companyUserRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void update(String id, UpdateCompanyUserRequest request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException();
    }
}
