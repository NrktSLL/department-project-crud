package com.nrkt.departmentprojectcrud.dto.response;

import com.nrkt.departmentprojectcrud.dto.response.base.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "Department Response")
@Relation(collectionRelation = "departments", itemRelation = "department")
@Builder
public class DepartmentResponse extends BaseResponse {
    @NotBlank
    @ApiModelProperty(notes = "Department Name", example = "IT", required = true, position = 1)
    String name;
}
