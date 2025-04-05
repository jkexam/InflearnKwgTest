package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@TestPropertySource("classpath:test-application.properties")
@Sql("/sql/user-service-test-data.sql")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void getByEmail_Active상태유저조회() {
        //given
        String email = "jkoogibook@gmail.com";

        //when
        UserEntity userEntity = userService.getByEmail(email);

        //then
        assertThat(userEntity.getNickname()).isEqualTo("jkoogibook");
        assertThat(userEntity.getEmail()).isEqualTo(email);
    }
    @Test
    void getByEmail_Panding상태유저조회안됨() {
        //given
        String email = "jkoogibook1@gmail.com";

        //when

        //then
        assertThatThrownBy(()->{
            userService.getByEmail(email);
        }).isInstanceOf(ResourceNotFoundException.class);
    }
}