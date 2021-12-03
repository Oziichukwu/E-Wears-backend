package com.example.ewears;


import com.example.ewears.data.models.User;
import com.example.ewears.data.repositories.UserRepository;
import org.assertj.core.api.Assertions;
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
        String userId = "2b36f09b-e1a3-400b-8519-fdc6df1284c1";
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        User user = optionalUser.get();
        user.setPassword("12345ugc");
        userRepository.save(user);

        User updatedUser = userRepository.findByUserId(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("12345ugc");
    }

    @Test

    public void testGet(){
        String userId = "2b36f09b-e1a3-400b-8519-fdc6df1284c1";

        Optional<User>optionalUser = userRepository.findByUserId(userId);

        Assertions.assertThat(optionalUser).isPresent();
    }
}
