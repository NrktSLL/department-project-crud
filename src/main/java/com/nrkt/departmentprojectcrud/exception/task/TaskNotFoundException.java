package com.nrkt.departmentprojectcrud.exception.task;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super("Task not found!");
    }
}
