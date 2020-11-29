package com.nrkt.departmentprojectcrud.helper;

import com.nrkt.departmentprojectcrud.controller.rest.EmployeeController;
import com.nrkt.departmentprojectcrud.controller.rest.LocationController;
import com.nrkt.departmentprojectcrud.controller.web.WebController;
import com.nrkt.departmentprojectcrud.dto.request.LocationRequest;
import com.nrkt.departmentprojectcrud.dto.response.LocationResponse;
import com.nrkt.departmentprojectcrud.mapper.LocationMapper;
import com.nrkt.departmentprojectcrud.model.Location;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LocationModelAssembler extends RepresentationModelAssemblerSupport<Location, LocationResponse> {

    private final LocationMapper locationMapper;

    public LocationModelAssembler(LocationMapper locationMapper) {
        super(WebController.class, LocationResponse.class);
        this.locationMapper = locationMapper;
    }

    @Override
    public LocationResponse toModel(Location entity) {

        LocationResponse locationResponse = locationMapper.locationEntityToLocationResponse(entity);

        Link[] links = new Link[]{
                linkTo(methodOn(LocationController.class).createLocation(null, null))
                        .withRel("location")
                        .withType("POST")
                        .withDeprecation("Add Location"),
                linkTo(methodOn(LocationController.class).editLocation(null, entity.getId(), null))
                        .withRel("location")
                        .withType("PUT")
                        .withDeprecation("Edit Location"),
                linkTo(methodOn(LocationController.class).getLocation(entity.getId()))
                        .withRel("location")
                        .withType("GET"),
                linkTo(methodOn(EmployeeController.class).createEntity(null))
                        .withSelfRel()
                        .withType("POST")
                        .withDeprecation("Add Employee")
        };

        locationResponse.add(links);

        return locationResponse;
    }

    public Location toEmployeeEntity(LocationRequest locationRequest) {
        return locationMapper.locationRequestToLocationEntity(locationRequest);
    }

    public List<LocationResponse> toEmployeeResponseList(List<Location> locations) {
        return locationMapper.employeeEntityToEmployeeResponseList(locations);
    }
}
