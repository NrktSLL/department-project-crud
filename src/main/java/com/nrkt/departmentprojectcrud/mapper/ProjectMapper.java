package com.nrkt.departmentprojectcrud.mapper;

import com.nrkt.departmentprojectcrud.dto.request.ProjectRequest;
import com.nrkt.departmentprojectcrud.dto.response.ProjectResponse;
import com.nrkt.departmentprojectcrud.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "projectResponses", ignore = true)
    @Mapping(target = "employee", ignore = true)
    ProjectResponse projectEntityToProjectResponse(Project project);

    @Mapping(target = "tasks", ignore = true)
    Project projectRequestToProjectEntity(ProjectRequest projectRequest);

    List<ProjectResponse> projectEntityToProjectResponseList(List<Project> tasks);

    List<Project> projectResponsesToProjectEntity(List<ProjectResponse> projectResponses);
}
