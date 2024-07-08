package com.capstone.webserver.user.dto;

import com.capstone.webserver.user.entity.Gender;
import com.capstone.webserver.user.entity.Role;
import com.capstone.webserver.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResponseDto {
        private String type;
        private String department;
        private String loginId;
        private String name;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserBasicDto {
        private String type;
        private String loginId;
        private String password;
        private String name;
        private String phone;
        private String email;
        private String department;
        private String gender;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserLoginDto {
        private String loginId;
        private String password;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserLoginResponseDto {
        private String loginId;
        private String type;
        private String accessToken;
        private String refreshToken;
    }
}
