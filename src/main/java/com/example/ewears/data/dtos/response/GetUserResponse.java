package com.example.ewears.data.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserResponse {

    private String userId;

    private String userName;

    private String firstName;

    private String lastName;

    private String email;



}
