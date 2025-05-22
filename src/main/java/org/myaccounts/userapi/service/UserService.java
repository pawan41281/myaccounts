package org.myaccounts.userapi.service;

import java.util.List;

import org.myaccounts.common.exception.ResourceAlreadyExistsException;
import org.myaccounts.common.exception.ResourceNotFoundException;
import org.myaccounts.common.exception.UnableToProcessException;
import org.myaccounts.userapi.vo.UserVo;

public interface UserService {

	public UserVo findByUsername(String userName) throws ResourceNotFoundException;

	public UserVo findByEmail(String email) throws ResourceNotFoundException;

	public UserVo save(UserVo UserVo) throws UnableToProcessException, ResourceAlreadyExistsException;

	public UserVo update(UserVo UserVo) throws UnableToProcessException, ResourceNotFoundException;

	public boolean existsByUsername(String userName) throws ResourceNotFoundException;

	public boolean existsByEmail(String Email) throws ResourceNotFoundException;

	public List<UserVo> findAll() throws UnableToProcessException;
}