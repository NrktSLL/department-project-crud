package com.nrkt.departmentprojectcrud.service.project;

import com.nrkt.departmentprojectcrud.dto.request.ProjectRequest;
import com.nrkt.departmentprojectcrud.dto.request.TaskRequest;
import com.nrkt.departmentprojectcrud.dto.response.EmployeeResponse;
import com.nrkt.departmentprojectcrud.dto.response.ProjectResponse;
import com.nrkt.departmentprojectcrud.exception.employee.EmployeeNotFoundException;
import com.nrkt.departmentprojectcrud.exception.project.ProjectNotFoundException;
import com.nrkt.departmentprojectcrud.helper.EmployeeModelAssembler;
import com.nrkt.departmentprojectcrud.helper.ProjectModelAssembler;
import com.nrkt.departmentprojectcrud.mapper.TaskMapper;
import com.nrkt.departmentprojectcrud.model.EmployeeToProject;
import com.nrkt.departmentprojectcrud.model.Project;
import com.nrkt.departmentprojectcrud.model.Task;
import com.nrkt.departmentprojectcrud.model.other.EmployeeToProjectKey;
import com.nrkt.departmentprojectcrud.repository.EmployeeRepository;
import com.nrkt.departmentprojectcrud.repository.EmployeeToProjectRepository;
import com.nrkt.departmentprojectcrud.repository.ProjectRepository;
import com.nrkt.departmentprojectcrud.repository.TaskRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    EmployeeToProjectRepository employeeToProjectRepository;
    EmployeeRepository employeeRepository;
    TaskRepository taskRepository;

    EmployeeModelAssembler employeeModelAssembler;
    ProjectModelAssembler projectModelAssembler;
    PagedResourcesAssembler<Project> pagedResourcesAssembler;

    TaskMapper taskMapper;

    @Override
    public ProjectResponse addProject(ProjectRequest project) {
        var newProject = projectModelAssembler.toEmployeeEntity(project);
        newProject = projectRepository.save(newProject);

        var exitEmployee = employeeRepository
                .findById(project.getEmployeeId())
                .orElseThrow(EmployeeNotFoundException::new);

        var employeeToProject = EmployeeToProject.builder()
                .id(new EmployeeToProjectKey(exitEmployee.getId(), newProject.getId()))
                .project(newProject)
                .employee(exitEmployee)
                .date(new Date())
                .build();

        var response = projectModelAssembler.toModel(newProject);

        employeeToProjectRepository.save(employeeToProject);

        if (!project.getTaskRequests().isEmpty()) {
            List<Task> tasks = new ArrayList<>();

            for (TaskRequest taskRequest : project.getTaskRequests()) {
                Task task = taskMapper.taskRequestToTaskEntity(taskRequest);
                task.setProject(newProject);
                tasks.add(task);
            }

            taskRepository.saveAll(tasks);

            response.setTasks(new HashSet<>(taskMapper.taskEntityToProjectTaskList(tasks)));
        }

        var employeeResponse = employeeModelAssembler.toModel(exitEmployee);

        response.setEmployee(employeeResponse);

        return response;
    }

    //TODO: this method will be written
    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest project) {
        return null;
    }

    @Override
    public void removeProject(Long id) {
        var project = projectRepository
                .findById(id)
                .orElseThrow(ProjectNotFoundException::new);

        var projectToEmployee = employeeToProjectRepository
                .findByIdProjectId(project.getId())
                .orElseThrow(ProjectNotFoundException::new);

        employeeToProjectRepository.delete(projectToEmployee);

        var tasks = taskRepository.findAllByProject(project);
        if (!tasks.isEmpty()) tasks.forEach(taskRepository::delete);

        projectRepository.delete(project);
    }

    @Override
    public ProjectResponse getProject(Long id) {
        var project = projectRepository
                .findById(id)
                .orElseThrow(ProjectNotFoundException::new);

        return getProjectResponse(project);
    }

    @Override
    public PagedModel<ProjectResponse> getAllProject(Pageable pageable) {
        var projects = projectRepository.findAll(pageable);

        return pagedResourcesAssembler.toModel(getProjectResponses(projects), projectModelAssembler);
    }

    @Override
    public PagedModel<ProjectResponse> findProjectStatus(Pageable pageable, Boolean status) {
        var projects = projectRepository.findByStatus(status, pageable);

        return pagedResourcesAssembler.toModel(getProjectResponses(projects), projectModelAssembler);
    }

    private Page<Project> getProjectResponses(Page<Project> projects) {
        List<ProjectResponse> projectResponseList = new ArrayList<>();

        for (Project project : projects) {
            ProjectResponse response = getProjectResponse(project);
            projectResponseList.add(response);
        }

        var a =  new PageImpl<>(projectModelAssembler.toProject(projectResponseList));

        return  a;
    }

    private ProjectResponse getProjectResponse(Project project) {
        var response = new ProjectResponse();
        var employeeResponse = new EmployeeResponse();

        var employeeToProject = employeeToProjectRepository
                .findByIdProjectId(project.getId())
                .orElse(null);

        response = projectModelAssembler.toModel(project);

        if (employeeToProject != null) {
            var employee = employeeRepository
                    .findById(employeeToProject.getId().getEmployeeId())
                    .orElseThrow(EmployeeNotFoundException::new);

            employeeResponse = employeeModelAssembler.toModel(employee);
            var tasks = taskRepository.findAllByProject(project);
            if (!tasks.isEmpty()) response.setTasks(new HashSet<>(taskMapper.taskEntityToProjectTaskList(tasks)));
            response.setEmployee(employeeResponse);
        }

        return response;
    }
}
