package com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model;

import com.semihunaldi.backendbootstrap.exceptionhandling.model.BaseResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse extends BaseResult {
    private Boolean success;
    private String message;
}
