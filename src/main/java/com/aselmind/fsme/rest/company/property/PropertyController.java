package com.aselmind.fsme.rest.company.property;

import com.aselmind.fsme.rest.common.CrudController;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/properties")
public class PropertyController implements CrudController<PropertyEntity, CreateProperyRequest, UpdatePropertyRequest> {

    private final PropertyManager propertyManager;

    @Override
    public Page<PropertyEntity> findAll(Pageable pageable) {
        return propertyManager.findAll(pageable);
    }

    @Override
    public PropertyEntity create(CreateProperyRequest request) {
        return propertyManager.create(request);
    }

    @Override
    public PropertyEntity read(String id) {
        return propertyManager.read(id);
    }

    @Override
    public void update(String id, UpdatePropertyRequest request) {
        propertyManager.update(id, request);
    }

    @Override
    public void delete(String id) {
        propertyManager.delete(id);
    }
}
