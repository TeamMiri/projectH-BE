package com.teammiri.projecth.domain.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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

    @Column(name = "OWNER_USER_ID")
    @NotNull
    private Long ownerUserId;

    @Column(name = "TOTAL_NUMBER")
    private Integer totalNumber;

    @Column(name = "INTRODUCTION", length = 512)
    @NotNull
    @Size(max = 512)
    private String introduction;

    @Column(name = "LOCATION", length = 64)
    @Size(max = 64)
    private String location;

    @Column(name = "STATUS", length = 32)
    @NotNull
    @Size(max = 32)
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
            @NotNull Long ownerUserId,
            Integer totalNumber,
            @NotNull @Size(max = 512) String introduction,
            @Size(max = 64) String location,
            @NotNull @Size(max = 32) ProjectStatus status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.title = title;
        this.ownerUserId = ownerUserId;
        this.totalNumber = totalNumber;
        this.introduction = introduction;
        this.location = location;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update(
            @NotNull @Size(max = 64) String title,
            @NotNull Long ownerUserId,
            Integer totalNumber,
            @NotNull @Size(max = 512) String introduction,
            @Size(max = 64) String location,
            @NotNull @Size(max = 32) ProjectStatus status) {
        this.title = title;
        this.ownerUserId = ownerUserId;
        this.totalNumber = totalNumber;
        this.introduction = introduction;
        this.location = location;
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }
}
