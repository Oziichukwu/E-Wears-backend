package com.example.ewears.services;

import com.example.ewears.data.dtos.request.CreateUserRequest;
import com.example.ewears.data.dtos.response.GetUserInfoResponse;
import com.example.ewears.data.dtos.response.GetUserResponse;
import com.example.ewears.data.models.Role;
import com.example.ewears.data.models.User;
import com.example.ewears.data.repositories.RoleRepository;
import com.example.ewears.data.repositories.UserRepository;
import com.example.ewears.exceptions.DuplicateEmailException;
import com.example.ewears.exceptions.Error;
import com.example.ewears.exceptions.ErrorResponse;
import com.example.ewears.exceptions.RuntimeExceptionPlaceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

        return savedUser.getUserId();

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
