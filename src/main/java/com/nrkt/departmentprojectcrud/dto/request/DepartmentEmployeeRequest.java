package com.nrkt.departmentprojectcrud.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "Department Employee Request", description = "Adding Employees to the Department")
public class DepartmentEmployeeRequest {

    @ApiModelProperty(notes = "Department ID ", example = "1", required = true, position = 1)
    Long departmentId;

    @ApiModelProperty(notes = "Employee ID ", example = "1", required = true, position = 2)
    Long employeeId;
}
