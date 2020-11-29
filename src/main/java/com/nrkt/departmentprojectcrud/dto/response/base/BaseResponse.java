package com.nrkt.departmentprojectcrud.dto.response.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class BaseResponse extends RepresentationModel<BaseResponse> implements Serializable{

    @ApiModelProperty(notes = "Id", required = true)
    @EqualsAndHashCode.Include
    Long id;

}
