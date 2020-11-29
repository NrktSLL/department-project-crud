package com.nrkt.departmentprojectcrud.repository;

import com.nrkt.departmentprojectcrud.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
