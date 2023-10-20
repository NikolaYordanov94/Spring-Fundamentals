package com.plannerapp.service.Impl;

import com.plannerapp.model.UserLoginBindingModel;
import com.plannerapp.model.UserRegisterBindingModel;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.LoggedUser;
import com.plannerapp.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {
        if(!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            return false;
        }

        User dbUser = userRepository.findByUsername(userRegisterBindingModel.getUsername());

        if(dbUser != null){
            return false;
        }

        User user = new User();
        user.setUsername(userRegisterBindingModel.getUsername());
        user.setEmail(userRegisterBindingModel.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        userRepository.save(user);

        return true;
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {

        User user = userRepository.findByUsername(userLoginBindingModel.getUsername());
        String username = userLoginBindingModel.getUsername();

        if(user != null && passwordEncoder.matches(userLoginBindingModel.getPassword(), user.getPassword())){
            loggedUser.login(username);

            return true;
        }

        return false;
    }

    @Override
    public void logout() {
        loggedUser.logout();
    }
}
