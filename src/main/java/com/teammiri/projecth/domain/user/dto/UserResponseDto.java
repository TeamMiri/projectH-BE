package com.teammiri.projecth.domain.user.dto;

import com.teammiri.projecth.domain.user.entity.User;
import com.teammiri.projecth.oauth.entity.ProviderType;
import com.teammiri.projecth.oauth.entity.RoleType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class UserResponseDto {
    private String userId;
    private String name;
    private String email;
    private String emailVerifiedYn;
    private Integer age;
    private String gender;
    private List<String> techSpec;
    private String profileImageUrl;
    private String contactNumber;
    private String introduction;
    private ProviderType providerType;
    private RoleType roleType;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.emailVerifiedYn = user.getEmailVerifiedYn();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.techSpec = user.getTechSpec();
        this.profileImageUrl = user.getProfileImageUrl();
        this.contactNumber = user.getContactNumber();
        this.introduction = user.getIntroduction();
        this.providerType = user.getProviderType();
        this.roleType = user.getRoleType();
        this.createdAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
    }
}
