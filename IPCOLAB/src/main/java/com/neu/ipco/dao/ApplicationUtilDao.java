/**
 * 
 */
package com.neu.ipco.dao;

import java.util.List;

import com.neu.ipco.entity.Instance;
import com.neu.ipco.entity.InstanceModule;
import com.neu.ipco.entity.InstanceTopic;
import com.neu.ipco.entity.InstanceType;
import com.neu.ipco.entity.UserRole;
import com.neu.ipco.entity.UserType;
import com.neu.ipco.exception.ApplicationUtilException;

/**
 * @author Harsha
 *
 */
public interface ApplicationUtilDao {
	
	public UserType getUserType(UserType userType) throws ApplicationUtilException;

	public UserType addUserType(UserType userType) throws ApplicationUtilException;

	public InstanceType getInstanceTypeByDesc(String instanceTypeBasic) throws ApplicationUtilException;

	public List<Instance> getInstancesByType(Integer instanceTypeIdBasic) throws ApplicationUtilException;

	public List<InstanceTopic> getInstanceTopicByTopicId(Integer topicId) throws ApplicationUtilException;

	public List<InstanceModule> getInstanceModuleByModuleId(Integer moduleId) throws ApplicationUtilException;

	public List<UserRole> getUserRoles() throws ApplicationUtilException;

}
