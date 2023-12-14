package com.bonappetit.service.impl;

import com.bonappetit.model.dto.recipe.RecipeAddBindingModel;
import com.bonappetit.model.dto.recipe.RecipeViewDTO;
import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.model.enums.CategoryName;
import com.bonappetit.repo.CategoryRepository;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final LoggedUser loggedUser;

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, LoggedUser loggedUser, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.loggedUser = loggedUser;
        this.recipeRepository = recipeRepository;
    }


    @Override
    public void add(RecipeAddBindingModel recipeAddBindingModel) {
        Category category = categoryRepository.findByCategoryName(recipeAddBindingModel.getCategoryName());
        User user = userRepository.findByUsername(loggedUser.getUsername());

        if(category != null && user != null){
            Recipe recipe = new Recipe();

            recipe.setName(recipeAddBindingModel.getName());
            recipe.setCategory(category);
            recipe.setIngredients(recipeAddBindingModel.getIngredients());
            recipe.setAddedBy(user);

            recipeRepository.save(recipe);
        }
    }

    @Override
    public List<RecipeViewDTO> getDesserts() {
        List<Recipe> recipesForDesserts = recipeRepository
                .findAllByCategory_CategoryName(CategoryName.DESSERT);

        List<RecipeViewDTO> recipeForDessertViewDTOS = new ArrayList<>();

        recipesForDesserts.forEach(recipe -> {
            RecipeViewDTO recipeViewDTO = new RecipeViewDTO();
            recipeViewDTO.setName(recipe.getName());
            recipeViewDTO.setIngredients(recipe.getIngredients());
            recipeViewDTO.setId(recipe.getId());

            recipeForDessertViewDTOS.add(recipeViewDTO);
        });

        return recipeForDessertViewDTOS;
    }

    @Override
    public List<RecipeViewDTO> getMainDishes() {
        List<Recipe> recipesForMainDishes = recipeRepository
                .findAllByCategory_CategoryName(CategoryName.MAIN_DISH);

        List<RecipeViewDTO> recipeForMainDisheViewDTOS = new ArrayList<>();

        recipesForMainDishes.forEach(recipe -> {
            RecipeViewDTO recipeViewDTO = new RecipeViewDTO();
            recipeViewDTO.setName(recipe.getName());
            recipeViewDTO.setIngredients(recipe.getIngredients());
            recipeViewDTO.setId(recipe.getId());

            recipeForMainDisheViewDTOS.add(recipeViewDTO);
        });

        return recipeForMainDisheViewDTOS;
    }

    @Override
    public List<RecipeViewDTO> getCocktails() {
        List<Recipe> recipesForCocktails = recipeRepository
                .findAllByCategory_CategoryName(CategoryName.COCKTAIL);

        List<RecipeViewDTO> recipeForCocktailViewDTOS = new ArrayList<>();

        recipesForCocktails.forEach(recipe -> {
            RecipeViewDTO recipeViewDTO = new RecipeViewDTO();
            recipeViewDTO.setName(recipe.getName());
            recipeViewDTO.setIngredients(recipe.getIngredients());
            recipeViewDTO.setId(recipe.getId());

            recipeForCocktailViewDTOS.add(recipeViewDTO);
        });

        return recipeForCocktailViewDTOS;
    }

    @Override
    public List<RecipeViewDTO> getFavourites() {
        User user = userRepository.findByUsername(loggedUser.getUsername());

        List<Recipe> favouritesCurrentUser = user.getFavouriteRecipes();
        List<RecipeViewDTO> favouriteRecipesCurrentUserViewDTOS = new ArrayList<>();

        favouritesCurrentUser.forEach(recipe -> {
            RecipeViewDTO recipeViewDTO = new RecipeViewDTO();
            recipeViewDTO.setName(recipe.getName());
            recipeViewDTO.setIngredients(recipe.getIngredients());
            recipeViewDTO.setId(recipe.getId());

            favouriteRecipesCurrentUserViewDTOS.add(recipeViewDTO);
        });

        return favouriteRecipesCurrentUserViewDTOS;
    }

    @Override
    public void addRecipeToFavourites(Long recipeId) {
        User user = userRepository.findByUsername(loggedUser.getUsername());

        recipeRepository.findById(recipeId).ifPresent(recipe -> {

            if (!user.getFavouriteRecipes().contains(recipe)) {
                user.getFavouriteRecipes().add(recipe);
                userRepository.save(user);
            }
        });
    }
}
