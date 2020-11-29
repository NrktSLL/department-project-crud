package com.nrkt.departmentprojectcrud.mapper;

import com.nrkt.departmentprojectcrud.dto.request.EmployeeRequest;
import com.nrkt.departmentprojectcrud.dto.response.EmployeeResponse;
import com.nrkt.departmentprojectcrud.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeResponse employeeEntityToEmployeeResponse(Employee employee);

    @Mapping(target = "location", ignore = true)
    @Mapping(target = "hiredDate", ignore = true)
    Employee employeeRequestToEmployeeEntity(EmployeeRequest employeeRequest);

    List<EmployeeResponse> employeeEntityToEmployeeResponseList(List<Employee> employees);
}
