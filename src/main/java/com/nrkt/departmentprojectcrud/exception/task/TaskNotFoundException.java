package com.nrkt.departmentprojectcrud.exception.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Task not found!")
public class TaskNotFoundException extends RuntimeException {

}
