package com.bonappetit.controller;

import com.bonappetit.model.dto.recipe.RecipeAddBindingModel;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.impl.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    private final LoggedUser loggedUser;

    public RecipeController(RecipeService recipeService, LoggedUser loggedUser) {
        this.recipeService = recipeService;
        this.loggedUser = loggedUser;
    }


    @GetMapping("/add")
    public ModelAndView addRecipe(@ModelAttribute("recipeAddBindingModel") RecipeAddBindingModel recipeAddBindingModel){
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("recipe-add");
    }

    @PostMapping("/add")
    public ModelAndView addRecipe(@ModelAttribute("recipeAddBindingModel") @Valid RecipeAddBindingModel recipeAddBindingModel,
                                  BindingResult bindingResult){

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("recipe-add");
        }

        recipeService.add(recipeAddBindingModel);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/add-favourites/{id}")
    public ModelAndView addToFavourites(@PathVariable("id") Long id){
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        recipeService.addRecipeToFavourites(id);

        return new ModelAndView("redirect:/home");
    }

}
