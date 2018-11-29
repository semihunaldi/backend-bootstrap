package com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model;

import com.semihunaldi.backendbootstrap.exceptionhandling.model.BaseResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse extends BaseResult {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
