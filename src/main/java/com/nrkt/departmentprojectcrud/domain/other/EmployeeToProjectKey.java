package com.nrkt.departmentprojectcrud.domain.other;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeToProjectKey implements Serializable {

    @Column(name = "employee_id",unique = true)
    Long employeeId;

    @Column(name = "project_id")
    Long projectId;
}
