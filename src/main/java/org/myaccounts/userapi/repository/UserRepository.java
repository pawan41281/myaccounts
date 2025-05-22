package org.myaccounts.userapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.myaccounts.userapi.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	public Optional<UserEntity> findByUsernameOrEmail(String username, String email);
	
	public Optional<UserEntity> findByUsername(String username);
	
	public Optional<UserEntity> findByEmail(String email);

	public Boolean existsByUsername(String username);

	public Boolean existsByEmail(String email);
}
