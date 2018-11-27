package com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private String id;
    private String username;
    private String name;
    private Date joinedAt;
}
