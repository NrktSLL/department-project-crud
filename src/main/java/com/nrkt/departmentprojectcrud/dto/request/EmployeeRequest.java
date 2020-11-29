package com.nrkt.departmentprojectcrud.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "Employee Request")
public class EmployeeRequest {

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

    @ApiModelProperty(notes = "Employee phone number", required = true)
    String phone;

    Long locationId;

    Long departmentId;
}
