package com.example.ewears.data.repositories;

import com.example.ewears.data.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByUserName(String userName);
    Optional<User> findByUserNameOrEmail(String userName, String email);
    Optional<User> findByUserId(String userId);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);
}
