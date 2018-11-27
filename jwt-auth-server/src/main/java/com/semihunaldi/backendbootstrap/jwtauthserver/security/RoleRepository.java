package com.semihunaldi.backendbootstrap.jwtauthserver.security;

import com.semihunaldi.backendbootstrap.entitymodel.user.Role;
import com.semihunaldi.backendbootstrap.entitymodel.user.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
