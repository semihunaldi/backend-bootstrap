package com.semihunaldi.backendbootstrap.authserver.config.jwt.controller;

import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.JwtAuthenticationResponse;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.LoginRequest;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.SignUpRequest;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.SignUpResponse;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.security.JwtTokenProvider;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.security.RoleRepository;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.security.UserJWTRepository;
import com.semihunaldi.backendbootstrap.entitymodel.exceptions.user.AppException;
import com.semihunaldi.backendbootstrap.entitymodel.exceptions.user.EmailAddressInUseException;
import com.semihunaldi.backendbootstrap.entitymodel.exceptions.user.UserExistsException;
import com.semihunaldi.backendbootstrap.entitymodel.user.Role;
import com.semihunaldi.backendbootstrap.entitymodel.user.RoleName;
import com.semihunaldi.backendbootstrap.entitymodel.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
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
public class AuthController extends BaseRestController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserJWTRepository userJWTRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
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
	@Transactional
	public ResponseEntity<SignUpResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if(userJWTRepository.existsByUserName(signUpRequest.getUserName())){
			throw new UserExistsException();
		}
		if(userJWTRepository.existsByEmail(signUpRequest.getEmail())){
			throw new EmailAddressInUseException();
		}
		Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User Role not set."));
		User user = User.builder()
				.name(signUpRequest.getName())
				.userName(signUpRequest.getUserName())
				.email(signUpRequest.getEmail())
				.mobilePhone(signUpRequest.getMobilePhone())
				.birthDate(signUpRequest.getBirthDate())
				.password(passwordEncoder.encode(signUpRequest.getPassword()))
				.roles(Collections.singleton(userRole))
				.build();
		User result = userJWTRepository.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/users/{userName}")
				.buildAndExpand(result.getUserName()).toUri();
		SignUpResponse signUpResponse = modelMapper.map(result, SignUpResponse.class);
		return ResponseEntity.created(location).body(signUpResponse);
	}
}
