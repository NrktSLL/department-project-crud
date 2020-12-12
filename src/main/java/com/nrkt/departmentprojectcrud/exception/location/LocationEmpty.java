package com.nrkt.departmentprojectcrud.exception.location;

public class LocationEmpty extends RuntimeException {
    public LocationEmpty() {
        super("Location is empty,location must be add before!");
    }
}
