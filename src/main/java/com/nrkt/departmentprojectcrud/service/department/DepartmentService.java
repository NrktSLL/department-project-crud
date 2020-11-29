package com.nrkt.departmentprojectcrud.service.department;

import com.nrkt.departmentprojectcrud.dto.request.DepartmentRequest;
import com.nrkt.departmentprojectcrud.dto.response.DepartmentResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse addDepartment(DepartmentRequest department);

    List<DepartmentResponse> addDepartments(List<DepartmentRequest> department);

    DepartmentResponse updateDepartment(Long id, DepartmentRequest department);

    void removeDepartment(Long id);

    DepartmentResponse getDepartment(Long id);

    PagedModel<DepartmentResponse> getAllDepartment(Pageable pageable);

    DepartmentResponse bringDepartmentByName(String name);

}