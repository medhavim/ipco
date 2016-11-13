/**
 * 
 */
package com.neu.ipco.dao.impl;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.neu.ipco.dao.AuthenticationDao;
import com.neu.ipco.entity.BasicInstanceUser;
import com.neu.ipco.entity.Credential;
import com.neu.ipco.entity.CustomizeInstanceUser;
import com.neu.ipco.entity.User;
import com.neu.ipco.exception.AuthenticationException;

/**
 * @author Harsha
 *
 */
@Repository("authenticationDao")
@Transactional
public class AuthenticationDaoImpl implements AuthenticationDao {
	
	private Logger LOGGER = Logger.getLogger(AuthenticationDaoImpl.class);
	
	@Autowired
	private HibernateTemplate template;

	/**
	 * 
	 */
	public AuthenticationDaoImpl() {
	}

	/* (non-Javadoc)
	 * @see com.neu.ipco.dao.AuthenticationDao#validUser(com.neu.ipco.entity.Credential)
	 */
	public User validUser(Credential userLogin, String userType) throws AuthenticationException {
		
		LOGGER.debug("AuthenticationDaoImpl: validUser: Start");
		
		List<User> users = (List<User>) template.findByNamedParam("from User u where u.credential.username = :username and u.credential.password = :password "
				+ "and u.userType.userTypeDesc = :userType", new String[]{"username", "password", "userType"}, new Object[]{userLogin.getUsername(), userLogin.getPassword(), userType});
		
		if(users.isEmpty()){
			LOGGER.debug("AuthenticationDaoImpl: validUser: End");
			return null;
		}
		else{
			LOGGER.debug("AuthenticationDaoImpl: validUser: End");
			return users.get(0);
		}
	}

	public User checkEmail(String email, String userType) throws AuthenticationException {
		
		LOGGER.debug("AuthenticationDaoImpl: checkEmail: Start");
		
		List<User> users = (List<User>) template.findByNamedParam("from User u where u.email = :email "
				+ "and u.userType.userTypeDesc = :userType", new String[]{"email", "userType"}, new Object[]{email, userType});
		if(users.isEmpty()){
			LOGGER.debug("AuthenticationDaoImpl: checkEmail: End");
			return null;
		}
		else{
			LOGGER.debug("AuthenticationDaoImpl: checkEmail: End");
			return users.get(0);
		}
	}

	public Credential checkUsername(String username) throws AuthenticationException {

		LOGGER.debug("AuthenticationDaoImpl: checkUsername: Start");
		
		List<Credential> credentials = (List<Credential>) template.findByNamedParam("from Credential c where c.username = :username", "username", username);
		if(credentials.isEmpty()){
			LOGGER.debug("AuthenticationDaoImpl: checkUsername: End");
			return null;
		}
		else{
			LOGGER.debug("AuthenticationDaoImpl: checkUsername: End");
			return credentials.get(0);
		}
	}

	public User userRegister(User user) throws AuthenticationException {
		
		LOGGER.debug("AuthenticationDaoImpl: userRegister: Start");

		int userId = (Integer) template.save(user);
		User newUser = template.get(User.class, userId);
		
		LOGGER.debug("AuthenticationDaoImpl: userRegister: End");
		return newUser;
	}

	public void resetCredentials(Credential newCredential) throws AuthenticationException {
		
		LOGGER.debug("AuthenticationDaoImpl: resetCredentials: Executing");
		template.update(newCredential);
	}

	public BasicInstanceUser getBasicInstanceByUserId(Integer userId) throws AuthenticationException {
		
		LOGGER.debug("AuthenticationDaoImpl: getBasicInstanceByUserId: Start");
		
		List<BasicInstanceUser> basicInstanceUsers = (List<BasicInstanceUser>) template.findByNamedParam("from BasicInstanceUser biu where biu.user.userId = :userId", "userId", userId);
		
		if(basicInstanceUsers.isEmpty()){
			LOGGER.debug("AuthenticationDaoImpl: getBasicInstanceByUserId: End");
			return null;
		}
		else{
			LOGGER.debug("AuthenticationDaoImpl: getBasicInstanceByUserId: End");
			return basicInstanceUsers.get(0);
		}
	}

	public CustomizeInstanceUser getCustomInstanceByUserId(Integer userId) throws AuthenticationException {
		
		LOGGER.debug("AuthenticationDaoImpl: getCustomInstanceByUserId: Start");
		
		List<CustomizeInstanceUser> customizeInstanceUsers = (List<CustomizeInstanceUser>) template.findByNamedParam("from CustomizeInstanceUser ciu where ciu.user.userId = :userId", "userId", userId);
		
		if(customizeInstanceUsers.isEmpty()){
			LOGGER.debug("AuthenticationDaoImpl: getCustomInstanceByUserId: End");
			return null;
		}
		else{
			LOGGER.debug("AuthenticationDaoImpl: getCustomInstanceByUserId: End");
			return customizeInstanceUsers.get(0);
		}
	}

	public void saveOrUpdateUser(User user) {
		LOGGER.debug("AuthenticationDaoImpl: saveOrUpdateUser: Executing");
		template.saveOrUpdate(user);
	}

}
