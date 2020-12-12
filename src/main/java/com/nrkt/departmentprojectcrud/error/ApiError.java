package com.nrkt.departmentprojectcrud.error;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NonNull
public class ApiError {
  String message;
  Date timestamp;
  Integer status;
  String error;
}
