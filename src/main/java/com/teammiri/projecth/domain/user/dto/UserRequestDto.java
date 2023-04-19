package com.teammiri.projecth.domain.user.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class UserRequestDto {
    private Integer age;
    private String gender;
    private List<String> techSpec;
    private String contactNumber;
    private String introduction;
    private String profileImageUrl;
}
