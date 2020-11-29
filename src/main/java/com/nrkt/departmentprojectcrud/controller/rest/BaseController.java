package com.nrkt.departmentprojectcrud.controller.rest;

import com.nrkt.departmentprojectcrud.dto.response.enums.PageSort;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface BaseController<T, K> {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(code = 500, message = "Internal Server Error")
    PagedModel<T> getEntityList(Integer page, Integer size, PageSort pageShort);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created!"),
            @ApiResponse(code = 404, message = "Not Found!"),
            @ApiResponse(code = 400, message = "Validate Error!"),
            @ApiResponse(code = 500, message = "Internal Server Error!")
    })
    T createEntity(
            @NotNull
            @RequestBody @Valid K request);


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found!"),
            @ApiResponse(code = 400, message = "Validate Error!"),
            @ApiResponse(code = 500, message = "Internal Server Error!")
    })
    T editEntity(@NotNull
                 @PathVariable Long id,
                 @NotNull
                 @RequestBody @Valid K request);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found!"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    T getEntity(
            @NotNull
            @PathVariable Long id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found!"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    void deleteEntity(@NotNull @PathVariable Long id);
}