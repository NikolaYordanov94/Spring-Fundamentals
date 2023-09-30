package com.softuni.mobilelele.service.impl;

import com.softuni.mobilelele.model.dto.UserRegistrationDTO;
import com.softuni.mobilelele.model.entity.UserEntity;
import com.softuni.mobilelele.repository.UserRepository;
import com.softuni.mobilelele.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {

        userRepository.save(map(userRegistrationDTO));
    }

    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        return new UserEntity();
    }
}
