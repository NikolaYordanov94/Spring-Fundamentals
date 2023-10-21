package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.HomeWordsDTO;
import com.dictionaryapp.model.dto.WordAddBindingModel;
import com.dictionaryapp.model.dto.WordDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.LoggedUser;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements WordService {

    private final LanguageRepository languageRepository;
    private final WordRepository wordRepository;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;

    public WordServiceImpl(LanguageRepository languageRepository, WordRepository wordRepository, LoggedUser loggedUser, UserRepository userRepository) {
        this.languageRepository = languageRepository;
        this.wordRepository = wordRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    @Override
    public void add(WordAddBindingModel wordAddBindingModel) {

        Language language = languageRepository.findByLanguageName(wordAddBindingModel.getLanguage());
        User user = userRepository.findByUsername(loggedUser.getUsername());

        if(language != null && user != null){
            Word word = new Word();

            word.setTerm(wordAddBindingModel.getTerm());
            word.setTranslation(wordAddBindingModel.getTranslation());
            word.setExample(wordAddBindingModel.getExample());
            word.setInputDate(wordAddBindingModel.getInputDate());
            word.setLanguage(language);
            word.setAddedBy(user);

            wordRepository.save(word);
        }
    }

    @Override
    public HomeWordsDTO getHomeViewData() {
        List<Word> words = wordRepository.findAll();

        List<WordDTO> wordsHome = words.stream().map(WordDTO::createFromWord)
                .collect(Collectors.toList());



        return new HomeWordsDTO(wordsHome);
    }
}