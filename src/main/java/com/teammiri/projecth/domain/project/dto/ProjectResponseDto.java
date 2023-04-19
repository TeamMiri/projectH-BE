package com.teammiri.projecth.domain.project.dto;

import com.teammiri.projecth.domain.project.entity.Project;
import com.teammiri.projecth.domain.project.entity.ProjectStatus;
import com.teammiri.projecth.domain.userproject.entity.UserProject;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ProjectResponseDto {
    private Long projectId;
    private String title;
    private String ownerId;
    private List<String> memberIdList;
    private Integer totalNumber;
    private String introduction;
    private String location;
    private List<String> techSpec;
    private String projectImageUrl;
    private String proposalUrl;
    private List<UserProject> userProjectList;
    private ProjectStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProjectResponseDto(Project project) {
        this.projectId = project.getProjectId();
        this.title = project.getTitle();
        this.ownerId = project.getOwner().getUserId();
        this.memberIdList = project.getMemberIdList();
        this.totalNumber = project.getTotalNumber();
        this.introduction = project.getIntroduction();
        this.location = project.getLocation();
        this.techSpec = project.getTechSpec();
        this.projectImageUrl = project.getProjectImageUrl();
        this.proposalUrl = project.getProposalUrl();
        this.userProjectList = project.getUserProjectList();
        this.status = project.getStatus();
        this.createdAt = project.getCreatedAt();
        this.updatedAt = project.getUpdatedAt();
    }
}
