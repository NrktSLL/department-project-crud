package com.nrkt.departmentprojectcrud.exception.department;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Department not found!")
public class DepartmentNotFoundException extends RuntimeException {

}
