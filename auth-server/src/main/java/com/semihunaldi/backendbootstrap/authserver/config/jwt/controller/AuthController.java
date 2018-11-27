package com.semihunaldi.backendbootstrap.authserver.config.jwt.controller;

import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.ApiResponse;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.JwtAuthenticationResponse;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.LoginRequest;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.SignUpRequest;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.exception.AppException;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.security.JwtTokenProvider;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.security.RoleRepository;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.security.UserRepository;
import com.semihunaldi.backendbootstrap.entitymodel.user.Role;
import com.semihunaldi.backendbootstrap.entitymodel.user.RoleName;
import com.semihunaldi.backendbootstrap.entitymodel.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@Profile("jwt")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsernameOrEmail(),
						loginRequest.getPassword()
				)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if(userRepository.existsByUserName(signUpRequest.getUsername())){
			return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if(userRepository.existsByEmail(signUpRequest.getEmail())){
			return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
				signUpRequest.getEmail(), signUpRequest.getPassword());

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new AppException("User Role not set."));

		user.setRoles(Collections.singleton(userRole));

		User result = userRepository.save(user);

		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/users/{username}")
				.buildAndExpand(result.getUserName()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}
}
