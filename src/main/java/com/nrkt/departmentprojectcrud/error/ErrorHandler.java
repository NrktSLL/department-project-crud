package com.nrkt.departmentprojectcrud.error;

import com.nrkt.departmentprojectcrud.exception.department.DepartmentCanNotBeEmpty;
import com.nrkt.departmentprojectcrud.exception.department.DepartmentNotFoundException;
import com.nrkt.departmentprojectcrud.exception.employee.EmployeeCanNotBeEmpty;
import com.nrkt.departmentprojectcrud.exception.employee.EmployeeNotFoundException;
import com.nrkt.departmentprojectcrud.exception.location.LocationEmpty;
import com.nrkt.departmentprojectcrud.exception.location.LocationNotFoundException;
import com.nrkt.departmentprojectcrud.exception.project.ProjectNotFoundException;
import com.nrkt.departmentprojectcrud.exception.task.TaskCanNotBeEmpty;
import com.nrkt.departmentprojectcrud.exception.task.TaskNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex) {
        return new ResponseEntity<>(getBody(BAD_REQUEST, ex, "Please enter a valid value"), new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(getBody(BAD_REQUEST, ex, ex.getMessage()), new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(getBody(BAD_REQUEST, ex, ex.getMessage()), new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler({
            DepartmentNotFoundException.class,
            EmployeeNotFoundException.class,
            LocationNotFoundException.class,
            ProjectNotFoundException.class,
            TaskNotFoundException.class
    })
    public ResponseEntity<Object> handleCustomNotFoundExceptions(Exception ex) {
        return new ResponseEntity<>(getBody(NOT_FOUND, ex, ex.getMessage()), new HttpHeaders(), NOT_FOUND);
    }

    @ExceptionHandler({
            DepartmentCanNotBeEmpty.class,
            EmployeeCanNotBeEmpty.class,
            LocationEmpty.class,
            TaskCanNotBeEmpty.class
    })
    public ResponseEntity<Object> handleCustomBadRequestExceptions(Exception ex) {
        return new ResponseEntity<>(getBody(BAD_REQUEST, ex, ex.getMessage()), new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception ex) {
        return new ResponseEntity<>(getBody(INTERNAL_SERVER_ERROR, ex, "Something Went Wrong"), new HttpHeaders(), INTERNAL_SERVER_ERROR);
    }

    private Map<String, ApiError> getBody(HttpStatus status, Exception ex, String message) {

        log.error(message, ex);

        var errorDetail = ApiError.builder()
                .message(ex.getMessage())
                .status(status.value())
                .timestamp(new Date())
                .error(status.getReasonPhrase())
                .build();

        Map<String, ApiError> responseBody = new LinkedHashMap<>();
        responseBody.put("error:", errorDetail);

        return responseBody;
    }
}