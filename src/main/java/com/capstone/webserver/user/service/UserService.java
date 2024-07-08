package com.capstone.webserver.user.service;

import com.capstone.webserver.common.config.jwt.TokenProvider;
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
    private final TokenProvider tokenProvider;

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

    public UserDto.UserLoginResponseDto login(UserDto.UserLoginDto dto) {
        String loginId = dto.getLoginId();
        String password = dto.getPassword();

        User user = userQueryDSLRepository.findByLoginId(loginId);

        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            String accessToken = tokenProvider.createAccessToken(String.format("%s:%s", user.getLoginId(), user.getType()));
            String refreshToken = tokenProvider.createRefreshToken();

            userQueryDSLRepository.saveRefreshToken(loginId, refreshToken);

            return new UserDto.UserLoginResponseDto(user.getLoginId(), user.getType().toString(), accessToken, refreshToken);
        }
        return null;
    }

    public List<UserDto.UserBasicDto> findAll() {
        List<User> users = userQueryDSLRepository.findAll();

        return users.stream()
                .map(this::toUserInfoDto)
                .toList();
    }

    public List<UserDto.UserBasicDto> findAllByType(Role type) {
        List<User> users = userQueryDSLRepository.findAllByType(type);

        return users.stream()
                .map(this::toUserInfoDto)
                .toList();
    }

    public UserDto.UserBasicDto findByLoginId(String loginId) {

        User user = userQueryDSLRepository.findByLoginId(loginId);

        return toUserInfoDto(user);
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
