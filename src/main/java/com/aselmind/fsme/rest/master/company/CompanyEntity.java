package com.aselmind.fsme.rest.master.company;

import com.aselmind.fsme.rest.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter(AccessLevel.PACKAGE)
public class CompanyEntity extends BaseEntity {
    @Column
    private String title;
    @Column
    private String code;
    @Column
    private String dbName;
}
