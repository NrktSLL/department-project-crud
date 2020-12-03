package com.nrkt.departmentprojectcrud.domain;

import com.nrkt.departmentprojectcrud.domain.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "department")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class Department extends BaseEntity {

    @NotBlank
    @Column
    String name;

    @ToString.Exclude
    @Singular
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "department",
            fetch = FetchType.LAZY)
    transient Set<Employee> employees;
}
