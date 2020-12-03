package com.nrkt.departmentprojectcrud.mapper;

import com.nrkt.departmentprojectcrud.dto.request.DepartmentRequest;
import com.nrkt.departmentprojectcrud.dto.response.DepartmentResponse;
import com.nrkt.departmentprojectcrud.domain.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentResponse departmentEntityToDepartmentResponse(Department department);

    @Mapping(target = "employees", ignore = true)
    Department departmentRequestToDepartmentEntity(DepartmentRequest departmentRequest);

    List<DepartmentResponse> departmentEntityToDepartmentResponseList(List<Department> departments);
}
