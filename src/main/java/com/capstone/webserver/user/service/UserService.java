package com.capstone.webserver.user.service;

import com.capstone.webserver.common.response.exception.CustomException;
import com.capstone.webserver.common.response.exception.ExceptionCode;
import com.capstone.webserver.user.dto.UserDto;
import com.capstone.webserver.user.entity.Gender;
import com.capstone.webserver.user.entity.Role;
import com.capstone.webserver.user.entity.User;
import com.capstone.webserver.user.repository.UserQueryDSLRepository;
import com.capstone.webserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserQueryDSLRepository userQueryDSLRepository;
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(UserDto.UserBasicDto dto) {
        if (isDuplicated(dto.getLoginId())) {
            throw new CustomException(ExceptionCode.DUPLICATE_ID);
        }

        User user = User.builder()
                        .type(Role.valueOf(dto.getType().toUpperCase()))
                        .loginId(dto.getLoginId())
                        .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                        .name(dto.getName())
                        .phone(dto.getPhone())
                        .email(dto.getEmail())
                        .department(dto.getDepartment())
                        .gender(Gender.valueOf(dto.getGender()))
                        .build();

        user = userRepository.save(user);
        return user.getId();
    }

    public Boolean isDuplicated(String loginId) {
        User user = userQueryDSLRepository.findByLoginId(loginId);

        return user != null;
    }

    public List<UserDto.UserBasicDto> findAll() {
        List<User> users = userQueryDSLRepository.findAll();

        return users.stream()
                .map(this::toUserInfoDto)
                .toList();
    }

    public List<UserDto.UserBasicDto> findAllByType(String type) {
        List<User> users = userQueryDSLRepository.findAllByType(type);

        return users.stream()
                .map(this::toUserInfoDto)
                .toList();
    }

    public User findByLoginId(String loginId) {
        return userQueryDSLRepository.findByLoginId(loginId);
    }

    private UserDto.UserBasicDto toUserInfoDto(User user) {
        return UserDto.UserBasicDto.builder()
                .type(user.getType().toString())
                .loginId(user.getLoginId())
                .password(user.getPassword())
                .name(user.getName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .department(user.getDepartment())
                .gender(user.getGender().toString())
                .build();
    }

}
