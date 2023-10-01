package com.softuni.mobilelele.service.impl;

import com.softuni.mobilelele.model.dto.UserRegistrationDTO;
import com.softuni.mobilelele.model.entity.UserEntity;
import com.softuni.mobilelele.repository.UserRepository;
import com.softuni.mobilelele.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {

        UserEntity userEntity = map(userRegistrationDTO);

        userRepository.save(userEntity);
    }

    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        UserEntity userEntity = new UserEntity();

        userEntity.setActive(true);
        userEntity.setFirstName(userRegistrationDTO.firstName());
        userEntity.setLastName(userRegistrationDTO.lastName());
        userEntity.setEmail(userRegistrationDTO.email());
        userEntity.setPassword(passwordEncoder.encode(userRegistrationDTO.password()));

        return userEntity;

//                .setActive(true)
//                .setFirstName(userRegistrationDTO.firstName())
//                .setLastName(userRegistrationDTO.lastName())
//                .setEmail(userRegistrationDTO.email())
//                .setPassword(passwordEncoder.encode(userRegistrationDTO.password()));
    }
}
