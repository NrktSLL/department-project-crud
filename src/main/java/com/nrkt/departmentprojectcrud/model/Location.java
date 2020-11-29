package com.nrkt.departmentprojectcrud.model;

import com.nrkt.departmentprojectcrud.model.base.BaseEntity;
import com.nrkt.departmentprojectcrud.model.enums.AddressType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "location")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Location extends BaseEntity {

    @Column
    String street;
    @Column
    Integer postalCode;
    @Column
    String city;
    @Column
    AddressType addressType;
}

