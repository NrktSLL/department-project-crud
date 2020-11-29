package com.nrkt.departmentprojectcrud.exception.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Project not found!")
public class ProjectNotFoundException extends RuntimeException {

}
