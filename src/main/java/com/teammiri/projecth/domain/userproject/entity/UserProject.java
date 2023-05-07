package com.teammiri.projecth.domain.userproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.teammiri.projecth.domain.project.entity.Project;
import com.teammiri.projecth.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class UserProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_project_id")
    private Long id;

    @Setter
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "project_id")
    private Project project;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public UserProject(User user, Project project) {
        this.user = user;
        this.project = project;
        createdAt = LocalDateTime.now();
    }

    public void joinProject(Project project) {
        this.project = project;
        project.getUserProjectList().add(this);
    }
}
