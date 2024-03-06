package com.aselmind.fsme.rest.company.property;

import com.aselmind.fsme.rest.config.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PropertyManager {
    private final PropertyRepository propertyRepository;

    public Page<PropertyEntity> findAll(Pageable pageable) {
        return propertyRepository.findAll(pageable);
    }

    public PropertyEntity create(CreateProperyRequest request) {
        return propertyRepository.save(map(request));
    }

    private PropertyEntity map(CreateProperyRequest request) {
        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setId(request.getId());
        propertyEntity.setValue(request.getValue());
        return propertyEntity;
    }

    public PropertyEntity read(String id) {
        return propertyRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public void update(String id, UpdatePropertyRequest request) {
        propertyRepository.save(map(read(id), request));
    }

    private PropertyEntity map(PropertyEntity entity, UpdatePropertyRequest request) {
        entity.setValue(request.getValue());
        return entity;
    }

    public void delete(String id) {
        propertyRepository.delete(read(id));
    }
}
