package com.securityDevelopment.project.controller;

import com.securityDevelopment.project.model.ProjectModel;
import com.securityDevelopment.project.model.dto.ProjectDTO;
import com.securityDevelopment.project.model.dto.ProjectResponseDTO;
import com.securityDevelopment.project.service.ProjectService;
import com.securityDevelopment.utils.exception.CustomException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project")
@SecurityRequirement(name = "Bearer Authentication")
public class ProjectController {
    ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> create(@RequestBody ProjectDTO dto) throws CustomException {
        ProjectModel project = projectService.create(dto.transformToProjectModel());
        ProjectResponseDTO response = ProjectResponseDTO.transformToProjectResponseDTO(project);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> listAll() {
        List<ProjectModel> project = projectService.listAll();
        List<ProjectResponseDTO> response = project.stream().map(ProjectResponseDTO::transformToProjectResponseDTO).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> findById(@PathVariable UUID id) {
        ProjectModel project = projectService.getById(id);
        ProjectResponseDTO response = ProjectResponseDTO.transformToProjectResponseDTO(project);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> update(@PathVariable UUID id, @RequestBody ProjectDTO dto) throws CustomException {
        ProjectModel project = projectService.getById(id);
        project.setName(dto.getName());
        project.setStatus(dto.getStatus());
        project.setSize(dto.getSize());

        projectService.create(project);
        ProjectResponseDTO response = ProjectResponseDTO.transformToProjectResponseDTO(project);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
