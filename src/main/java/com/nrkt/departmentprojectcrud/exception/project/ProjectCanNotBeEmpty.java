package com.nrkt.departmentprojectcrud.exception.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Project can not be empty!")
public class ProjectCanNotBeEmpty extends RuntimeException {

}
