/**
 * 
 */
package com.neu.ipco.service;

import com.neu.ipco.entity.BasicInstanceUser;
import com.neu.ipco.entity.Credential;
import com.neu.ipco.entity.CustomizeInstanceUser;
import com.neu.ipco.entity.User;
import com.neu.ipco.exception.AuthenticationException;

/**
 * @author Harsha
 *
 */
public interface AuthenticationService {
	
	public boolean checkEmail(String email) throws AuthenticationException;

	public User checkEmailReset(String email, String userType) throws AuthenticationException;
	
	public boolean checkUsername(String username) throws AuthenticationException;
	
	public User userRegister(User user) throws AuthenticationException;
	
	public User validUser(Credential userLogin, String userType) throws AuthenticationException;
	
	public User resetCredentials(Credential newCredential, String userType) throws AuthenticationException;

	public BasicInstanceUser getBasicInstanceByUserId(Integer userId) throws AuthenticationException;

	public CustomizeInstanceUser getCustomInstanceByUserId(Integer userId) throws AuthenticationException;

}
