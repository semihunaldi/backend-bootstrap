package com.semihunaldi.backendbootstrap.authserver.config.jwt.controller;

import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.UserIdentityAvailability;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.UserProfile;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.UserSummary;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.exception.ResourceNotFoundException;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.security.UserJWTRepository;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.security.UserPrincipal;
import com.semihunaldi.backendbootstrap.entitymodel.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Profile("jwt")
public class UserController {

    @Autowired
    private UserJWTRepository userJWTRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@AuthenticationPrincipal UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "userName") String userName) {
        Boolean isAvailable = !userJWTRepository.existsByUserName(userName);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userJWTRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{userName}")
    public UserProfile getUserProfile(@PathVariable(value = "userName") String userName) {
        User user = userJWTRepository.findByUserName(userName).orElseThrow(() -> new ResourceNotFoundException("User", "userName", userName));
        return UserProfile.builder()
                .id(user.getId())
                .name(user.getName())
                .userName(user.getUserName())
                .joinedAt(user.getCreateTime())
                .email(user.getEmail())
                .mobilePhone(user.getMobilePhone())
                .birthDate(user.getBirthDate())
                .build();
    }
}
