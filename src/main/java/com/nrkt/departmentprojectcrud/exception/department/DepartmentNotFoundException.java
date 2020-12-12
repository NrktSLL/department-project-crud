package com.nrkt.departmentprojectcrud.exception.department;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException() {
        super("Department not found!");
    }
}
