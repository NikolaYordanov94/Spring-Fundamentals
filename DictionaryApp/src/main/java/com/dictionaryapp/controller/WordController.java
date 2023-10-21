package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.WordAddBindingModel;
import com.dictionaryapp.service.LoggedUser;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/words")
public class WordController {

    private final WordService wordService;
    private final LoggedUser loggedUser;

    public WordController(WordService wordService, LoggedUser loggedUser) {
        this.wordService = wordService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/add")
    public ModelAndView addWord(@ModelAttribute("wordAddBindingModel") WordAddBindingModel wordAddBindingModel){
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("word-add");
    }

    @PostMapping("/add")
    public ModelAndView addWord(@ModelAttribute("wordAddBindingModel") @Valid WordAddBindingModel wordAddBindingModel,
                                BindingResult bindingResult){

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("word-add");
        }

        wordService.add(wordAddBindingModel);

        return new ModelAndView("redirect:/home");
    }


}
