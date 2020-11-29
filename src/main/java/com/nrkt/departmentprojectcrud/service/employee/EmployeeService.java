package com.nrkt.departmentprojectcrud.service.employee;

import com.nrkt.departmentprojectcrud.dto.request.EmployeeRequest;
import com.nrkt.departmentprojectcrud.dto.response.EmployeeResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse addEmployee(EmployeeRequest employee);

    List<EmployeeResponse> addEmployees(List<EmployeeRequest> employees);

    EmployeeResponse updateEmployee(Long id, EmployeeRequest employee);

    void removeEmployee(Long id);

    EmployeeResponse getEmployee(Long id);

    PagedModel<EmployeeResponse> getAllEmployee(Pageable pageable);

    EmployeeResponse findEmployeeByEmail(String email);

    List<EmployeeResponse> findDepartmentInEmployee(Long departmentId);
}
