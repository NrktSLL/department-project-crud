package com.nrkt.departmentprojectcrud.controller.rest;

import com.nrkt.departmentprojectcrud.dto.request.LocationRequest;
import com.nrkt.departmentprojectcrud.dto.response.LocationResponse;
import com.nrkt.departmentprojectcrud.model.enums.AddressType;
import com.nrkt.departmentprojectcrud.service.location.LocationService;
import io.swagger.annotations.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = "/v1/locations")
@Api(tags = "Location")
public class LocationController{

    LocationService locationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "New Location", notes = "Add New Location")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Location Created!"),
            @ApiResponse(code = 404, message = "Location Not Found!"),
            @ApiResponse(code = 400, message = "Validate Error!"),
            @ApiResponse(code = 500, message = "Internal Server Error!")
    })
    public LocationResponse createLocation(
            @NotNull
            @ApiParam(value = "Address Type", required = true, allowableValues = "HOME, BUSINESS, OTHER")
            @Valid @RequestParam(value = "Address Type") AddressType addressType,
            @NotNull
            @ApiParam(name = "location", value = "Location Specifications", required = true)
            @Valid @RequestBody LocationRequest locationRequest) {

        return locationService.addLocation(locationRequest, addressType);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Update Location")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Location Not Found!"),
            @ApiResponse(code = 400, message = "Validate Error!"),
            @ApiResponse(code = 500, message = "Internal Server Error!")
    })
    public LocationResponse editLocation(
            @NotNull
            @ApiParam(value = "Address Type", required = true, allowableValues = "HOME, BUSINESS, OTHER")
            @Valid @RequestParam(value = "AddressType") AddressType addressType,
            @NotNull
            @PathVariable long id,
            @NotNull
            @ApiParam(value = "Location Specifications", required = true)
            @RequestBody @Valid LocationRequest locationRequest) {

        return locationService.updateLocation(id, locationRequest, addressType);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete Location")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Location Not Found!"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public void deleteLocation(@NotNull @PathVariable long id) {
        locationService.removeLocation(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Location")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Location Not Found!"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public LocationResponse getLocation(
            @NotNull
            @ApiParam(value = "Existing Location ID", required = true)
            @PathVariable long id) {

        return locationService.getLocation(id);
    }
}
