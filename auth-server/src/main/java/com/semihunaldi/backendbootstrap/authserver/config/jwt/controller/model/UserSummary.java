package com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model;

import com.semihunaldi.backendbootstrap.exceptionhandling.model.BaseResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false,of = "id")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSummary extends BaseResult {
    private String id;
    private String username;
    private String name;
}
