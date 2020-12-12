package com.nrkt.departmentprojectcrud.exception.task;

public class TaskCanNotBeEmpty extends RuntimeException {
    public TaskCanNotBeEmpty() {
        super("Task can not be empty!");
    }
}
