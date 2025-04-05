package com.example.demo.repository;

import com.example.demo.model.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest(showSql = true)
@Sql("/sql/user-repository-test-data.sql")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;


    @Test
    void findByIdAndStatus사용자정보조회(){
        //given

        //when
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1L, UserStatus.ACTIVE);

        //then
        assertThat(result.isPresent()).isTrue();
    }
    @Test
    void findByIdAndStatus사용자정보조회_결과없음(){
        //given

        //when
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1L, UserStatus.PENDING);

        //then
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void findByEmailAndStatus사용자정보조회(){
        //given

        //when
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("jkoogibook@gmail.com", UserStatus.ACTIVE);

        //then
        assertThat(result.isPresent()).isTrue();
    }
    @Test
    void findByEmailAndStatus사용자정보조회_결과없음(){
        //given

        //when
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("jkoogibook@gmail.com", UserStatus.PENDING);

        //then
        assertThat(result.isEmpty()).isTrue();
    }
}