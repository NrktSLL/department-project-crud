package com.nrkt.departmentprojectcrud.exception.location;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException() {
        super("Location not found!");
    }
}
