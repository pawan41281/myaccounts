package org.myaccounts.authapi.service;

import org.myaccounts.authapi.vo.LoginVo;
import org.myaccounts.authapi.vo.SignupRequestVo;
import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;

public interface AuthService {
	public String login(LoginVo loginVo) throws ResourceNotFoundException;
	
	public boolean validateToken(String token);

	public boolean existsByUsername(String userName) throws ResourceNotFoundException;

	public boolean existsByEmail(String Email) throws ResourceNotFoundException;
	
	public boolean save(SignupRequestVo signUpRequestVo) throws UnableToProcessException, ResourceAlreadyExistsException;
}