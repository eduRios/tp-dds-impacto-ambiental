package com.dds.tpimpactoambiental.dtos;


import com.dds.tpimpactoambiental.model.BaseEntity;

public class IdTextPair {
    private long id;
    private String text;

    public IdTextPair(long id, String text) {
        this.id = id;
        this.text = text;
    }
    public IdTextPair(BaseEntity entity) {
        this(entity.getId(), entity.toString());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
