package com.example.ewears.services;


import com.example.ewears.data.dtos.request.CreateUserRequest;
import com.example.ewears.data.dtos.response.GetUserInfoResponse;
import com.example.ewears.data.dtos.response.GetUserResponse;

public interface UserService{

    String createUser (CreateUserRequest createUserRequest);

    GetUserResponse getUserByUserName(String userName);

    GetUserResponse getUserByUserId(String userId);

    GetUserInfoResponse getUserInfo();
}
