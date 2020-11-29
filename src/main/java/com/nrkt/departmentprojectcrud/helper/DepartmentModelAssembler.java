package com.nrkt.departmentprojectcrud.helper;

import com.nrkt.departmentprojectcrud.controller.rest.DepartmentController;
import com.nrkt.departmentprojectcrud.controller.rest.EmployeeController;
import com.nrkt.departmentprojectcrud.controller.rest.LocationController;
import com.nrkt.departmentprojectcrud.controller.web.WebController;
import com.nrkt.departmentprojectcrud.dto.request.DepartmentRequest;
import com.nrkt.departmentprojectcrud.dto.response.DepartmentResponse;
import com.nrkt.departmentprojectcrud.dto.response.enums.PageSort;
import com.nrkt.departmentprojectcrud.mapper.DepartmentMapper;
import com.nrkt.departmentprojectcrud.model.Department;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DepartmentModelAssembler extends RepresentationModelAssemblerSupport<Department, DepartmentResponse> {

    private final DepartmentMapper departmentMapper;

    public DepartmentModelAssembler(DepartmentMapper departmentMapper) {
        super(WebController.class, DepartmentResponse.class);
        this.departmentMapper = departmentMapper;
    }

    @Override
    public DepartmentResponse toModel(Department entity) {

        DepartmentResponse departmentResponse = departmentMapper.departmentEntityToDepartmentResponse(entity);

        Link[] links = new Link[]{
                linkTo(methodOn(DepartmentController.class).createEntity(null))
                        .withRel("department")
                        .withType("POST")
                        .withDeprecation("Add Department"),
                linkTo(methodOn(DepartmentController.class).editEntity(entity.getId(), null))
                        .withRel("department")
                        .withType("PUT")
                        .withDeprecation("Edit Department"),
                linkTo(methodOn(DepartmentController.class).getEntity(entity.getId()))
                        .withRel("department")
                        .withType("GET")
                        .withDeprecation("Get Department"),
                linkTo(methodOn(DepartmentController.class).getEntityList(0, 5, PageSort.ASC))
                        .withRel("department")
                        .withType("GET")
                        .withTitle("Departments")
                        .withDeprecation("Get All Department List"),
                linkTo(methodOn(EmployeeController.class).createEntity(null))
                        .withSelfRel()
                        .withType("POST")
                        .withDeprecation("Add Employee"),
                linkTo(methodOn(LocationController.class).createLocation(null, null))
                        .withSelfRel()
                        .withType("POST")
                        .withDeprecation("Add Location")
        };

        departmentResponse.add(links);

        return departmentResponse;
    }

    public Department toDepartmentEntity(DepartmentRequest departmentRequest){
        return departmentMapper.departmentRequestToDepartmentEntity(departmentRequest);
    }

    public List<DepartmentResponse> toDepartmentResponseList(List<Department> departmentList){
        return departmentMapper.departmentEntityToDepartmentResponseList(departmentList);
    }
}
