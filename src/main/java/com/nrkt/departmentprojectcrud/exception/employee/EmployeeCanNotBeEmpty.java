package com.nrkt.departmentprojectcrud.exception.employee;

public class EmployeeCanNotBeEmpty extends RuntimeException {
    public EmployeeCanNotBeEmpty() {
        super("Employee can not be empty!");
    }
}
