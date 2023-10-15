package com.resellerapp.model.entity;

import com.resellerapp.model.enums.ConditionName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ConditionName name;

    @NotNull
    private String description;

    public Condition() {
    }

    public ConditionName getName() {
        return name;
    }

    public void setName(ConditionName name) {
        this.name = name;
        setDescription(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(ConditionName name) {
        String description = "";

        switch (name){
            case GOOD:
                description = "Some signs of wear and tear or minor defects";
                break;
            case ACCEPTABLE:
                description = "The item is fairly worn but continues to function properly";
                break;
            case EXCELLENT:
                description = "In perfect condition";
                break;
        }
        this.description = description;
    }
}
