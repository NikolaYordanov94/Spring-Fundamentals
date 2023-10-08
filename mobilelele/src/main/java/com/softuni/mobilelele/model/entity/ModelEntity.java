package com.softuni.mobilelele.model.entity;

import com.softuni.mobilelele.model.enums.ModelCategoryEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity{

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private ModelCategoryEnum category;

    @ManyToOne
    private BrandEntity brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModelCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(ModelCategoryEnum category) {
        this.category = category;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public ModelEntity() {
    }
}
