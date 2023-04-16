package com.teammiri.projecth.domain.project.repository;

import com.teammiri.projecth.domain.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
