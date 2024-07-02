package com.capstone.webserver.user.api;

import com.capstone.webserver.user.dto.UserDto;
import com.capstone.webserver.user.entity.User;
import com.capstone.webserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto.UserBasicDto>> findAllUsers() {
        List<UserDto.UserBasicDto> users = userService.findAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/type/{type}")
    public ResponseEntity<List<UserDto.UserBasicDto>> findAllUsersByType(@PathVariable("type") String type) {
        List<UserDto.UserBasicDto> users = userService.findAllByType(type);

        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/loginId/{loginId}")
    public ResponseEntity<User> findByLoginId(@PathVariable("loginId") String loginId) {
        User user = userService.findByLoginId(loginId);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/users/sign-up")
    public ResponseEntity<Long> saveUser(@RequestBody UserDto.UserBasicDto dto) {
        Long userId = userService.save(dto);
        return ResponseEntity.ok(userId);
    }

    @GetMapping("/users/check-availability")
    public ResponseEntity<Boolean> checkDuplicateLoginId(@RequestParam("loginId") String loginId) {
        Boolean isDuplicated = userService.isDuplicated(loginId);

        return ResponseEntity.ok(isDuplicated);
    }

}
