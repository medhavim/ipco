/**
 * 
 */
package com.neu.ipco.service;

import com.neu.ipco.entity.Credential;
import com.neu.ipco.entity.User;
import com.neu.ipco.exception.AuthenticationException;

/**
 * @author Harsha
 *
 */
public interface AuthenticationService {
	
	public boolean checkEmail(String email) throws AuthenticationException;
	
	public boolean checkUsername(String username) throws AuthenticationException;
	
	public User userRegister(User user) throws AuthenticationException;
	
	public User validUser(Credential userLogin) throws AuthenticationException;
	
	public void resetCredentials(Credential newCredential) throws AuthenticationException;
	
	public User validAdmin(Credential adminLogin) throws AuthenticationException;

}
