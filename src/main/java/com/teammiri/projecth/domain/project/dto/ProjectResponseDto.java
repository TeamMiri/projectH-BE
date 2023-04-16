package com.teammiri.projecth.domain.project.dto;

import com.teammiri.projecth.domain.project.entity.Project;
import com.teammiri.projecth.domain.project.entity.ProjectStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProjectResponseDto {
    private Long projectId;
    private String title;
    private Long ownerUserId;
    private Integer totalNumber;
    private String introduction;
    private String location;
    private ProjectStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProjectResponseDto(Project project) {
        this.projectId = project.getProjectId();
        this.title = project.getTitle();
        this.ownerUserId = project.getOwnerUserId();
        this.totalNumber = project.getTotalNumber();
        this.introduction = project.getIntroduction();
        this.location = project.getLocation();
        this.status = project.getStatus();
        this.createdAt = project.getCreatedAt();
        this.updatedAt = project.getUpdatedAt();
    }
}
