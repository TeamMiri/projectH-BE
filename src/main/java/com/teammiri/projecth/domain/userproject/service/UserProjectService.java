package com.teammiri.projecth.domain.userproject.service;

import com.teammiri.projecth.domain.project.repository.ProjectRepository;
import com.teammiri.projecth.domain.user.repository.UserRepository;
import com.teammiri.projecth.domain.userproject.entity.UserProject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProjectService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public void join(Long projectId, Long userId) {
        userRepository.findById(userId).ifPresent(user -> {
            projectRepository.findById(projectId).ifPresent(
                    project -> {
                        UserProject userProject = new UserProject();
                        user.addUserProject(userProject);
                        userProject.joinProject(project);

            });
        });
    }
}