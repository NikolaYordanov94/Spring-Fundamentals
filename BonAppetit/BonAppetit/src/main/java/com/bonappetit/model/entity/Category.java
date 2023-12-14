package com.bonappetit.model.entity;

import com.bonappetit.model.enums.CategoryName;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private CategoryName categoryName;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Recipe> recipes;

    public Category() {
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
        setDescription(categoryName);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(CategoryName categoryName) {
        String description = "";

        switch (categoryName){
            case MAIN_DISH:
                description = "Heart of the meal, substantial and satisfying; main dish delights taste buds.";
                break;
            case DESSERT:
                description = "Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy.";
                break;
            case COCKTAIL:
                description = "Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass.";
                break;
        }

        this.description = description;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
