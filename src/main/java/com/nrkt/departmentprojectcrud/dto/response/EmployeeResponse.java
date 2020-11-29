package com.nrkt.departmentprojectcrud.dto.response;

import com.nrkt.departmentprojectcrud.dto.response.base.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@ApiModel(value = "Employee Response")
@Relation(collectionRelation = "employees", itemRelation = "employee")
public class EmployeeResponse extends BaseResponse {

    @NotBlank
    @ApiModelProperty(notes = "Employee firstname", example = "Ali", required = true)
    String firstName;

    @ApiModelProperty(notes = "Employee second name", example = "Nrkt")
    String secondName;

    @NotBlank
    @ApiModelProperty(notes = "Employee surname", example = "SLL", required = true)
    String lastName;

    @Email
    @ApiModelProperty(notes = "Employee email", example = "aa@bb.com", required = true)
    String email;

    @Pattern(regexp = "[0-9\\s]{10}")
    @ApiModelProperty(notes = "Employee phone number", required = true)
    String phone;

    LocationResponse location;
}
