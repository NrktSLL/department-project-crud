package com.nrkt.departmentprojectcrud.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@ApiModel(value = "Department Employee Response")
public class DepartmentEmployeeResponse {

    @JsonProperty("department")
    @ApiModelProperty(notes = "Department", required = true, position = 1)
    DepartmentResponse departmentResponse;

    @JsonProperty("employees")
    @ApiModelProperty(notes = "Employees", required = true, position = 1)
    List<EmployeeResponse> employeeResponses;
}
