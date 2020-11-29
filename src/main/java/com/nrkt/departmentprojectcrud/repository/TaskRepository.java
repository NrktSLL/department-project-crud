package com.nrkt.departmentprojectcrud.repository;

import com.nrkt.departmentprojectcrud.model.Project;
import com.nrkt.departmentprojectcrud.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByProject(Project project);
}
