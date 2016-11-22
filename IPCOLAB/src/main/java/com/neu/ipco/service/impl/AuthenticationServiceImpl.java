/**
 * 
 */
package com.neu.ipco.service.impl;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.ipco.dao.AuthenticationDao;
import com.neu.ipco.entity.BasicInstanceUser;
import com.neu.ipco.entity.Credential;
import com.neu.ipco.entity.CustomizeInstanceUser;
import com.neu.ipco.entity.User;
import com.neu.ipco.exception.AuthenticationException;
import com.neu.ipco.service.AuthenticationService;
import com.neu.ipco.utility.AppConstants;

/**
 * @author Harsha
 *
 */
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {
	
	private Logger LOGGER = Logger.getLogger(AuthenticationServiceImpl.class);
	
	@Autowired
	private AuthenticationDao authenticationDao;
	

	/**
	 * 
	 */
	public AuthenticationServiceImpl() {
	}

	public User validUser(Credential userLogin, String userType) throws AuthenticationException {
		LOGGER.debug("AuthenticationServiceImpl: validUser: Executing");
		return authenticationDao.validUser(userLogin, userType);
	}

	public boolean checkEmail(String email) throws AuthenticationException {
		LOGGER.debug("AuthenticationServiceImpl: checkEmail: Start");
		User user = authenticationDao.checkEmail(email, AppConstants.USER_TYPE_USER);
		LOGGER.debug("AuthenticationServiceImpl: checkEmail: End");
		return (null == user);
	}
	
	public User checkEmailReset(String email, String userType) throws AuthenticationException {
		LOGGER.debug("AuthenticationServiceImpl: checkEmailReset: Executing");
		return authenticationDao.checkEmail(email, userType);
	}

	public boolean checkUsername(String username) throws AuthenticationException {
		LOGGER.debug("AuthenticationServiceImpl: checkUsername: Start");
		Credential credential = authenticationDao.checkUsername(username);
		LOGGER.debug("AuthenticationServiceImpl: checkUsername: End");
		return (null == credential);
	}

	public User userRegister(User user) throws AuthenticationException {
		LOGGER.debug("AuthenticationServiceImpl: userRegister: Start");
		user.setCreatedTs(new Date());
		user.getCredential().setCreatedTs(new Date());
		LOGGER.debug("AuthenticationServiceImpl: userRegister: Executing");
		return authenticationDao.userRegister(user);
	}

	public User resetCredentials(Credential newCredential, String userType) throws AuthenticationException {
		LOGGER.debug("AuthenticationServiceImpl: resetCredentials: Start");
		authenticationDao.resetCredentials(newCredential);
		LOGGER.debug("AuthenticationServiceImpl: resetCredentials: Executing");
		return authenticationDao.validUser(newCredential, userType);
	}

	public BasicInstanceUser getBasicInstanceByUserId(Integer userId) throws AuthenticationException {
		LOGGER.debug("AuthenticationServiceImpl: getBasicInstanceByUserId: Executing");
		return authenticationDao.getBasicInstanceByUserId(userId);
	}

	public CustomizeInstanceUser getCustomInstanceByUserId(Integer userId) throws AuthenticationException {
		LOGGER.debug("AuthenticationServiceImpl: getBasicInstanceByUserId: Executing");
		return authenticationDao.getCustomInstanceByUserId(userId);
	}

	@Override
	public void updateUserLoginTimestamp(User user) throws AuthenticationException {
		LOGGER.debug("AuthenticationServiceImpl: updateUserLoginTimestamp: Executing");
		user.getCredential().setUpdatedTs(new Date());
		authenticationDao.saveOrUpdateUser(user);
	}
	

	public void loadUserInstancesToSession(HttpSession session, User user) throws AuthenticationException {
		BasicInstanceUser basicInstanceUser = getBasicInstanceByUserId(user.getUserId());
		
		CustomizeInstanceUser customizeInstanceUser = getCustomInstanceByUserId(user.getUserId());
		
		session.setAttribute("basicInstance", basicInstanceUser);
		session.setAttribute("customInstance", customizeInstanceUser);
	}

}
