package com.example.ewears.services;

import com.example.ewears.data.dtos.request.CreateUserRequest;
import com.example.ewears.data.dtos.response.GetUserInfoResponse;
import com.example.ewears.data.dtos.response.GetUserResponse;
import com.example.ewears.data.models.User;
import com.example.ewears.data.repositories.UserRepository;
import com.example.ewears.exceptions.DuplicateEmailException;
import com.example.ewears.exceptions.RuntimeExceptionPlaceHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;



    BCryptPasswordEncoder passwordEncoder;

    @Override
    public String createUser(CreateUserRequest createUserRequest) {

    String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());

    if (userRepository.existsByUserName(createUserRequest.getUserName())){
        throw new RuntimeExceptionPlaceHolder("UserName already exist!!!");
    }
    if (userRepository.existsByEmail(createUserRequest.getEmail())){
        throw new DuplicateEmailException("Email already exist!!!");
    }

        User user = User.builder()
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .userName(createUserRequest.getUserName())
                .password(createUserRequest.getPassword())
                .email(createUserRequest.getEmail())
                .build();

        User savedUser = userRepository.save(user);

        return savedUser.toString();

    }

    @Override
    public GetUserResponse getUserByUserName(String userName) {

        GetUserResponse getUserResponse;

        return null;
    }

    @Override
    public GetUserResponse getUserByUserId(String userId) {
        return null;
    }

    @Override
    public GetUserInfoResponse getUserInfo() {
        return null;
    }
}
