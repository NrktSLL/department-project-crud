package com.nrkt.departmentprojectcrud.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nrkt.departmentprojectcrud.dto.response.base.BaseResponse;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Builder
@ApiModel(value = "Project Response")
@Relation(collectionRelation = "Projects")
public class ProjectResponse extends BaseResponse {

    @NotBlank
    String jobTitle;

    Double salary;
    Boolean status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    EmployeeResponse employee;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    transient Set<TaskResponse> tasks;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    transient Set<ProjectResponse> projectResponses;
}
