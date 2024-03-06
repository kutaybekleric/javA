package com.aselmind.fsme.rest.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface CrudController<T, C, U> extends CrudOperations<T, C, U> {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Page<T> findAll(Pageable pageable);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    T create(@RequestBody C request);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    T read(@PathVariable String id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable String id, @RequestBody U request);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id);
}
