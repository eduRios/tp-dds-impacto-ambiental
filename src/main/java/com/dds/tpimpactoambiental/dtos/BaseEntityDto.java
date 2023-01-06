package com.dds.tpimpactoambiental.dtos;

import com.dds.tpimpactoambiental.model.BaseEntity;

public abstract class BaseEntityDto {
    protected Long id;

    public BaseEntityDto(BaseEntity entity) {
        this.id = entity.getId();
    }

    public BaseEntityDto() {
    }

    public Long getId() {
        return id;
    }
}
