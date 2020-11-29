package com.nrkt.departmentprojectcrud.repository;

import com.nrkt.departmentprojectcrud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String mail);

    List<Employee> findByDepartmentId(Long departmentId);
}
