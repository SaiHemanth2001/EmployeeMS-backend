package com.sterlite.ms.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sterlite.ms.config.JwtService;
import com.sterlite.ms.entity.Manager;
import com.sterlite.ms.entity.Role;
import com.sterlite.ms.repository.ManagerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final ManagerRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse saveManager(RegisterRequest request) {

		var manager = Manager.builder().name(request.getName()).email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword())).role(Role.MANAGER).build();
		repository.save(manager);
		var jwtToken = jwtService.generateToken(manager);

		return AuthenticationResponse.builder().token(jwtToken).build();

	}

	public String authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
					)
				);
		var manager = repository.findByEmail(request.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("manager not found"));
		var jwtToken  = jwtService.generateToken(manager);
		
		return jwtToken;
	}

}
