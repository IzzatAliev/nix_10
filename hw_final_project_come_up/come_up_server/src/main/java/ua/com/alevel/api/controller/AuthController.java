package ua.com.alevel.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.api.dto.LocalUser;
import ua.com.alevel.api.dto.request.LoginRequest;
import ua.com.alevel.api.dto.request.SignUpRequest;
import ua.com.alevel.api.dto.response.ApiResponse;
import ua.com.alevel.api.dto.response.JwtAuthenticationResponse;
import ua.com.alevel.exception.UserAlreadyExistAuthenticationException;
import ua.com.alevel.facade.user.StudentFacade;
import ua.com.alevel.security.jwt.TokenProvider;
import ua.com.alevel.util.GeneralUtils;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final StudentFacade studentFacade;
	private final TokenProvider tokenProvider;

	@Lazy
	public AuthController( AuthenticationManager authenticationManager, StudentFacade studentFacade, TokenProvider tokenProvider) {
		this.authenticationManager = authenticationManager;
		this.studentFacade = studentFacade;
		this.tokenProvider = tokenProvider;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.createToken(authentication);
		LocalUser localUser = (LocalUser) authentication.getPrincipal();
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, GeneralUtils.buildUserInfo(localUser)));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		try {
			studentFacade.registerNewStudent(signUpRequest);
		} catch (UserAlreadyExistAuthenticationException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully"));
	}
}
