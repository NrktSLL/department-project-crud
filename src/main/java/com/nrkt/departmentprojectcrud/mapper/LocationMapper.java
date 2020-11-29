package com.nrkt.departmentprojectcrud.mapper;

import com.nrkt.departmentprojectcrud.dto.request.LocationRequest;
import com.nrkt.departmentprojectcrud.dto.response.LocationResponse;
import com.nrkt.departmentprojectcrud.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationResponse locationEntityToLocationResponse(Location location);

    @Mapping(target = "addressType", ignore = true)
    Location locationRequestToLocationEntity(LocationRequest locationRequest);

    List<LocationResponse> employeeEntityToEmployeeResponseList(List<Location> locations);
}
