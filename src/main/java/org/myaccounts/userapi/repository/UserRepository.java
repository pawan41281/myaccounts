package org.myaccounts.userapi.repository;

import java.util.List;
import java.util.Optional;

import org.myaccounts.userapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public Optional<UserEntity> findByUsernameOrEmail(String username, String email);

	public Optional<UserEntity> findByUsername(String username);

	public Optional<UserEntity> findByEmail(String email);

	@Query(value = "SELECT * FROM users " + "WHERE LOWER(username) LIKE LOWER(CONCAT('%', :username, '%')) "
			+ "   OR LOWER(email) LIKE LOWER(CONCAT('%', :email, '%')) "
			+ "   OR mobile = :mobile", nativeQuery = true)
	public List<UserEntity> findByUsernameorEmailorMobile(String username, String email, String mobile);

	public Boolean existsByUsername(String username);

	public Boolean existsByEmail(String email);
}
