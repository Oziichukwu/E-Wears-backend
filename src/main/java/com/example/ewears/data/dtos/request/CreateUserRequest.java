package com.example.ewears.data.dtos.request;


import com.example.ewears.data.models.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    private String userId;

    @NotBlank
    @Size(max = 40, message = "UserName length should not be greater than 40 characters")
    private String userName;

    @NotBlank
    @Size(min = 6, max = 20 , message = "password length should be between 6 and 20 characters")
    private String password;

    @NotBlank
    @Size(max = 40, message = "UserName length should not be greater than 40 characters")
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Size(max = 40, message = "UserName length should not be greater than 40 characters")
    @Email
    private String email;

    @NotBlank
    private Gender gender;


    private List<String> roleNames;

}
