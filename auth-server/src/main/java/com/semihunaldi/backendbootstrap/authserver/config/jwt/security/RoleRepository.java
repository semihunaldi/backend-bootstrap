package com.semihunaldi.backendbootstrap.authserver.config.jwt.security;

import com.semihunaldi.backendbootstrap.entitymodel.user.Role;
import com.semihunaldi.backendbootstrap.entitymodel.user.RoleName;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("jwt")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
