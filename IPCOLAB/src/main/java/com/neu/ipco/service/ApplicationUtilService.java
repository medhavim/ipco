/**
 * 
 */
package com.neu.ipco.service;

import com.neu.ipco.entity.UserType;
import com.neu.ipco.exception.ApplicationUtilException;

/**
 * @author Harsha
 *
 */
public interface ApplicationUtilService {
	
	public UserType getUserType(UserType userType) throws ApplicationUtilException;
	
	public UserType addUserType(UserType userType) throws ApplicationUtilException;

}
