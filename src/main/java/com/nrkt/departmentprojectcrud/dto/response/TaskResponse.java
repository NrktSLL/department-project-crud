package com.nrkt.departmentprojectcrud.dto.response;

import com.nrkt.departmentprojectcrud.dto.response.base.BaseResponse;
import com.nrkt.departmentprojectcrud.model.enums.TaskStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Builder
@ApiModel(value = "Task Response")
@Relation(collectionRelation = "Tasks")
public class TaskResponse extends BaseResponse {

    @NotBlank
    @ApiModelProperty(notes = "Task Title", example = "TODO", required = true)
    String title;

    @ApiModelProperty(notes = "Task Description")
    String description;

    @ApiModelProperty(dataType = "string", notes = "Task Type", required = true, allowableValues = "VALID, PENDING, TERMINATION")
    TaskStatus taskStatus;
}





