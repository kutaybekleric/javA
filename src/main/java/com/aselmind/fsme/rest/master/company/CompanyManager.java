package com.aselmind.fsme.rest.master.company;

import com.aselmind.fsme.rest.DatasourceProperties;
import com.aselmind.fsme.rest.common.CrudOperations;
import com.aselmind.fsme.rest.company.CompanyDbExecute;
import com.aselmind.fsme.rest.company.CompanyEntityManagerFactory;
import com.aselmind.fsme.rest.config.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyManager implements CrudOperations<CompanyEntity, CreateCompanyRequest, UpdateCompanyRequest> {
    private final CompanyRepository companyRepository;
    private final DatasourceProperties datasourceProperties;
    private final CompanyEntityManagerFactory companyEntityManagerFactory;
    private final CompanyDbExecute companyDbExecute;

    @Override
    public Page<CompanyEntity> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @Override
    public CompanyEntity create(CreateCompanyRequest request) {
        CompanyEntity companyEntity = companyRepository.save(map(request));
        initTables(companyEntity);
        return companyEntity;
    }

    public void initTables(CompanyEntity companyEntity) {
        LocalSessionFactoryBuilder builder = companyEntityManagerFactory.createLocalSessionFactoryBuilder();
        companyDbExecute.run(companyEntity, () -> builder.buildSessionFactory().openSession().close());
    }

    private CompanyEntity map(CreateCompanyRequest request) {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setTitle(request.getTitle());
        companyEntity.setCode(request.getCode());
        companyEntity.setDbName(datasourceProperties.getCompanyDataSourcePrefix() + request.getDbNameSuffix());
        return companyEntity;
    }

    @Override
    public CompanyEntity read(String id) {
        return companyRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void update(String id, UpdateCompanyRequest request) {
        companyRepository.save(map(read(id), request));
    }

    private CompanyEntity map(CompanyEntity entity, UpdateCompanyRequest request) {
        entity.setTitle(request.getTitle());
        return entity;
    }

    @Override
    public void delete(String id) {
        throw new NotImplementedException();
    }

    public Optional<CompanyEntity> findByCode(String companyCode) {
        return companyRepository.findByCode(companyCode);
    }
}
