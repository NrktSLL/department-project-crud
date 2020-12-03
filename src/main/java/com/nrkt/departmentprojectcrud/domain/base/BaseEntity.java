package com.nrkt.departmentprojectcrud.domain.base;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false, updatable = false)
    Long id;
}
