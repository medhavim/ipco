/**
 * 
 */
package com.neu.ipco.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public User validUser(Credential userLogin) throws AuthenticationException {
		return authenticationDao.validUser(userLogin);
	}

	public boolean checkEmail(String email) throws AuthenticationException {
		User user = authenticationDao.checkEmail(email);
		return (null == user);
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

	public void resetCredentials(Credential newCredential) throws AuthenticationException {
		authenticationDao.resetCredentials(newCredential);
	}

	public User validAdmin(Credential adminLogin) throws AuthenticationException {
		return authenticationDao.validAdmin(adminLogin);
	}

}
