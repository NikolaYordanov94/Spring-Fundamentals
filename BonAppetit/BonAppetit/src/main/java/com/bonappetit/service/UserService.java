package com.bonappetit.service;

import com.bonappetit.model.dto.user.UserLoginBindingModel;
import com.bonappetit.model.dto.user.UserRegisterBindingModel;

public interface UserService {

    boolean register(UserRegisterBindingModel userRegisterBindingModel);

    boolean login(UserLoginBindingModel userLoginBindingModel);

    void logout();
}
