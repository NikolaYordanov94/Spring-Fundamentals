package com.bonappetit.service;

import com.bonappetit.model.dto.recipe.RecipeAddBindingModel;
import com.bonappetit.model.dto.recipe.RecipeViewDTO;
import com.bonappetit.model.entity.Recipe;

import java.util.List;

public interface RecipeService {

    void add(RecipeAddBindingModel recipeAddBindingModel);

    List<RecipeViewDTO> getDesserts();

    List<RecipeViewDTO> getMainDishes();

    List<RecipeViewDTO> getCocktails();

    List<RecipeViewDTO> getFavourites();

    void addRecipeToFavourites(Long recipeId);

}
