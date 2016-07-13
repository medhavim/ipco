/**
 * 
 */
package com.neu.ipco.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.neu.ipco.dao.AuthenticationDao;
import com.neu.ipco.entity.Credential;
import com.neu.ipco.entity.User;
import com.neu.ipco.exception.AuthenticationException;

/**
 * @author Harsha
 *
 */
@Repository("authenticationDao")
@Transactional
public class AuthenticationDaoImpl implements AuthenticationDao {
	
	@Autowired
	private HibernateTemplate template;

	/**
	 * 
	 */
	public AuthenticationDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.neu.ipco.dao.AuthenticationDao#validUser(com.neu.ipco.entity.Credential)
	 */
	public User validUser(Credential userLogin) throws AuthenticationException {
		List<User> users = (List<User>) template.findByNamedParam("from User u where u.credential = :credential "
				+ "and u.userType.userTypeDesc = :userType", new String[]{"credential", "userType"}, new Object[]{userLogin, "user"});
		if(users.isEmpty())
			return null;
		else
			return users.get(0);
	}

	public User checkEmail(String email) throws AuthenticationException {
		List<User> users = (List<User>) template.findByNamedParam("from User u where u.email = :email", "email", email);
		if(users.isEmpty())
			return null;
		else
			return users.get(0);
	}

	public Credential checkUsername(String username) throws AuthenticationException {
		List<Credential> credentials = (List<Credential>) template.findByNamedParam("from Credential c where c.username = :username", "username", username);
		if(credentials.isEmpty())
			return null;
		else 
			return credentials.get(0);
	}

	public User userRegister(User user) throws AuthenticationException {
		int userId = (Integer) template.save(user);
		return template.get(User.class, userId);
	}

	public void resetCredentials(Credential newCredential) throws AuthenticationException {
		template.update(newCredential);
	}

	public User validAdmin(Credential adminLogin) throws AuthenticationException {
		return (User) template.findByNamedParam("from User u where u.credential = :credential "
				+ "and u.userType.userTypeDesc = :userType", new String[]{"credential", "userType"}, new Object[]{adminLogin, "admin"});
	}

}
