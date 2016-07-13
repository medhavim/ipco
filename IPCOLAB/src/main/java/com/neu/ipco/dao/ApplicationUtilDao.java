/**
 * 
 */
package com.neu.ipco.dao;

import com.neu.ipco.entity.UserType;
import com.neu.ipco.exception.ApplicationUtilException;

/**
 * @author Harsha
 *
 */
public interface ApplicationUtilDao {
	
	public UserType getUserType(UserType userType) throws ApplicationUtilException;

	public UserType addUserType(UserType userType) throws ApplicationUtilException;

}
