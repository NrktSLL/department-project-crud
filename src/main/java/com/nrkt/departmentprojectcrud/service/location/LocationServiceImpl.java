package com.nrkt.departmentprojectcrud.service.location;

import com.nrkt.departmentprojectcrud.dto.request.LocationRequest;
import com.nrkt.departmentprojectcrud.dto.response.LocationResponse;
import com.nrkt.departmentprojectcrud.exception.location.LocationNotFoundException;
import com.nrkt.departmentprojectcrud.helper.LocationModelAssembler;
import com.nrkt.departmentprojectcrud.domain.Location;
import com.nrkt.departmentprojectcrud.domain.enums.AddressType;
import com.nrkt.departmentprojectcrud.repository.LocationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class LocationServiceImpl implements LocationService {

    LocationRepository locationRepository;

    LocationModelAssembler locationModelAssembler;
    PagedResourcesAssembler<Location> pagedResourcesAssembler;

    @Override
    public LocationResponse addLocation(LocationRequest location, AddressType addressType) {
        var newLocation = locationModelAssembler.toEmployeeEntity(location);
        newLocation.setAddressType(addressType);
        newLocation = locationRepository.save(newLocation);

        return locationModelAssembler.toModel(newLocation);
    }

    @Override
    public LocationResponse updateLocation(Long id, LocationRequest location, AddressType addressType) {
        var exitLocation = locationModelAssembler.toEmployeeEntity(location);
        exitLocation = Location.builder()
                .street(location.getStreet())
                .postalCode(location.getPostalCode())
                .city(location.getCity())
                .addressType(addressType)
                .build();
        exitLocation = locationRepository.save(exitLocation);

        return locationModelAssembler.toModel(exitLocation);
    }

    @Override
    public void removeLocation(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public LocationResponse getLocation(Long id) {
        Location location = locationRepository
                .findById(id)
                .orElseThrow(LocationNotFoundException::new);

        return locationModelAssembler.toModel(location);
    }

    @Override
    public List<LocationResponse> getAllLocation() {
        return locationRepository.findAll()
                .stream()
                .map(locationModelAssembler::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public PagedModel<LocationResponse> getAllLocationWithPage(Pageable pageable) {
        var locationPage = locationRepository.findAll(pageable);

        return pagedResourcesAssembler.toModel(locationPage, locationModelAssembler);
    }
}
