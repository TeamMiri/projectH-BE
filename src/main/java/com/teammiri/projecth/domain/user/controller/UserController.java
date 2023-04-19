package com.teammiri.projecth.domain.user.controller;

import com.teammiri.projecth.domain.user.dto.UserRequestDto;
import com.teammiri.projecth.domain.user.dto.UserResponseDto;
import com.teammiri.projecth.domain.user.entity.User;
import com.teammiri.projecth.domain.user.service.UserService;
import com.teammiri.projecth.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.findUsers();
    }

    @GetMapping("me")
    public ApiResponse getUser() {
        User user = userService.getLoginUser();

        return ApiResponse.success("user", user);
    }

    @GetMapping("{userId}")
    public ApiResponse getUser(@PathVariable final String userId) {
        User user = userService.getUser(userId);

        return ApiResponse.success("user", user);
    }

    @PutMapping("me")
    public ApiResponse update(@RequestBody final UserRequestDto userRequestDto) {
        User user = userService.getLoginUser();
        log.info("update user, userId={}, userRequestDto={}", user.getUserId(), userRequestDto);
        String updatedUserId = userService.update(user.getUserId(), userRequestDto);
        return ApiResponse.success("userId", updatedUserId);
    }
}
