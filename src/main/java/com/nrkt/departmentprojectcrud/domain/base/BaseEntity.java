package com.nrkt.departmentprojectcrud.domain.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseEntity implements Serializable {

    @Id
    @GenericGenerator(name = "seq_identity", strategy = "increment")
    @GeneratedValue(generator = "seq_identity")
    @EqualsAndHashCode.Include
    @Column(nullable = false, updatable = false, unique = true)
    Long id;
}
