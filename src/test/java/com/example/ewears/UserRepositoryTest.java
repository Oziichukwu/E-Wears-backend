package com.example.ewears;


import com.example.ewears.data.models.User;
import com.example.ewears.data.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;


    @Test
    public void testThatNewUserWasAdded(){

        User user = User.builder()
                .firstName("uchechukwu")
                .lastName("ukaegbu")
                .userName("Goodboyz")
                .email("ukaegbu.goodnews@yahoo.com")
                .password("456ugc")
                .build();

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getUserId()).isGreaterThan("0");

    }

    @Test
    public void testThatDataBaseReturnsAllSavedUsers(){
        userRepository.findAll();
    }

    @Test
    public void testUpdate(){
        String userId = "d6ab639d-0411-437e-b54b-d311c63a51fe";
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        User user = optionalUser.get();
        user.setPassword("12345ugc");
        userRepository.save(user);

        User updatedUser = userRepository.findByUserId(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("12345ugc");
    }

    @Test
    public void getUserByIdTest(){
        String userId = "d6ab639d-0411-437e-b54b-d311c63a51fe";

        Optional<User>optionalUser = userRepository.findByUserId(userId);

        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void deleteUserByIdTest(){

        String userId = "e666b095-6727-4229-a4ab-35d2be2fb388";
        userRepository.deleteById(userId);

        Optional<User>optionalUser = userRepository.findByUserId(userId);

        Assertions.assertThat(optionalUser).isNotPresent();

    }

    @Test
    public void findUserByUserName(){

        String userName = "Goodboyz";

        Optional<User>optionalUser = userRepository.findByUserName(userName);

        Assertions.assertThat(optionalUser).isPresent();

        System.out.println(optionalUser);
    }
}
