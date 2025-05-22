package org.myaccounts.authapi.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.myaccounts.authapi.vo.LoginVo;
import org.myaccounts.authapi.vo.SignupRequestVo;
import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.common.security.JwtTokenProvider;
import org.myaccounts.userapi.entity.RoleEntity;
import org.myaccounts.userapi.entity.UserEntity;
import org.myaccounts.userapi.repository.RoleRepository;
import org.myaccounts.userapi.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;

	private final JwtTokenProvider jwtTokenProvider;

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final PasswordEncoder encoder;

	@Override
	public String login(LoginVo loginVo) throws ResourceNotFoundException {

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginVo.getUsernameOrEmail(), loginVo.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			String token = jwtTokenProvider.generateToken(authentication);

			return token;
		} catch (Exception e) {
			throw new ResourceNotFoundException("Invalid credential");
		}
	}

	@Override
	public boolean existsByUsername(String userName) throws ResourceNotFoundException {
		try {
			return userRepository.existsByUsername(userName);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Username not exists");
		}
	}

	@Override
	public boolean existsByEmail(String Email) throws ResourceNotFoundException {
		try {
			return userRepository.existsByEmail(Email);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Email not exists");
		}
	}

	@Override
	public boolean save(SignupRequestVo signUpRequestVo)
			throws UnableToProcessException, ResourceAlreadyExistsException {

		if (existsByUsername(signUpRequestVo.getUsername())) {
			throw new ResourceAlreadyExistsException("Username is already exists");
		}

		if (existsByEmail(signUpRequestVo.getEmail())) {
			throw new ResourceAlreadyExistsException("Username is already exists");
		}

		try {

			// Create new user's account
			UserEntity user = new UserEntity(signUpRequestVo.getName(), signUpRequestVo.getUsername(), signUpRequestVo.getEmail(),
					signUpRequestVo.getMobile(), encoder.encode(signUpRequestVo.getPassword()),
					signUpRequestVo.isLocked());

			Set<String> strRoles = signUpRequestVo.getRoles();
			Set<RoleEntity> roles = new HashSet<>();

			if (strRoles != null && !strRoles.isEmpty()) {
				strRoles.forEach(role -> {

					RoleEntity userRole = roleRepository.findByName(role);
					if (userRole == null)
						throw new RuntimeException("Error: Role '" + role + "' not found in database.");

					roles.add(userRole);
				});

			} else {
				
				RoleEntity userRole = roleRepository.findByName("ROLE_USER");
				if (userRole == null)
					throw new RuntimeException("Error: default role not found in database.");

				roles.add(userRole);
				
				
				

			}

			user.setRoles(roles);

			user = userRepository.save(user);

			return user != null ? true : false;

		} catch (UnableToProcessException e) {
			throw new UnableToProcessException(e.getMessage());
		}catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}
	}

	@Override
	public boolean validateToken(String token) {
		return jwtTokenProvider.validateToken(token);
	}

}