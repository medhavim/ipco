/**
 * 
 */
package com.neu.ipco.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.neu.ipco.dao.ApplicationUtilDao;
import com.neu.ipco.entity.UserType;
import com.neu.ipco.exception.ApplicationUtilException;

/**
 * @author Harsha
 *
 */
@Repository("applicationUtilDao")
public class ApplicationUtilDaoImpl implements ApplicationUtilDao {

	@Autowired
	private HibernateTemplate template;
	
	public UserType getUserType(UserType userType) throws ApplicationUtilException {
		return ((List<UserType>) template.findByNamedParam("from UserType ut where ut.userTypeDesc = :userTypeDesc", "userTypeDesc", userType.getUserTypeDesc())).get(0);
	}

	public UserType addUserType(UserType userType) throws ApplicationUtilException {
		int userTypeId = (Integer) template.save(userType);
		userType.setUserTypeId(userTypeId);
		return userType;
	}

}
