package com.teammiri.projecth.domain.user.service;

import com.teammiri.projecth.domain.user.dto.UserRequestDto;
import com.teammiri.projecth.domain.user.dto.UserResponseDto;
import com.teammiri.projecth.domain.user.entity.User;
import com.teammiri.projecth.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * 유저 리스트 조회
     *
     */
    public List<UserResponseDto> findUsers() {
        Sort sort = Sort.by(Sort.Direction.DESC, "userSeq", "createdAt");
        List<User> userList = userRepository.findAll(sort);
        return userList.stream().map(UserResponseDto::new).collect(Collectors.toList());
    }

    /**
     * 유저 상세 조회
     * @param userId
     * @return
     */
     public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    /**
     * 로그인한 유저 정보 조회
     * @return
     */
    public User getLoginUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return getUser(principal.getUsername());
    }
    /**
     * 유저 정보 업데이트
     */
    @Transactional
    public String update(String userId, UserRequestDto userQueryDto) {
        User userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new IllegalArgumentException("해당 유저가 없습니다. id=" + userId);
        }
        userEntity.update(userQueryDto);
        log.info("updated user, userId={}, userEntity={}", userId, userEntity);
        return userEntity.getUserId();
    }
}
