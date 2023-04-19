package com.teammiri.projecth.domain.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teammiri.projecth.common.StringListConverter;
import com.teammiri.projecth.domain.project.dto.ProjectRequestDto;
import com.teammiri.projecth.domain.user.entity.User;
import com.teammiri.projecth.domain.userproject.entity.UserProject;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Project {
    @JsonIgnore
    @Id
    @Column(name = "PROJECT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(name = "TITLE", length = 64)
    @NotNull
    @Size(max = 64)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User owner;

    @Column(name = "user_id", insertable = false, updatable = false)
    private String ownerId;

    @ElementCollection
    private List<String> memberIdList = new ArrayList<>();

    @Column(name = "TOTAL_NUMBER")
    private Integer totalNumber;

    @Column(name = "INTRODUCTION", length = 512)
    @NotNull
    @Size(max = 512)
    private String introduction;

    @Column(name = "LOCATION", length = 64)
    @Size(max = 64)
    private String location;

    @Column(name = "TECH_SPEC")
    @Convert(converter = StringListConverter.class)
    private List<String> techSpec = new ArrayList<>();

    @Setter
    @Column(name = "PROJECT_IMAGE_URL", length = 512)
    @Size(max = 512)
    private String projectImageUrl;

    @Setter
    @Column(name = "PROPOSAL_URL", length = 512)
    @Size(max = 512)
    private String proposalUrl;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<UserProject> userProjectList = new ArrayList<>();

    @Column(name = "STATUS", length = 32)
    @NotNull
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public Project(
            @NotNull @Size(max = 64) String title,
            @NotNull User owner,
            @NotNull String ownerId,
            List<String> memberIdList,
            Integer totalNumber,
            @NotNull @Size(max = 512) String introduction,
            @Size(max = 64) String location,
            List<String> techSpec,
            List<UserProject> userProjectList,
            String projectImageUrl,
            String proposalUrl,
            @NotNull ProjectStatus status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.title = title;
        this.owner = owner;
        this.ownerId = ownerId;
        this.memberIdList = memberIdList;
        this.totalNumber = totalNumber;
        this.introduction = introduction;
        this.location = location;
        this.techSpec = techSpec;
        this.userProjectList = userProjectList;
        this.projectImageUrl = projectImageUrl;
        this.proposalUrl = proposalUrl;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update(
            ProjectRequestDto params, User owner) {
        if (params.getTitle() != null) {
            this.title = params.getTitle();
        }
        if (params.getOwnerId() != null) {
            this.ownerId = params.getOwnerId();
        }
        if (owner != null) {
            this.owner = owner;
        }
        if (params.getMemberIdList() != null) {
            this.memberIdList = params.getMemberIdList();
        }
        if (params.getTotalNumber() != null) {
            this.totalNumber = params.getTotalNumber();
        }
        if (params.getIntroduction() != null) {
            this.introduction = params.getIntroduction();
        }
        if (params.getLocation() != null) {
            this.location = params.getLocation();
        }
        if (params.getTechSpec() != null) {
            this.techSpec = params.getTechSpec();
        }
        if (params.getStatus() != null) {
            this.status = params.getStatus();
        }
        this.updatedAt = LocalDateTime.now();
    }
}
