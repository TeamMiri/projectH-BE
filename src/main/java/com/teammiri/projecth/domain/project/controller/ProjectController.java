package com.teammiri.projecth.domain.project.controller;

import com.teammiri.projecth.domain.project.dto.ProjectRequestDto;
import com.teammiri.projecth.domain.project.dto.ProjectResponseDto;
import com.teammiri.projecth.domain.project.service.ProjectService;
import com.teammiri.projecth.domain.userproject.service.UserProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService projectService;
    private final UserProjectService userProjectService;

    /**
     * 프로젝트 생성
     */
    @PostMapping
    public Long create(@RequestBody final ProjectRequestDto projectRequestDto) {
        log.info("createProject, projectRequestDto={}", projectRequestDto);
        return projectService.create(projectRequestDto);
    }

    /**
     * 프로젝트 리스트 조회
     */
    @GetMapping
    public List<ProjectResponseDto> findAll() {
        log.info("findAllProject");
        return projectService.findAll();
    }

    /**
     * 프로젝트 상세 조회
     */
    @GetMapping("/{projectId}")
    public ProjectResponseDto findById(@PathVariable final Long projectId) {
        log.info("findProjectById, projectId={}", projectId);
        return projectService.findById(projectId);
    }

    /**
     * 프로젝트 수정
     */
    @PatchMapping("/{projectId}")
    public Long update(@PathVariable final Long projectId, @RequestBody final ProjectRequestDto projectRequestDto) {
        log.info("updateProject, projectId={}, projectRequestDto={}", projectId, projectRequestDto);
        return projectService.update(projectId, projectRequestDto);
    }

    /**
     * 프로젝트 참가
     */
    @PostMapping("/{projectId}/join")
    public void join(@PathVariable final Long projectId, @RequestBody final Long userId) {
        log.info("joinProject, projectId={}, userId={}", projectId, userId);
        userProjectService.join(projectId, userId);
    }
}
