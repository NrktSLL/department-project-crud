package com.nrkt.departmentprojectcrud.exception.department;

public class DepartmentCanNotBeEmpty extends RuntimeException {
    public DepartmentCanNotBeEmpty() {
        super("Department can not be empty!");
    }
}
