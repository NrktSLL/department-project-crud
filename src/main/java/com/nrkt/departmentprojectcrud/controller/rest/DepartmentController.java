package com.nrkt.departmentprojectcrud.controller.rest;

import com.nrkt.departmentprojectcrud.dto.request.DepartmentRequest;
import com.nrkt.departmentprojectcrud.dto.response.DepartmentResponse;
import com.nrkt.departmentprojectcrud.dto.response.enums.PageSort;
import com.nrkt.departmentprojectcrud.model.Department;
import com.nrkt.departmentprojectcrud.service.department.DepartmentService;
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
@RequestMapping(value = "/v1/departments")
@Api(tags = "Department")
public class DepartmentController implements BaseController<DepartmentResponse, DepartmentRequest> {

    DepartmentService departmentService;

    @Override
    public PagedModel<DepartmentResponse> getEntityList(
            @ApiParam(value = "Page Number")
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @ApiParam(value = "Size")
            @RequestParam(defaultValue = "5", required = false) Integer size,
            @ApiParam(value = "Page Sorting Type", allowableValues = "ASC, DESC")
            @RequestParam(defaultValue = "ASC", required = false) PageSort pageShort) {

        Pageable sorted = pageShort.equals(PageSort.DESC) ?
                PageRequest.of(page, size, Sort.sort(Department.class).descending()) :
                PageRequest.of(page, size, Sort.sort(Department.class).ascending());

       return  departmentService.getAllDepartment(sorted);
    }

    @Override
    @ApiOperation(value = "New Department")
    public DepartmentResponse createEntity(
            @ApiParam(name = "Department", value = "Department Specifications", required = true)
            @NotNull @Valid DepartmentRequest request) {

        return departmentService.addDepartment(request);
    }

    @Override
    @ApiOperation("Edit Department")
    public DepartmentResponse editEntity(
            @NotNull Long id,
            @ApiParam(value = "Department Specifications", required = true)
            @NotNull @Valid DepartmentRequest request) {

        return departmentService.updateDepartment(id, request);
    }

    @Override
    @ApiOperation(value = "Get Department")
    public DepartmentResponse getEntity(@NotNull Long id) {
        return departmentService.getDepartment(id);
    }

    @Override
    @ApiOperation(value = "Delete Department")
    public void deleteEntity(@NotNull Long id) {
        departmentService.removeDepartment(id);
    }

    @GetMapping("department/{department}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Department")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Department Not Found!"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public DepartmentResponse getDepartmentName(
            @NotNull
            @ApiParam(value = "Existing Department Name", required = true)
            @PathVariable String department) {

        return departmentService.bringDepartmentByName(department);
    }
}
