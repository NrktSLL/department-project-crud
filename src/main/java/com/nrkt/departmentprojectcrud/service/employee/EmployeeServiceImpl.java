package com.nrkt.departmentprojectcrud.service.employee;

import com.nrkt.departmentprojectcrud.dto.request.EmployeeRequest;
import com.nrkt.departmentprojectcrud.dto.response.EmployeeResponse;
import com.nrkt.departmentprojectcrud.exception.employee.EmployeeCanNotBeEmpty;
import com.nrkt.departmentprojectcrud.exception.employee.EmployeeNotFoundException;
import com.nrkt.departmentprojectcrud.exception.location.LocationNotFoundException;
import com.nrkt.departmentprojectcrud.helper.EmployeeModelAssembler;
import com.nrkt.departmentprojectcrud.model.Employee;
import com.nrkt.departmentprojectcrud.repository.EmployeeRepository;
import com.nrkt.departmentprojectcrud.repository.EmployeeToProjectRepository;
import com.nrkt.departmentprojectcrud.repository.LocationRepository;
import com.nrkt.departmentprojectcrud.repository.ProjectRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;
    LocationRepository locationRepository;
    ProjectRepository projectRepository;
    EmployeeToProjectRepository employeeToProjectRepository;

    EmployeeModelAssembler employeeModelAssembler;
    PagedResourcesAssembler<Employee> pagedResourcesAssembler;

    @Override
    public EmployeeResponse addEmployee(EmployeeRequest employee) {
        var location = locationRepository
                .findById(employee.getLocationId())
                .orElseThrow(EmployeeNotFoundException::new);

        Employee newEmployee = employeeModelAssembler.toEmployeeEntity(employee);
        newEmployee.setLocation(location);

        employeeRepository.save(newEmployee);

        return employeeModelAssembler.toModel(newEmployee);
    }

    @Override
    public List<EmployeeResponse> addEmployees(List<EmployeeRequest> employees) {
        if (employees.isEmpty()) throw new EmployeeCanNotBeEmpty();

        List<Employee> employeeList = employees
                .stream()
                .map(employeeModelAssembler::toEmployeeEntity)
                .collect(Collectors.toList());
        employeeList = employeeRepository.saveAll(employeeList);

        return employeeModelAssembler.toEmployeeResponseList(employeeList);
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employee) {
        var location = locationRepository
                .findById(employee.getLocationId())
                .orElseThrow(LocationNotFoundException::new);

        var existEmployee = employeeRepository
                .findById(id)
                .orElseThrow(EmployeeNotFoundException::new);

        existEmployee = Employee.builder()
                .firstName(employee.getFirstName())
                .secondName(employee.getSecondName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .location(location)
                .hiredDate(new Date())
                .build();

        existEmployee = employeeRepository.save(existEmployee);

        return employeeModelAssembler.toModel(existEmployee);
    }

    @Override
    public void removeEmployee(Long id) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(EmployeeNotFoundException::new);

        var exitProject = employeeToProjectRepository
                .findByEmployeeId(employee.getId())
                .orElseThrow(EmployeeNotFoundException::new);

        var project = projectRepository
                .findById(exitProject.getProject().getId())
                .orElse(null);

        if (project != null) {
            project.setStatus(false);
            projectRepository.save(project);
        }

        employeeToProjectRepository.delete(exitProject);

        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeResponse getEmployee(Long id) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(EmployeeNotFoundException::new);

        return employeeModelAssembler.toModel(employee);
    }

    @Override
    public PagedModel<EmployeeResponse> getAllEmployee(Pageable pageable) {
        var employeePage = employeeRepository.findAll(pageable);

        return pagedResourcesAssembler.toModel(employeePage, employeeModelAssembler);
    }

    @Override
    public EmployeeResponse findEmployeeByEmail(String email) {
        var employee = employeeRepository
                .findByEmail(email)
                .orElseThrow(EmployeeNotFoundException::new);

        return employeeModelAssembler.toModel(employee);
    }

    @Override
    public List<EmployeeResponse> findDepartmentInEmployee(Long departmentId) {
        var employeeList = employeeRepository
                .findByDepartmentId(departmentId);

        return employeeModelAssembler.toEmployeeResponseList(employeeList);
    }
}
