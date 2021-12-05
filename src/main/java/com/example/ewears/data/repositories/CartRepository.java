package com.example.ewears.data.repositories;

import com.example.ewears.data.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByRoleName(String name);

    Boolean existsByRoleName(String roleName);

    @Override
    List<Role> findAll();
}
