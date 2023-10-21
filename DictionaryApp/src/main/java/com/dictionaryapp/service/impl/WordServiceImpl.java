package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.WordAddBindingModel;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {

    private final LanguageRepository languageRepository;
    private final WordRepository wordRepository;

    public WordServiceImpl(LanguageRepository languageRepository, WordRepository wordRepository) {
        this.languageRepository = languageRepository;
        this.wordRepository = wordRepository;
    }

    @Override
    public void add(WordAddBindingModel wordAddBindingModel) {

        Language language = languageRepository.findByLanguageName(wordAddBindingModel.getLanguageName());

        if(language != null){
            Word word = new Word();

            word.setTerm(wordAddBindingModel.getTerm());
            word.setTranslation(wordAddBindingModel.getTranslation());
            word.setExample(wordAddBindingModel.getExample());
            word.setInputDate(wordAddBindingModel.getInputDate());
            word.setLanguage(language);

            wordRepository.save(word);
        }



    }
}
