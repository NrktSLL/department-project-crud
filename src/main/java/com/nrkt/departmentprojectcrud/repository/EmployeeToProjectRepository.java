package com.nrkt.departmentprojectcrud.repository;

import com.nrkt.departmentprojectcrud.domain.EmployeeToProject;
import com.nrkt.departmentprojectcrud.domain.other.EmployeeToProjectKey;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeToProjectRepository extends CrudRepository<EmployeeToProject, EmployeeToProjectKey> {

    Optional<EmployeeToProject> findByEmployeeId(Long id);

    Optional<EmployeeToProject> findByIdProjectId(Long id);
}
