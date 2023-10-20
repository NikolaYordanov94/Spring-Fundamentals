package com.plannerapp.service;

import com.plannerapp.model.UserLoginBindingModel;
import com.plannerapp.model.UserRegisterBindingModel;

public interface UserService {
    boolean register(UserRegisterBindingModel userRegisterBindingModel);

    boolean login(UserLoginBindingModel userLoginBindingModel);

    void logout();

}
