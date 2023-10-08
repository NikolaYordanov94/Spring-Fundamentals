package com.softuni.mobilelele.service.impl;

import com.softuni.mobilelele.model.dto.UserLoginDTO;
import com.softuni.mobilelele.model.dto.UserRegistrationDTO;
import com.softuni.mobilelele.model.entity.UserEntity;
import com.softuni.mobilelele.repository.UserRepository;
import com.softuni.mobilelele.service.UserService;
import com.softuni.mobilelele.util.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {

        UserEntity userEntity = map(userRegistrationDTO);

        userRepository.save(userEntity);
    }

    @Override
    public boolean loginUser(UserLoginDTO userLoginDTO) {
        var userEntity = userRepository
                .findByEmail(userLoginDTO.email())
                .orElse(null);

        boolean loginSuccess = false;

        if(userEntity != null){
            String rawPassword = userLoginDTO.password();
            String encodedPassword = userEntity.getPassword();

            loginSuccess = encodedPassword != null &&
                    passwordEncoder.matches(rawPassword, encodedPassword);

            if(loginSuccess){
                currentUser.setLogged(true);
                currentUser.setFirstName(currentUser.getFirstName());
                currentUser.setLastName(currentUser.getLastName());
            }else {
                currentUser.logout();
            }

        }

        return loginSuccess;

    }

    @Override
    public void logoutUser() {
        currentUser.logout();
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
