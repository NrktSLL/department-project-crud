package com.nrkt.departmentprojectcrud.repository;

import com.nrkt.departmentprojectcrud.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByName(String name);
}
