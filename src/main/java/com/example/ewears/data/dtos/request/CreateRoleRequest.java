package com.example.ewears.data.dtos.request;


import com.example.ewears.data.models.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleRequest extends DateAudit {

    @NotBlank
    private String roleName;

    @NotBlank
    private String roleDescription;

}
