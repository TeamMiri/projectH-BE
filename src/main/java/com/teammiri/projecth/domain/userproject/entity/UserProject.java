package com.teammiri.projecth.domain.userproject.entity;

import com.teammiri.projecth.domain.project.entity.Project;
import com.teammiri.projecth.domain.user.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class UserProject {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void joinProject(Project project) {
        this.project = project;
        project.getUserProjectList().add(this);
    }
}
