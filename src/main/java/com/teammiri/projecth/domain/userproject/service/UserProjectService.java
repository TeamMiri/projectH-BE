package com.teammiri.projecth.domain.userproject.service;

import com.teammiri.projecth.domain.project.repository.ProjectRepository;
import com.teammiri.projecth.domain.user.entity.User;
import com.teammiri.projecth.domain.user.repository.UserRepository;
import com.teammiri.projecth.domain.userproject.entity.UserProject;
import com.teammiri.projecth.domain.userproject.repository.UserProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        projectRepository.findById(projectId).ifPresent(
                project -> {
                    UserProject userProject = new UserProject();
                    user.addUserProject(userProject);
                    userProject.joinProject(project);
                    project.getMemberIdList().add(user.getUserId());
                    userProjectRepository.save(userProject);
                });

    }
}

