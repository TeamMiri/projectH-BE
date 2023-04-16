package com.teammiri.projecth.domain.project.dto;

import com.teammiri.projecth.domain.project.entity.Project;
import com.teammiri.projecth.domain.project.entity.ProjectStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectRequestDto {
    private String title;
    private Long ownerUserId;
    private Integer totalNumber;
    private String introduction;
    private String location;
    private ProjectStatus status;

    public Project toEntity() {
        return Project.builder()
                .title(title)
                .ownerUserId(ownerUserId)
                .totalNumber(totalNumber)
                .introduction(introduction)
                .location(location)
                .status(status)
                .build();
    }
}
