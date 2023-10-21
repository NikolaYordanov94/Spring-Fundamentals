package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.UserLoginBindingModel;
import com.dictionaryapp.model.dto.UserRegistrationBindingModel;

public interface UserService {
    boolean register(UserRegistrationBindingModel userRegistrationBindingModel);

    boolean login(UserLoginBindingModel userLoginBindingModel);

    void logout();

}
