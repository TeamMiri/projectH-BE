package com.teammiri.projecth.domain.project.service;

import com.teammiri.projecth.domain.project.dto.ProjectRequestDto;
import com.teammiri.projecth.domain.project.dto.ProjectResponseDto;
import com.teammiri.projecth.domain.project.entity.Project;
import com.teammiri.projecth.domain.project.repository.ProjectRepository;
import com.teammiri.projecth.domain.user.entity.User;
import com.teammiri.projecth.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    /**
     * 프로젝트 생성
     */
    @Transactional
    public Long create(final ProjectRequestDto params) {
        User owner = userRepository.findByUserId(params.getOwnerId());
        return projectRepository.save(params.toEntity(owner)).getProjectId();
    }

    /**
     * 프로젝트 조회
     */
    public List<ProjectResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "projectId", "createdAt");
        List<Project> projectList = projectRepository.findAll(sort);
        return projectList.stream().map(ProjectResponseDto::new).collect(Collectors.toList());
    }

    /**
     * 프로젝트 상세 조회
     */
    public ProjectResponseDto findById(final Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 없습니다. id=" + projectId));
        return new ProjectResponseDto(project);
    }

    /**
     * 프로젝트 수정
     */
    @Transactional
    public Long update(final Long projectId, final ProjectRequestDto params) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 없습니다. id=" + projectId));
        User owner = userRepository.findByUserId(params.getOwnerId());
        project.update(params, owner);
        return projectId;
    }
}
