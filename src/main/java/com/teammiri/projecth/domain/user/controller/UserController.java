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
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        return ApiResponse.success("user", user);
    }

    @PatchMapping("me")
    public String update(@PathVariable final String userId, @RequestBody final UserRequestDto userRequestDto) {
        log.info("update user, userId={}, userRequestDto={}", userId, userRequestDto);
        return userService.update(userId, userRequestDto);
    }
}
