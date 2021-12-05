package com.example.ewears.services;

import com.example.ewears.data.dtos.request.CreateRoleRequest;
import com.example.ewears.data.models.Role;
import com.example.ewears.data.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;


    @Override
    public String createRole(CreateRoleRequest createRoleRequest) {

        Role role = Role.builder()
                .roleName(createRoleRequest.getRoleName())
                .roleDescription(createRoleRequest.getRoleDescription())
                .build();

        Role savedRole = roleRepository.save(role);

        return savedRole.getId();
    }
}
