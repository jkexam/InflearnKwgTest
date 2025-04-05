package com.example.demo.repository;

import com.example.demo.model.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest(showSql = true)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void UserRepository연결확인(){
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("jkoogibook@gmail.com");
        userEntity.setAddress("inchun");
        userEntity.setNickname("jkoogi");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("34cebab6-0f90-4ae1-b264-9cea6482ba1a");

        //when
        UserEntity result = userRepository.save(userEntity);

        //then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    void findByIdAndStatus사용자정보조회(){
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("jkoogibook@gmail.com");
        userEntity.setAddress("inchun");
        userEntity.setNickname("jkoogi");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("34cebab6-0f90-4ae1-b264-9cea6482ba1a");

        //when
        userRepository.save(userEntity);
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1L, UserStatus.ACTIVE);

        //then
        assertThat(result.isPresent()).isTrue();
    }
    @Test
    void findByIdAndStatus사용자정보조회_결과없음(){
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("jkoogibook@gmail.com");
        userEntity.setAddress("inchun");
        userEntity.setNickname("jkoogi");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("34cebab6-0f90-4ae1-b264-9cea6482ba1a");

        //when
        userRepository.save(userEntity);
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1L, UserStatus.PENDING);

        //then
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void findByEmailAndStatus사용자정보조회(){
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("jkoogibook@gmail.com");
        userEntity.setAddress("inchun");
        userEntity.setNickname("jkoogi");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("34cebab6-0f90-4ae1-b264-9cea6482ba1a");

        //when
        userRepository.save(userEntity);
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("jkoogibook@gmail.com", UserStatus.ACTIVE);

        //then
        assertThat(result.isPresent()).isTrue();
    }
    @Test
    void findByEmailAndStatus사용자정보조회_결과없음(){
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("jkoogibook@gmail.com");
        userEntity.setAddress("inchun");
        userEntity.setNickname("jkoogi");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("34cebab6-0f90-4ae1-b264-9cea6482ba1a");

        //when
        userRepository.save(userEntity);
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("jkoogibook@gmail.com", UserStatus.PENDING);

        //then
        assertThat(result.isEmpty()).isTrue();
    }
}