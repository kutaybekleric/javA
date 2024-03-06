package com.aselmind.fsme.rest.master.company;

import com.aselmind.fsme.rest.common.BaseRepository;

import java.util.Optional;

public interface CompanyRepository extends BaseRepository<CompanyEntity> {
    Optional<CompanyEntity> findByCode(String companyCode);
}
