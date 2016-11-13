/**
 * 
 */
package com.neu.ipco.dao;

import com.neu.ipco.entity.BasicInstanceUser;
import com.neu.ipco.entity.Credential;
import com.neu.ipco.entity.CustomizeInstanceUser;
import com.neu.ipco.entity.User;
import com.neu.ipco.exception.AuthenticationException;

/**
 * @author Harsha
 *
 */
public interface AuthenticationDao {

	
	public User validUser(Credential userLogin, String userType) throws AuthenticationException;

	public User checkEmail(String email, String userType) throws AuthenticationException;

	public Credential checkUsername(String username) throws AuthenticationException;

	public User userRegister(User user) throws AuthenticationException;

	public void resetCredentials(Credential newCredential) throws AuthenticationException;

	public BasicInstanceUser getBasicInstanceByUserId(Integer userId) throws AuthenticationException;

	public CustomizeInstanceUser getCustomInstanceByUserId(Integer userId) throws AuthenticationException;

	public void saveOrUpdateUser(User user);
}
