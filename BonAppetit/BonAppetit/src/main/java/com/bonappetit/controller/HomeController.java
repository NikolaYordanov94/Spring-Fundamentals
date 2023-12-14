package com.bonappetit.controller;

import com.bonappetit.service.RecipeService;
import com.bonappetit.service.impl.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final RecipeService recipeService;

    private final LoggedUser loggedUser;

    public HomeController(RecipeService recipeService, LoggedUser loggedUser) {
        this.recipeService = recipeService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/")
    public ModelAndView index(){
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home(){

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");

        modelAndView.addObject("desserts", recipeService.getDesserts());
        modelAndView.addObject("mainDishes", recipeService.getMainDishes());
        modelAndView.addObject("cocktails", recipeService.getCocktails());
        modelAndView.addObject("favourites", recipeService.getFavourites());

        return modelAndView;
    }




}
