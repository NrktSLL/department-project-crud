package com.nrkt.departmentprojectcrud.domain;

import com.nrkt.departmentprojectcrud.domain.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "employee")
public class Employee extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    String firstName;
    @Column
    String secondName;
    @NotBlank
    @Column(nullable = false)
    String lastName;
    @Column(unique = true, nullable = false)
    @Email
    String email;
    @Column(unique = true)
    @Pattern(regexp = "[0-9\\s]{10}")
    String phone;
    @Column
    @PastOrPresent
    Date hiredDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    Department department;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    @NotNull
    Location location;

}
