package com.nrkt.departmentprojectcrud.service.location;

import com.nrkt.departmentprojectcrud.dto.request.LocationRequest;
import com.nrkt.departmentprojectcrud.dto.response.LocationResponse;
import com.nrkt.departmentprojectcrud.model.enums.AddressType;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.List;

public interface LocationService {

    LocationResponse addLocation(LocationRequest location, AddressType addressType);

    LocationResponse updateLocation(Long id, LocationRequest location, AddressType addressType);

    void removeLocation(Long id);

    LocationResponse getLocation(Long id);

    List<LocationResponse> getAllLocation();

    PagedModel<LocationResponse> getAllLocationWithPage(Pageable pageable);
}
