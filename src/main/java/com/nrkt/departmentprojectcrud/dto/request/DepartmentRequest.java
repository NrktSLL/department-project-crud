package com.nrkt.departmentprojectcrud.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "Department Request")
public class DepartmentRequest {

    @NotBlank
    @ApiModelProperty(notes = "Department Name", example = "IT", required = true)
    String name;
}
