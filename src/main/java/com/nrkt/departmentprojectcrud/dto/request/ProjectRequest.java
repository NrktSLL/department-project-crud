package com.nrkt.departmentprojectcrud.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ApiModel(value = "Project Request")
public class ProjectRequest {

    @NotBlank
    @ApiModelProperty(notes = "Job Title", example = "Test", required = true)
    String jobTitle;
    @ApiModelProperty(notes = "Salary", example = "100")
    Double salary;
    @ApiModelProperty(notes = "Status", example = "true")
    Boolean status;

    @ApiModelProperty(notes = "employeeId", example = "1")
    Long employeeId;

    List<TaskRequest> taskRequests = new ArrayList<>();
}
