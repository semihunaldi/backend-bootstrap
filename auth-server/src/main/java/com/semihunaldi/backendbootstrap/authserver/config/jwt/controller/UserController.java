package com.semihunaldi.backendbootstrap.authserver.config.jwt.controller;

import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.UserIdentityAvailability;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.UserProfile;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.UserSummary;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.exception.ResourceNotFoundException;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.security.UserPrincipal;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.security.UserRepository;
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
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@AuthenticationPrincipal UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUserName(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return new UserProfile(user.getId(), user.getUserName(), user.getName(), user.getCreateTime());
    }
}
