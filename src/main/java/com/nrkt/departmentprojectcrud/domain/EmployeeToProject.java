package com.nrkt.departmentprojectcrud.domain;

import com.nrkt.departmentprojectcrud.domain.other.EmployeeToProjectKey;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Employee_Project")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EmployeeToProject implements Serializable {

    @EmbeddedId
    EmployeeToProjectKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectId")
    @JoinColumn(name = "project_Id")
    Project project;

    Date date;
}
