package com.aselmind.fsme.rest.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudOperations<T, C, U> {
    Page<T> findAll(Pageable pageable);

    T create(C request);

    T read(String id);

    void update(String id, U request);

    void delete(String id);
}
