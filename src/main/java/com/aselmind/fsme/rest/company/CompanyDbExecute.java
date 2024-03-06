package com.aselmind.fsme.rest.company;

import com.aselmind.fsme.rest.master.company.CompanyEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Supplier;

@Component
public class CompanyDbExecute {
    private final ThreadLocal<CompanyEntity> companyEntity = new ThreadLocal<>();

    public void run(CompanyEntity company, @NotNull Runnable runnable) {
        try {
            companyEntity.set(company);
            runnable.run();
        } finally {
            companyEntity.remove();
        }
    }

    public <T> T run(CompanyEntity company, @NotNull Supplier<T> supplier) {
        try {
            companyEntity.set(company);
            return supplier.get();
        } finally {
            companyEntity.remove();
        }
    }

    public Optional<CompanyEntity> getCompanyEntity() {
        return Optional.ofNullable(companyEntity.get());
    }
}
