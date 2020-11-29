package com.nrkt.departmentprojectcrud.dto.request;

import com.nrkt.departmentprojectcrud.model.enums.TaskStatus;
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
@ApiModel(value = "Task Request")
public class TaskRequest {

    @NotBlank
    @ApiModelProperty(notes = "Task Title", example = "TODO", required = true)
    String title;

    @ApiModelProperty(notes = "Task Description")
    String description;

    @ApiModelProperty(dataType = "string", notes = "Task Type", required = true, allowableValues = "VALID, PENDING, TERMINATION")
    TaskStatus taskStatus;
}





