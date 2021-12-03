package com.example.ewears.services;

import com.example.ewears.data.dtos.request.CreateUserRequest;
import com.example.ewears.data.dtos.response.GetUserInfoResponse;
import com.example.ewears.data.dtos.response.GetUserResponse;
import com.example.ewears.data.models.Role;
import com.example.ewears.data.models.User;
import com.example.ewears.data.repositories.RoleRepository;
import com.example.ewears.data.repositories.UserRepository;
import com.example.ewears.exceptions.*;
import com.example.ewears.exceptions.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{


    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public String createUser(CreateUserRequest createUserRequest) {

    String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());

    if (userRepository.existsByUserName(createUserRequest.getUserName())){
        throw new RuntimeExceptionPlaceHolder("UserName already exist!!!");
    }
    if (userRepository.existsByEmail(createUserRequest.getEmail())){
        throw new DuplicateEmailException("Email already exist!!!");
    }

        ErrorResponse errorResponse = ErrorResponse.builder()
                .uuid(UUID.randomUUID())
                .errors(new ArrayList<>())
                .build();

    List<Role> validRoles = new ArrayList<>();

    createUserRequest.getRoleNames().forEach(roleName -> {
                roleRepository.findByRoleName(roleName).<Runnable>map(role -> () -> validRoles.add(role))
                        .orElse(() -> {

                            Error error = Error.builder()
                                    .code("400")
                                    .message(roleName + " role doesn't exist")
                                    .build();
                            errorResponse.getErrors().add(error);
                        })
                        .run();
            });

        User user = User.builder()
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .userName(createUserRequest.getUserName())
                .password(createUserRequest.getPassword())
                .email(createUserRequest.getEmail())
                .build();

        User savedUser = userRepository.save(user);

        if (!errorResponse.getErrors().isEmpty()){
            throw new SuccessCodeWithErrorResponseException(savedUser.getUserId(), errorResponse);
        }

        return savedUser.getUserId();

    }

    @Override
    public GetUserResponse getUserByUserName(String userName) {

        Optional<User>optionalUserNameOrEmail = userRepository.findByUserNameOrEmail(userName, userName);

        User userByUserName = optionalUserNameOrEmail.orElseThrow(()->
                new RuntimeExceptionPlaceHolder("UserName or Email does not exist!!!")
            );

        return GetUserResponse.builder()
                .userId(userByUserName.getUserId())
                .userName(userByUserName.getUserName())
                .firstName(userByUserName.getFirstName())
                .lastName(userByUserName.getLastName())
                .email(userByUserName.getEmail())
                .build();
    }

    @Override
    public GetUserResponse getUserByUserId(String userId) {

        Optional<User> optionalUserResponse = userRepository.findByUserId(userId);

        User userByUserId = optionalUserResponse.orElseThrow(() ->
                new RuntimeExceptionPlaceHolder("UserId does not exist!!!")
            );
        return GetUserResponse.builder()
                .userId(userByUserId.getUserId())
                .userName(userByUserId.getUserName())
                .firstName(userByUserId.getFirstName())
                .lastName(userByUserId.getLastName())
                .email(userByUserId.getEmail())
                .build();
    }

    @Override
    public GetUserInfoResponse getUserInfo() {
        return null;
    }
}
