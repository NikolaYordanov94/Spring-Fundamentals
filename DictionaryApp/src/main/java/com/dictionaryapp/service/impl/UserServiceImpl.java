package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.UserLoginBindingModel;
import com.dictionaryapp.model.dto.UserRegistrationBindingModel;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.service.LoggedUser;
import com.dictionaryapp.service.UserService;
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
    public boolean register(UserRegistrationBindingModel userRegistrationBindingModel) {

        if (!userRegistrationBindingModel.getPassword().equals(userRegistrationBindingModel.getConfirmPassword())) {
            return false;
        }

        boolean existsByUsernameOrEmail = userRepository.existsByUsernameOrEmail(
                userRegistrationBindingModel.getUsername(),
                userRegistrationBindingModel.getEmail());

        if (existsByUsernameOrEmail) {
            return false;
        }

        User user = new User();
        user.setUsername(userRegistrationBindingModel.getUsername());
        user.setEmail(userRegistrationBindingModel.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationBindingModel.getPassword()));

        userRepository.save(user);

        return true;
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {

        String username = userLoginBindingModel.getUsername();
        User user = userRepository.findByUsername(username);

        if (user != null
                && passwordEncoder.matches(userLoginBindingModel.getPassword(), user.getPassword())) {
            loggedUser.login(username);
            return true;
        }

        return false;
    }

    @Override
    public void logout() {
        this.loggedUser.logout();
    }
}
