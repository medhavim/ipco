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

import com.neu.ipco.dao.ApplicationUtilDao;
import com.neu.ipco.entity.UserType;
import com.neu.ipco.exception.ApplicationUtilException;

/**
 * @author Harsha
 *
 */
@Repository("applicationUtilDao")
@Transactional
public class ApplicationUtilDaoImpl implements ApplicationUtilDao {
	
	private Logger LOGGER = Logger.getLogger(ApplicationUtilDaoImpl.class);

	@Autowired
	private HibernateTemplate template;
	
	public UserType getUserType(UserType userType) throws ApplicationUtilException {
		LOGGER.debug("ApplicationUtilDaoImpl: getUserType: Executing");
		return ((List<UserType>) template.findByNamedParam("from UserType ut where ut.userTypeDesc = :userTypeDesc", "userTypeDesc", userType.getUserTypeDesc())).get(0);
	}

	public UserType addUserType(UserType userType) throws ApplicationUtilException {
		LOGGER.debug("ApplicationUtilDaoImpl: addUserType: Start");
		int userTypeId = (Integer) template.save(userType);
		userType.setUserTypeId(userTypeId);
		LOGGER.debug("ApplicationUtilDaoImpl: addUserType: End");
		return userType;
	}

}
