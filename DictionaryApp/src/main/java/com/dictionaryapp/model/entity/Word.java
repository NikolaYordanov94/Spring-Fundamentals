package com.dictionaryapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Table(name = "words")
public class Word extends BaseEntity{

    @Column(nullable = false)
    @Length(min = 2, max = 40)
    private String term;

    @Column(nullable = false)
    @Length(min = 2, max = 80)
    private String translation;

    @Length(min = 2, max = 200)
    private String example;


    @Column(nullable = false)
    @PastOrPresent
    private LocalDate inputDate;

    @ManyToOne
    private Language language;

    @ManyToOne
    private User addedBy;

    public Word() {
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

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }
}
