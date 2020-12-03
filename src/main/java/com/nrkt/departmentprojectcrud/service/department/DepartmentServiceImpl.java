package com.nrkt.departmentprojectcrud.service.department;

import com.nrkt.departmentprojectcrud.dto.request.DepartmentRequest;
import com.nrkt.departmentprojectcrud.dto.response.DepartmentResponse;
import com.nrkt.departmentprojectcrud.exception.department.DepartmentCanNotBeEmpty;
import com.nrkt.departmentprojectcrud.exception.department.DepartmentNotFoundException;
import com.nrkt.departmentprojectcrud.helper.DepartmentModelAssembler;
import com.nrkt.departmentprojectcrud.domain.Department;
import com.nrkt.departmentprojectcrud.domain.Employee;
import com.nrkt.departmentprojectcrud.repository.DepartmentRepository;
import com.nrkt.departmentprojectcrud.repository.EmployeeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;
    EmployeeRepository employeeRepository;

    DepartmentModelAssembler departmentModelAssembler;
    PagedResourcesAssembler<Department> pagedResourcesAssembler;

    @Override
    public DepartmentResponse addDepartment(DepartmentRequest department) {
        Department newDepartment = departmentModelAssembler.toDepartmentEntity(department);
        newDepartment = departmentRepository.save(newDepartment);

        return departmentModelAssembler.toModel(newDepartment);
    }

    @Override
    public List<DepartmentResponse> addDepartments(List<DepartmentRequest> departments) {
        if (departments.isEmpty()) throw new DepartmentCanNotBeEmpty();

        List<Department> departmentList = departments
                .stream()
                .map(departmentModelAssembler::toDepartmentEntity)
                .collect(Collectors.toList());
        departmentList = departmentRepository.saveAll(departmentList);

        return departmentModelAssembler.toDepartmentResponseList(departmentList);
    }

    @Override
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest department) {
        var existDepartment = departmentRepository
                .findById(id)
                .orElseThrow(DepartmentNotFoundException::new);
        existDepartment = Department.builder().name(department.getName()).build();
        existDepartment = departmentRepository.save(existDepartment);

        return departmentModelAssembler.toModel(existDepartment);
    }

    @Override
    public void removeDepartment(Long id) {
        var existDepartment = departmentRepository
                .findById(id)
                .orElseThrow(DepartmentNotFoundException::new);

        List<Employee> employeeList = new ArrayList<>();

        var employeeInDepartmentList = employeeRepository
                .findByDepartmentId(existDepartment.getId());

        for (Employee employee : employeeInDepartmentList) {
            employee.setDepartment(null);
            employeeList.add(employee);
        }

        employeeRepository.saveAll(employeeList);

        departmentRepository.delete(existDepartment);
    }

    @Override
    public DepartmentResponse getDepartment(Long id) {
        Department department = departmentRepository
                .findById(id)
                .orElseThrow(DepartmentNotFoundException::new);

        return departmentModelAssembler.toModel(department);
    }

    @Override
    public PagedModel<DepartmentResponse> getAllDepartment(Pageable pageable) {

        var departmentPage = departmentRepository.findAll(pageable);

        return pagedResourcesAssembler.toModel(departmentPage, departmentModelAssembler);
    }

    @Override
    public DepartmentResponse bringDepartmentByName(String name) {
        var department = departmentRepository
                .findByName(name)
                .orElseThrow(DepartmentNotFoundException::new);
        return departmentModelAssembler.toModel(department);
    }
}
