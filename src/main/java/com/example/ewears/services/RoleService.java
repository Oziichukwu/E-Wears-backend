package com.example.ewears.services;

import com.example.ewears.data.dtos.request.CreateRoleRequest;
import com.example.ewears.data.models.Role;

import java.util.List;

public interface RoleService {

    String createRole(CreateRoleRequest createRoleRequest);

    List<Role> findAllRoles();
}
