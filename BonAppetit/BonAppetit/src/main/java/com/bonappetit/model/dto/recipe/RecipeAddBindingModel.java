package com.bonappetit.model.dto.recipe;

import com.bonappetit.model.enums.CategoryName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RecipeAddBindingModel {

    @Size(min = 2, max = 40, message = "Name length must be between 2 and 40 characters!")
    private String name;

    @Size(min = 2, max = 80, message = "Ingredients length must be between 2 and 80 characters!")
    private String ingredients;

    @NotNull(message = "You must select a category!")
    private CategoryName categoryName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }
}
