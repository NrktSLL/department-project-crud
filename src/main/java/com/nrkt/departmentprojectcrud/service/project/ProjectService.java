package com.nrkt.departmentprojectcrud.service.project;

import com.nrkt.departmentprojectcrud.dto.request.ProjectRequest;
import com.nrkt.departmentprojectcrud.dto.response.ProjectResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

public interface ProjectService {

    ProjectResponse addProject(ProjectRequest project);

    ProjectResponse updateProject(Long id, ProjectRequest project);

    void removeProject(Long id);

    ProjectResponse getProject(Long id);

    PagedModel<ProjectResponse> getAllProject(Pageable pageable);

    PagedModel<ProjectResponse> findProjectStatus(Pageable pageable, Boolean status);

}
