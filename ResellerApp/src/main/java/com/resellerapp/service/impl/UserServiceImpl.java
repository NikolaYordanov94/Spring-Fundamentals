package com.resellerapp.service.impl;

import com.resellerapp.model.UserLoginBindingModel;
import com.resellerapp.model.UserRegisterBindingModel;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.LoggedUser;
import com.resellerapp.service.UserService;
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
        if(userRegisterBindingModel == null){
            return false;
        }

        String username = userRegisterBindingModel.getUsername();

        if(this.userRepository.findByUsername(username) != null){
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(userRegisterBindingModel.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        this.userRepository.save(user);

        return true;
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {

        User user = findUserByUserName(userLoginBindingModel.getUsername());

        if(user != null && passwordEncoder.matches(userLoginBindingModel.getPassword(), user.getPassword())){
            loggedUser.setUsername(user.getUsername());
            loggedUser.setLogged(true);

            return true;
        }

        return false;
    }

    @Override
    public void logout() {
        loggedUser.setUsername(null);
        loggedUser.setLogged(false);
    }

    private User findUserByUserName(String username){
        return this.userRepository.findByUsername(username);
    }
}
