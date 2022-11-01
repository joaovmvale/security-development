package com.securityDevelopment.project.repository;

import com.securityDevelopment.project.model.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<ProjectModel, UUID> {
}
