package com.aselmind.fsme.rest.master.superuser;

import com.aselmind.fsme.rest.common.CrudOperations;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SuperUserManager implements CrudOperations<SuperUserEntity, CreateSuperUserRequest, UpdateSuperUserRequest> {
    private final SuperUserRepository superUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<SuperUserEntity> findAll(Pageable pageable) {
        return superUserRepository.findAll(pageable);
    }

    @Override
    public SuperUserEntity create(CreateSuperUserRequest request) {
        return superUserRepository.save(map(request));
    }

    private SuperUserEntity map(CreateSuperUserRequest request) {
        SuperUserEntity entity = new SuperUserEntity();
        entity.setUsername(request.getUsername());
        entity.setUserPassword(passwordEncoder.encode(request.getUserPassword()));
        return entity;
    }

    @Override
    public SuperUserEntity read(String id) {
        return null;
    }

    @Override
    public void update(String id, UpdateSuperUserRequest request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException();
    }
}
