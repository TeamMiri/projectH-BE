package com.teammiri.projecth.domain.userproject.repository;

import com.teammiri.projecth.domain.userproject.entity.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProjectRepository extends JpaRepository<UserProject, Long> {
}
