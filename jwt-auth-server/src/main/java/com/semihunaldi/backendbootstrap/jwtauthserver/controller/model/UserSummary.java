package com.semihunaldi.backendbootstrap.jwtauthserver.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSummary {
    private String id;
    private String username;
    private String name;
}
