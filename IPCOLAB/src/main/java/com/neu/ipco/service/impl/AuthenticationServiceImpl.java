/**
 * 
 */
package com.neu.ipco.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.ipco.constants.AppConstants;
import com.neu.ipco.dao.AuthenticationDao;
import com.neu.ipco.entity.Credential;
import com.neu.ipco.entity.User;
import com.neu.ipco.exception.AuthenticationException;
import com.neu.ipco.service.AuthenticationService;

/**
 * @author Harsha
 *
 */
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private AuthenticationDao authenticationDao;
	

	/**
	 * 
	 */
	public AuthenticationServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public User validUser(Credential userLogin, String userType) throws AuthenticationException {
		return authenticationDao.validUser(userLogin, userType);
	}

	public boolean checkEmail(String email) throws AuthenticationException {
		User user = authenticationDao.checkEmail(email, AppConstants.USER_TYPE_USER);
		return (null == user);
	}
	
	public User checkEmailReset(String email, String userType) throws AuthenticationException {
		return authenticationDao.checkEmail(email, userType);
	}

	public boolean checkUsername(String username) throws AuthenticationException {
		Credential credential = authenticationDao.checkUsername(username);
		return (null == credential);
	}

	public User userRegister(User user) throws AuthenticationException {
		user.setCreatedTs(new Date());
		user.getCredential().setCreatedTs(new Date());
		return authenticationDao.userRegister(user);
	}

	public User resetCredentials(Credential newCredential, String userType) throws AuthenticationException {
		authenticationDao.resetCredentials(newCredential);
		return authenticationDao.validUser(newCredential, userType);
	}

}
