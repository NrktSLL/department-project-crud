package com.nrkt.departmentprojectcrud.exception.project;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException() {
        super("Project not found!");
    }
}
