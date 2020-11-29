package com.nrkt.departmentprojectcrud.repository;

import com.nrkt.departmentprojectcrud.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    //@Query("SELECT project FROM Project project WHERE project.status = :status")
    Page<Project> findByStatus(@Param("status") boolean status, Pageable pageable);
}
