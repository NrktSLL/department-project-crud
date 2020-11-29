package com.nrkt.departmentprojectcrud.model;

import com.nrkt.departmentprojectcrud.model.base.BaseEntity;
import com.nrkt.departmentprojectcrud.model.enums.TaskStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "task")
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Task extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    String title;
    @Column
    String description;
    @Column
    TaskStatus taskStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    Project project;
}





