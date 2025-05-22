package org.myaccounts.userapi.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.userapi.entity.RoleEntity;
import org.myaccounts.userapi.entity.UserEntity;
import org.myaccounts.userapi.mapper.UserMapper;
import org.myaccounts.userapi.repository.RoleRepository;
import org.myaccounts.userapi.repository.UserRepository;
import org.myaccounts.userapi.vo.RoleVo;
import org.myaccounts.userapi.vo.UserVo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final PasswordEncoder encoder;

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final UserMapper userMapper;

	@Override
	public UserVo save(UserVo userVo) throws UnableToProcessException, ResourceAlreadyExistsException {

		if (existsByUsername(userVo.getUsername())) {
			throw new ResourceAlreadyExistsException("Username is already exists");
		}

		if (existsByEmail(userVo.getEmail())) {
			throw new ResourceAlreadyExistsException("Username is already exists");
		}

		try {

			// Create new user's account
			UserEntity user = new UserEntity(userVo.getName(), userVo.getUsername(), userVo.getEmail(),
					userVo.getMobile(), encoder.encode(userVo.getPassword()), userVo.isLocked());

			Set<RoleVo> voRoles = userVo.getRoles();
			Set<RoleEntity> entityRoles = new HashSet<>();

			if (voRoles != null && !voRoles.isEmpty()) {
				voRoles.forEach(role -> {

					RoleEntity roleEntity = roleRepository.findByName(role.getName());
					if (roleEntity == null)
						throw new RuntimeException("Error: Role '" + role.getName() + "' not found in database.");

					entityRoles.add(roleEntity);
				});

			} else {

				RoleEntity roleEntity = roleRepository.findByName("ROLE_USER");
				if (roleEntity == null)
					throw new RuntimeException("Error: default role not found in database.");

				entityRoles.add(roleEntity);

			}

			user.setRoles(entityRoles);

			user = userRepository.save(user);

			return userMapper.convert(user);

		} catch (UnableToProcessException e) {
			throw new UnableToProcessException(e.getMessage());
		} catch (Exception e) {
			throw new UnableToProcessException(e.getMessage());
		}

	}

	@Override
	public UserVo update(UserVo userVo) throws UnableToProcessException, ResourceNotFoundException {

		// Update existing user's account
		UserEntity existingUser = null;
		UserEntity user = userMapper.convert(userVo);
		existingUser = userRepository.findByUsername(user.getUsername()).get();

		if (existingUser == null) {
			throw new ResourceNotFoundException("Username not exists");
		}

		if (user.getName() != null && !user.getName().isBlank())
			existingUser.setName(user.getName());

		if (user.getEmail() != null && !user.getEmail().isBlank())
			existingUser.setEmail(user.getEmail());

		if (user.getMobile() != null && !user.getMobile().isBlank())
			existingUser.setMobile(userVo.getMobile());

		if (user.getPassword() != null && !user.getPassword().isBlank())
			existingUser.setPassword(encoder.encode(user.getPassword()));

		existingUser.setLocked(user.isLocked());

		if (user.getRoles() != null && !user.getRoles().isEmpty()) {
			existingUser.setRoles(user.getRoles());
		}

		try {
			existingUser = userRepository.save(existingUser);
			return userVo;
		} catch (Exception e) {
			throw new UnableToProcessException("User not updated");
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
			throw new ResourceNotFoundException("Username not exists");
		}
	}

	@Override
	public List<UserVo> findAll() throws UnableToProcessException {
		List<UserEntity> list = userRepository.findAll();
		List<UserVo> userVoList = userMapper.convertToUserVoList(list);
		return userVoList;
	}

	@Override
	public UserVo findByUsername(String userName) throws ResourceNotFoundException {
		Optional<UserEntity> optionalUser = userRepository.findByUsername(userName);
		if (optionalUser.isPresent())
			return userMapper.convert(optionalUser.get());
		else
			throw new ResourceNotFoundException("Username not exists");
	}

	@Override
	public UserVo findByEmail(String email) throws ResourceNotFoundException {
		Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
		if (optionalUser.isPresent())
			return userMapper.convert(optionalUser.get());
		else
			throw new ResourceNotFoundException("Email not exists");
	}

}