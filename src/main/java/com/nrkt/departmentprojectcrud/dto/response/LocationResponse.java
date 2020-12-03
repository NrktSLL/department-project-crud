package com.nrkt.departmentprojectcrud.dto.response;

import com.nrkt.departmentprojectcrud.dto.response.base.BaseResponse;
import com.nrkt.departmentprojectcrud.domain.enums.AddressType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Builder
@ApiModel(value = "Location Response")
@Relation(collectionRelation = "Locations")
public class LocationResponse extends BaseResponse {

    @NotBlank
    @ApiModelProperty(notes = "Street", required = true)
    String street;

    @NotNull
    @ApiModelProperty(notes = "PostalCode", required = true)
    Integer postalCode;

    @NotNull
    @ApiModelProperty(notes = "City", required = true)
    String city;

    @NotEmpty
    @ApiModelProperty(dataType = "string", notes = "Address Type", required = true, allowableValues = "HOME, BUSINESS, OTHER")
    AddressType addressType;
}
