package com.nrkt.departmentprojectcrud.boostrap;

import com.nrkt.departmentprojectcrud.model.*;
import com.nrkt.departmentprojectcrud.model.enums.AddressType;
import com.nrkt.departmentprojectcrud.model.enums.TaskStatus;
import com.nrkt.departmentprojectcrud.model.other.EmployeeToProjectKey;
import com.nrkt.departmentprojectcrud.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DataLoader implements CommandLineRunner {

    DepartmentRepository departmentRepository;
    EmployeeRepository employeeRepository;
    ProjectRepository projectRepository;
    TaskRepository taskRepository;
    EmployeeToProjectRepository employeeToProjectRepository;
    LocationRepository locationRepository;

    @Override
    public void run(String... args) {

        var department = Department.builder()
                .name("It")
                .build();

        departmentRepository.save(department);

        var location = Location.builder()
                .addressType(AddressType.HOME)
                .city("Ankara")
                .street("Test")
                .postalCode(5000)
                .build();

        locationRepository.save(location);

        var employee = Employee.builder()
                .email("bb@gmail.com")
                .firstName("Ali")
                .hiredDate(new Date())
                .lastName("SLL")
                .secondName("SS")
                .department(department)
                .location(location)
                .build();

        employeeRepository.save(employee);

        var project = Project.builder()
                .jobTitle("Test")
                .salary(100D)
                .status(true)
                .build();

        projectRepository.save(project);

        var projectToEmployee = EmployeeToProject.builder()
                .id(new EmployeeToProjectKey(employee.getId(), project.getId()))
                .employee(employee)
                .project(project)
                .date(new Date())
                .build();

        employeeToProjectRepository.save(projectToEmployee);

        List<Task> tasks = new ArrayList<>();

        var task1 = Task.builder()
                .title("Test")
                .taskStatus(TaskStatus.VALID)
                .project(project)
                .build();

        var task2 = Task.builder()
                .title("Test2")
                .taskStatus(TaskStatus.PENDING)
                .project(project)
                .build();

        tasks.add(task2);
        tasks.add(task1);

        if (!tasks.isEmpty()) taskRepository.saveAll(tasks);
    }
}
