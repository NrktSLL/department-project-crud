package com.nrkt.departmentprojectcrud.helper;

import com.nrkt.departmentprojectcrud.controller.rest.DepartmentController;
import com.nrkt.departmentprojectcrud.controller.rest.EmployeeController;
import com.nrkt.departmentprojectcrud.controller.rest.LocationController;
import com.nrkt.departmentprojectcrud.controller.web.WebController;
import com.nrkt.departmentprojectcrud.dto.request.EmployeeRequest;
import com.nrkt.departmentprojectcrud.dto.response.EmployeeResponse;
import com.nrkt.departmentprojectcrud.dto.response.enums.PageSort;
import com.nrkt.departmentprojectcrud.mapper.EmployeeMapper;
import com.nrkt.departmentprojectcrud.domain.Employee;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeModelAssembler extends RepresentationModelAssemblerSupport<Employee, EmployeeResponse> {

    private final EmployeeMapper employeeMapper;

    public EmployeeModelAssembler(EmployeeMapper employeeMapper) {
        super(WebController.class, EmployeeResponse.class);
        this.employeeMapper = employeeMapper;
    }

    @Override
    @NonNull
    public EmployeeResponse toModel(@NonNull Employee entity) {

        EmployeeResponse employeeResponse = employeeMapper.employeeEntityToEmployeeResponse(entity);

        Link[] links = new Link[]{
                linkTo(methodOn(EmployeeController.class).createEntity(null))
                        .withRel("employee")
                        .withType("POST")
                        .withDeprecation("Add Employee"),
                linkTo(methodOn(EmployeeController.class).editEntity(entity.getId(), null))
                        .withRel("employee")
                        .withType("PUT")
                        .withDeprecation("Edit Employee"),
                linkTo(methodOn(EmployeeController.class).getEntity(entity.getId()))
                        .withRel("employee")
                        .withType("GET")
                        .withDeprecation("Get Employee"),
                linkTo(methodOn(EmployeeController.class).getEmployeeByMail(null))
                        .withRel("employee")
                        .withType("GET")
                        .withDeprecation("Get Employee By Email"),
                linkTo(methodOn(EmployeeController.class).getEntityList(0, 5, PageSort.ASC))
                        .withRel("Employee")
                        .withType("GET")
                        .withTitle("Employees")
                        .withDeprecation("Get All Employee List"),
                linkTo(methodOn(LocationController.class).createLocation(null, null))
                        .withSelfRel()
                        .withType("POST")
                        .withDeprecation("Add Location"),
                linkTo(methodOn(DepartmentController.class).createEntity(null))
                        .withSelfRel()
                        .withType("POST")
                        .withDeprecation("Add Department")
        };

        employeeResponse.add(links);

        return employeeResponse;
    }

    public Employee toEmployeeEntity(EmployeeRequest employeeRequest) {
        return employeeMapper.employeeRequestToEmployeeEntity(employeeRequest);
    }

    public List<EmployeeResponse> toEmployeeResponseList(List<Employee> employeeList) {
        return employeeMapper.employeeEntityToEmployeeResponseList(employeeList);
    }
}
