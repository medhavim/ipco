/**
 * 
 */
package com.neu.ipco.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neu.ipco.dao.ApplicationUtilDao;
import com.neu.ipco.entity.UserType;
import com.neu.ipco.exception.ApplicationUtilException;
import com.neu.ipco.service.ApplicationUtilService;

/**
 * @author Harsha
 *
 */
@Service("applicationUtilService")
public class ApplicationUtilServiceImpl implements ApplicationUtilService {
	
	@Autowired
	private ApplicationUtilDao applicationUtilDao;

	/* (non-Javadoc)
	 * @see com.neu.ipco.service.ApplicationUtilService#getUesrType(com.neu.ipco.entity.UserType)
	 */
	@Transactional
	public UserType getUserType(UserType userType) throws ApplicationUtilException {
		return applicationUtilDao.getUserType(userType);
	}

	public UserType addUserType(UserType userType) throws ApplicationUtilException {
		return applicationUtilDao.addUserType(userType);
	}

}
