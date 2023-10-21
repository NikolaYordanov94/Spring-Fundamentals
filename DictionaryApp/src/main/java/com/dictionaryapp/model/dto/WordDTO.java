package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enums.LanguageName;

import java.time.LocalDate;

public class WordDTO {

    private String term;

    private String translation;

    private String example;

    private User addedBy;

    private LocalDate inputDate;

    private LanguageName language;

    public static WordDTO createFromWord(Word word){
        WordDTO wordDTO = new WordDTO();

        wordDTO.setTerm(word.getTerm());
        wordDTO.setExample(word.getExample());
        wordDTO.setTranslation(word.getTranslation());
        wordDTO.setAddedBy(word.getAddedBy());
        wordDTO.setInputDate(word.getInputDate());
        wordDTO.setLanguage(word.getLanguage().getLanguageName());

        return wordDTO;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public LanguageName getLanguage() {
        return language;
    }

    public void setLanguage(LanguageName language) {
        this.language = language;
    }
}
