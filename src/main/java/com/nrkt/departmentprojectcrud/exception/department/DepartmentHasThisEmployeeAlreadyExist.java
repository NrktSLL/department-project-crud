package com.nrkt.departmentprojectcrud.exception.department;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "The department already has this employee!")
public class DepartmentHasThisEmployeeAlreadyExist extends RuntimeException {
}
