package com.nrkt.departmentprojectcrud.domain;

import com.nrkt.departmentprojectcrud.domain.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "project")
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Project extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    String jobTitle;
    @Column
    Double salary;
    @Column(columnDefinition = "tinyint(1) default 1")
    Boolean status;

    @ToString.Exclude
    @Singular
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "project",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    transient Set<Task> tasks;
}
