package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UserStatus;
import com.example.demo.model.dto.UserCreateDto;
import com.example.demo.repository.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@TestPropertySource("classpath:test-application.properties")
@SqlGroup({
        @Sql(value="/sql/user-service-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value="/sql/user-service-delete-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
})
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
    void getByEmail_Pending상태유저조회안됨() {
        //given
        String email = "jkoogibook1@gmail.com";

        //when

        //then
        assertThatThrownBy(()->{
            userService.getByEmail(email);
        }).isInstanceOf(ResourceNotFoundException.class);
    }
    @Test
    void getById_Active상태유저조회() {
        //given
        //when
        UserEntity userEntity = userService.getById(11);

        //then
        assertThat(userEntity.getNickname()).isEqualTo("jkoogibook");
        assertThat(userEntity.getEmail()).isEqualTo("jkoogibook@gmail.com");
    }
    @Test
    void getById_Pending상태유저조회안됨() {
        //given
        //when
        //then
        assertThatThrownBy(()->{
            userService.getById(2);
        }).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void userCreateDto_사용자등록() {
        //given
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .email("jkoogibook33@gmail.com")
                .nickname("jkoogibook33")
                .address("busan3")
                .build();

        //when
        UserEntity userEntity = userService.create(userCreateDto);

        //then
        assertThat(userEntity.getNickname()).isEqualTo("jkoogibook33");
        assertThat(userEntity.getEmail()).isEqualTo("jkoogibook33@gmail.com");
        assertThat(userEntity.getStatus()).isEqualTo(UserStatus.PENDING);
//        assertThat(userEntity.getCertificationCode()).isEqualTo("####");
    }
}