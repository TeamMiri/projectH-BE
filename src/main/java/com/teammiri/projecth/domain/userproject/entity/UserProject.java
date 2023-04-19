package com.teammiri.projecth.domain.userproject.entity;

import com.teammiri.projecth.domain.project.entity.Project;
import com.teammiri.projecth.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class UserProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_project_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
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
