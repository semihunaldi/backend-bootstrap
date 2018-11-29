package com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model;

import com.semihunaldi.backendbootstrap.exceptionhandling.model.BaseResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIdentityAvailability extends BaseResult {
    private Boolean available;
}
