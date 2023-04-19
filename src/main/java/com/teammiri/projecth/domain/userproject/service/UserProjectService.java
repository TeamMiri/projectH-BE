package com.teammiri.projecth.domain.userproject.service;

import com.teammiri.projecth.domain.project.entity.Project;
import com.teammiri.projecth.domain.project.repository.ProjectRepository;
import com.teammiri.projecth.domain.user.entity.User;
import com.teammiri.projecth.domain.user.repository.UserRepository;
import com.teammiri.projecth.domain.userproject.entity.UserProject;
import com.teammiri.projecth.domain.userproject.repository.UserProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserProjectService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final UserProjectRepository userProjectRepository;

    public void join(Long projectId, String userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new IllegalStateException("해당 유저가 없습니다. id=" + userId);
        }
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalStateException("해당 프로젝트가 없습니다. id=" + projectId));
        project.getMemberIdList().add(user.getUserId());
        UserProject saved = userProjectRepository.save(UserProject.builder().user(user).project(project).build());
        log.info("saved: {}", saved);
    }
}

