package com.nrkt.departmentprojectcrud.exception.location;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Location can not be empty!")
public class LocationCanNotBeEmpty extends RuntimeException {

}
