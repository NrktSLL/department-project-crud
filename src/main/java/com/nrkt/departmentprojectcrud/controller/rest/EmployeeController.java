package com.nrkt.departmentprojectcrud.controller.rest;

import com.nrkt.departmentprojectcrud.dto.request.EmployeeRequest;
import com.nrkt.departmentprojectcrud.dto.response.EmployeeResponse;
import com.nrkt.departmentprojectcrud.dto.response.enums.PageSort;
import com.nrkt.departmentprojectcrud.exception.location.LocationEmpty;
import com.nrkt.departmentprojectcrud.domain.Employee;
import com.nrkt.departmentprojectcrud.service.employee.EmployeeService;
import com.nrkt.departmentprojectcrud.service.location.LocationService;
import io.swagger.annotations.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = "/v1/employees")
@Api(tags = "Employee")
public class EmployeeController implements BaseController<EmployeeResponse, EmployeeRequest> {

    EmployeeService employeeService;
    LocationService locationService;

    @Override
    @ApiOperation("Get All Department")
    public PagedModel<EmployeeResponse> getEntityList(
            @ApiParam(value = "Page Number")
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @ApiParam(value = "Size")
            @RequestParam(defaultValue = "5", required = false) Integer size,
            @ApiParam(value = "Page Sorting Type", allowableValues = "ASC, DESC")
            @RequestParam(defaultValue = "ASC", required = false) PageSort pageShort) {

        Pageable sorted = pageShort.equals(PageSort.DESC) ?
                PageRequest.of(page, size, Sort.sort(Employee.class).descending()) :
                PageRequest.of(page, size, Sort.sort(Employee.class).ascending());

        return employeeService.getAllEmployee(sorted);
    }

    @Override
    @ApiOperation("Add Employee")
    public EmployeeResponse createEntity(
            @ApiParam(name = "Employee", value = "Employee Specifications", required = true)
            @NotNull @RequestBody @Valid EmployeeRequest employeeRequest) {

        if (locationService.getAllLocation().isEmpty()) throw new LocationEmpty();

        return employeeService.addEmployee(employeeRequest);
    }

    @Override
    @ApiOperation("Edit Employee")
    public EmployeeResponse editEntity(
            @NotNull Long id,
            @NotNull @Valid EmployeeRequest request) {

        if (locationService.getAllLocation().isEmpty()) throw new LocationEmpty();

        return employeeService.updateEmployee(id, request);
    }

    @Override
    @ApiOperation("Get Employee")
    public EmployeeResponse getEntity(@NotNull Long id) {
        return employeeService.getEmployee(id);
    }

    @Override
    @ApiOperation("Delete Employee")
    public void deleteEntity(@NotNull Long id) {
        employeeService.removeEmployee(id);
    }

    @GetMapping("/findbyemail")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Bring Employee Information by Mail")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Employee Not Found!"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public EmployeeResponse getEmployeeByMail(
            @NotNull @RequestHeader(value = "email") String mail) {

        return employeeService.findEmployeeByEmail(mail);
    }
}
