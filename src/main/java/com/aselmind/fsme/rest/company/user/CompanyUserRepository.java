package com.aselmind.fsme.rest.company.user;

import com.aselmind.fsme.rest.common.BaseRepository;

import java.util.Optional;

interface CompanyUserRepository extends BaseRepository<CompanyUserEntity> {
    Optional<CompanyUserEntity> findByUsername(String username);
}
