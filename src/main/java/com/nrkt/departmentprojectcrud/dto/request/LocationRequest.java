package com.nrkt.departmentprojectcrud.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "Location Request")
public class LocationRequest {

    @NotBlank
    @ApiModelProperty(notes = "Street", example = "Hisar", required = true)
    String street;

    @NotNull
    @ApiModelProperty(notes = "PostalCode", example = "58000", required = true)
    Integer postalCode;

    @NotNull
    @ApiModelProperty(notes = "City", example = "Sivas", required = true)
    String city;
}
