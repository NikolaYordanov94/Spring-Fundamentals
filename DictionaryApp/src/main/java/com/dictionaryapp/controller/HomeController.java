package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.HomeWordsDTO;
import com.dictionaryapp.model.dto.WordDTO;
import com.dictionaryapp.service.LoggedUser;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final WordService wordService;

    public HomeController(LoggedUser loggedUser, WordService wordService) {
        this.loggedUser = loggedUser;
        this.wordService = wordService;
    }

    @GetMapping("/")
    public ModelAndView index(){

        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        return new ModelAndView("index");
    }

    @GetMapping("/home")
        public String home(Model model){

        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        HomeWordsDTO wordsHome = wordService.getHomeViewData();

        List<WordDTO> germanWords = new ArrayList<>();
        List<WordDTO> frenchWords = new ArrayList<>();
        List<WordDTO> spanishWords = new ArrayList<>();
        List<WordDTO> italianWords = new ArrayList<>();

        wordsHome.getWords().forEach(wordDTO -> {
            switch (wordDTO.getLanguage()) {
                case GERMAN:
                    germanWords.add(wordDTO);
                    break;
                case ITALIAN:
                    italianWords.add(wordDTO);
                    break;
                case FRENCH:
                    frenchWords.add(wordDTO);
                    break;
                case SPANISH:
                    spanishWords.add(wordDTO);
                    break;
            }
        });

        model.addAttribute("germanWords", germanWords);
        model.addAttribute("frenchWords", frenchWords);
        model.addAttribute("spanishWords", spanishWords);
        model.addAttribute("italianWords", italianWords);

        return "home";
    }
}
