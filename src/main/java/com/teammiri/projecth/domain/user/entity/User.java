package com.teammiri.projecth.domain.user.entity;

import com.teammiri.projecth.common.StringListConverter;
import com.teammiri.projecth.domain.user.dto.UserRequestDto;
import com.teammiri.projecth.domain.userproject.entity.UserProject;
import com.teammiri.projecth.oauth.entity.ProviderType;
import com.teammiri.projecth.oauth.entity.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "USER_")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @JsonIgnore
    @Id
    @Column(name = "USER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    @Column(name = "USER_ID", length = 64, unique = true)
    @NotNull
    @Size(max = 64)
    private String userId;

    @Column(name = "NAME", length = 100)
    @NotNull
    @Size(max = 100)
    private String name;

    @JsonIgnore
    @Column(name = "PASSWORD", length = 128)
    @NotNull
    @Size(max = 128)
    private String password;

    @Column(name = "EMAIL", length = 512, unique = true)
    @NotNull
    @Size(max = 512)
    private String email;

    @Column(name = "EMAIL_VERIFIED_YN", length = 1)
    @NotNull
    @Size(min = 1, max = 1)
    private String emailVerifiedYn;

    @Column(name = "AGE")
    @Max(value = 150)
    private Integer age;

    @Column(name = "GENDER", length = 1)
    @Size(max = 1)
    private String gender;

    // techSpec
    @Column(name = "TECH_SPEC")
    @Convert(converter = StringListConverter.class)
    private List<String> techSpec = new ArrayList<>();

    @Column(name = "PROFILE_IMAGE_URL", length = 512)
    @NotNull
    @Size(max = 512)
    private String profileImageUrl;

    @Column(name = "CONTACT_NUMBER", length = 20)
    @Size(max = 20)
    private String contactNumber;

    @Column(name = "INTRODUCTION", length = 1000)
    @Size(max = 1000)
    private String introduction;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserProject> userProjectList = new ArrayList<>();

    @Column(name = "PROVIDER_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProviderType providerType;

    @Column(name = "ROLE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public User(
            @NotNull @Size(max = 64) String userId,
            @NotNull @Size(max = 100) String name,
            @NotNull @Size(max = 512) String email,
            @NotNull @Size(max = 1) String emailVerifiedYn,
            @NotNull @Size(max = 512) String profileImageUrl,
            @NotNull ProviderType providerType,
            @NotNull RoleType roleType,
            @NotNull LocalDateTime createdAt,
            @NotNull LocalDateTime modifiedAt
    ) {
        this.userId = userId;
        this.name = name;
        this.password = "NO_PASS";
        this.email = email != null ? email : "NO_EMAIL";
        this.emailVerifiedYn = emailVerifiedYn;
        this.age = age != null ? age : 0;
        this.gender = gender != null? gender : "";
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "";
        this.contactNumber = contactNumber != null ? contactNumber : "";
        this.introduction = introduction != null ? introduction : "";
        this.providerType = providerType;
        this.roleType = roleType;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public void update(UserRequestDto userRequestDto) {
        this.age = userRequestDto.getAge();
        this.gender = userRequestDto.getGender();
        this.techSpec = userRequestDto.getTechSpec();
        this.contactNumber = userRequestDto.getContactNumber();
        this.introduction = userRequestDto.getIntroduction();
        this.profileImageUrl = userRequestDto.getProfileImageUrl();
        this.modifiedAt = LocalDateTime.now();
    }

    public void addUserProject(UserProject userProject) {
        this.userProjectList.add(userProject);
        userProject.setUser(this);
    }
}


