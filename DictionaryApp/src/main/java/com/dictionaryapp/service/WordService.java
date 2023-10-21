package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.HomeWordsDTO;
import com.dictionaryapp.model.dto.WordAddBindingModel;

public interface WordService {

    void add(WordAddBindingModel wordAddBindingModel);

    HomeWordsDTO getHomeViewData();
}
