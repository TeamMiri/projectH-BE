package com.teammiri.projecth.domain.project.dto;

import com.teammiri.projecth.domain.project.entity.Project;
import com.teammiri.projecth.domain.project.entity.ProjectStatus;
import com.teammiri.projecth.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectRequestDto {
    private String title;
    private String ownerId;
    private List<String> memberIdList = new ArrayList<>();
    private Integer totalNumber;
    private List<String> techSpec = new ArrayList<>();
    private String introduction;
    private String location;
    private ProjectStatus status;

    public Project toEntity(User owner) {
        memberIdList.add(owner.getName() + "," + ownerId);
        return Project.builder()
                .title(title)
                .owner(owner)
                .memberIdList(memberIdList)
                .totalNumber(totalNumber)
                .techSpec(techSpec)
                .introduction(introduction)
                .location(location)
                .status(status)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
