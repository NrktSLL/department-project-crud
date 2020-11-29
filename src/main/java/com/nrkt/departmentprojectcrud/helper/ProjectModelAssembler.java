package com.nrkt.departmentprojectcrud.helper;

import com.nrkt.departmentprojectcrud.controller.rest.EmployeeController;
import com.nrkt.departmentprojectcrud.controller.rest.LocationController;
import com.nrkt.departmentprojectcrud.controller.rest.ProjectController;
import com.nrkt.departmentprojectcrud.controller.web.WebController;
import com.nrkt.departmentprojectcrud.dto.request.ProjectRequest;
import com.nrkt.departmentprojectcrud.dto.response.ProjectResponse;
import com.nrkt.departmentprojectcrud.dto.response.enums.PageSort;
import com.nrkt.departmentprojectcrud.dto.response.enums.ProjectStatus;
import com.nrkt.departmentprojectcrud.mapper.ProjectMapper;
import com.nrkt.departmentprojectcrud.model.Project;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjectModelAssembler extends RepresentationModelAssemblerSupport<Project, ProjectResponse> {

    private final ProjectMapper projectMapper;

    public ProjectModelAssembler(ProjectMapper projectMapper) {
        super(WebController.class, ProjectResponse.class);
        this.projectMapper = projectMapper;
    }

    @Override
    public ProjectResponse toModel(Project entity) {

        ProjectResponse projectResponse = projectMapper.projectEntityToProjectResponse(entity);

        Link[] links = new Link[]{
                linkTo(methodOn(ProjectController.class).createEntity(null))
                        .withRel("project")
                        .withType("POST")
                        .withDeprecation("Add Project"),
                linkTo(methodOn(ProjectController.class).getEntity(entity.getId()))
                        .withRel("project")
                        .withType("GET")
                        .withDeprecation("GET Project"),
                linkTo(methodOn(ProjectController.class).getEntityList(0, 5, PageSort.ASC))
                        .withRel("project")
                        .withType("GET")
                        .withTitle("Projects")
                        .withDeprecation("GET All Project List"),
                linkTo(methodOn(ProjectController.class).getProjectByStatus(ProjectStatus.ACTIVE, 0, 5, PageSort.ASC))
                        .withRel("project")
                        .withType("GET")
                        .withTitle("Projects")
                        .withDeprecation("GET All Project List By Status"),
                linkTo(methodOn(EmployeeController.class).createEntity(null))
                        .withSelfRel()
                        .withType("POST")
                        .withDeprecation("Add Employee"),
                linkTo(methodOn(LocationController.class).createLocation(null, null))
                        .withSelfRel()
                        .withType("POST")
                        .withDeprecation("Add Location")
        };

        projectResponse.add(links);

        return projectResponse;
    }

    public Project toEmployeeEntity(ProjectRequest locationRequest) {
        return projectMapper.projectRequestToProjectEntity(locationRequest);
    }

    public List<ProjectResponse> toEmployeeResponseList(List<Project> projects) {
        return projectMapper.projectEntityToProjectResponseList(projects);
    }

    public List<Project> toProject(List<ProjectResponse> projectResponses) {
        return projectMapper.projectResponsesToProjectEntity(projectResponses);
    }
}
