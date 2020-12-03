package com.nrkt.departmentprojectcrud.controller.rest;

import com.nrkt.departmentprojectcrud.dto.request.ProjectRequest;
import com.nrkt.departmentprojectcrud.dto.response.ProjectResponse;
import com.nrkt.departmentprojectcrud.dto.response.enums.PageSort;
import com.nrkt.departmentprojectcrud.dto.response.enums.ProjectStatus;
import com.nrkt.departmentprojectcrud.domain.Employee;
import com.nrkt.departmentprojectcrud.service.project.ProjectService;
import io.swagger.annotations.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = "/v1/projects")
@Api(tags = "Project")
public class ProjectController implements BaseController<ProjectResponse, ProjectRequest> {

    ProjectService projectService;

    @Override
    @ApiOperation(value = "Get All Projects")
    public PagedModel<ProjectResponse> getEntityList(
            @ApiParam(value = "Page Number")
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @ApiParam(value = "Size")
            @RequestParam(defaultValue = "5", required = false) Integer size,
            @ApiParam(value = "Page Sorting Type", allowableValues = "ASC, DESC")
            @RequestParam(defaultValue = "ASC", required = false) PageSort pageShort) {

        Pageable sorted = pageShort.equals(PageSort.DESC) ?
                PageRequest.of(page, size, Sort.sort(Employee.class).descending()) :
                PageRequest.of(page, size, Sort.sort(Employee.class).ascending());

        return projectService.getAllProject(sorted);
    }

    @Override
    @ApiOperation(value = "New Project")
    public ProjectResponse createEntity(
            @NotNull
            @ApiParam(name = "project", value = "Project Specifications", required = true)
            @Valid @RequestBody ProjectRequest projectRequest) {

        return projectService.addProject(projectRequest);
    }

    @Override
    @ApiIgnore
    public ProjectResponse editEntity(@NotNull Long id, @NotNull @Valid ProjectRequest request) {
        return null;
    }

    @Override
    @ApiOperation(value = "Get Project")
    public ProjectResponse getEntity(@NotNull Long id) {
        return projectService.getProject(id);
    }

    @Override
    public void deleteEntity(@NotNull Long id) {
        projectService.removeProject(id);
    }

    @GetMapping("status/{status}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Project by Status")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Project Not Found!"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public PagedModel<ProjectResponse> getProjectByStatus(
            @NotNull
            @ApiParam(value = "status", required = true, allowableValues = "ACTIVE, NONACTIVE")
            @PathVariable ProjectStatus status,
            @ApiParam(value = "Page Number")
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @ApiParam(value = "Size")
            @RequestParam(defaultValue = "5", required = false) Integer size,
            @ApiParam(value = "Page Sorting Type", allowableValues = "ASC, DESC")
            @RequestParam(defaultValue = "ASC", required = false) PageSort pageShort) {

        boolean isActive = false;

        if (status == ProjectStatus.ACTIVE) isActive = true;

        Pageable sorted = pageShort.equals(PageSort.DESC) ?
                PageRequest.of(page, size, Sort.sort(Employee.class).descending()) :
                PageRequest.of(page, size, Sort.sort(Employee.class).ascending());

        return projectService.findProjectStatus(sorted, isActive);
    }
}
