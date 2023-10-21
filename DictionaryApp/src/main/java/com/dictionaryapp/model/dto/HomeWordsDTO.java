package com.dictionaryapp.model.dto;

import java.util.ArrayList;
import java.util.List;

public class HomeWordsDTO {

    private List<WordDTO> words;

    public HomeWordsDTO() {
        this.words = new ArrayList<>();
    }

    public HomeWordsDTO(List<WordDTO> words) {
        this.words = words;
    }

    public List<WordDTO> getWords() {
        return words;
    }

    public void setWords(List<WordDTO> words) {
        this.words = words;
    }
}
