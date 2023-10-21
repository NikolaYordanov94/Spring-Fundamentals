package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.enums.LanguageName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class WordAddBindingModel {

    @Size(min = 2, max = 40, message = "The term length must be between 2 and 40 characters!")
    private String term;

    @Size(min = 2, max = 80, message = "The translation length must be between 2 and 80 characters!")
    private String translation;

    @Size(min = 2, max = 200, message = "The example must be between 2 and 200 characters!")
    private String example;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private LocalDate inputDate;

    @NotNull(message = "You must select a language!")
    private LanguageName language;

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

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public LanguageName getLanguageName() {
        return language;
    }

    public void setLanguageName(LanguageName language) {
        this.language = language;
    }
}
