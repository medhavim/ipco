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
import com.neu.ipco.entity.Instance;
import com.neu.ipco.entity.InstanceModule;
import com.neu.ipco.entity.InstanceQuiz;
import com.neu.ipco.entity.InstanceTopic;
import com.neu.ipco.entity.InstanceType;
import com.neu.ipco.entity.Status;
import com.neu.ipco.entity.UserRole;
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

	public InstanceType getInstanceTypeByDesc(String instanceTypeBasic) throws ApplicationUtilException {
		LOGGER.debug("ApplicationUtilDaoImpl: getInstanceTypeByDesc: Executing");
		return ((List<InstanceType>) template.findByNamedParam("from InstanceType it where it.instanceTypeDesc = :instanceTypeDesc", "instanceTypeDesc", instanceTypeBasic)).get(0);
	}

	public List<Instance> getInstancesByType(Integer instanceTypeIdBasic) throws ApplicationUtilException {
		LOGGER.debug("ApplicationUtilDaoImpl: getInstancesByType: Executing");
		return (List<Instance>) template.findByNamedParam("from Instance i where i.instanceType.instanceTypeId = :instanceTypeId", "instanceTypeId", instanceTypeIdBasic);
	}

	public List<InstanceTopic> getInstanceTopicByTopicId(Integer topicId) throws ApplicationUtilException {
		LOGGER.debug("ApplicationUtilDaoImpl: getInstanceTopicByTopicId: Executing");
		return (List<InstanceTopic>) template.findByNamedParam("from InstanceTopic it where it.topic.topicId = :topicId", "topicId", topicId);
	}

	public List<InstanceModule> getInstanceModuleByModuleId(Integer moduleId) throws ApplicationUtilException {
		LOGGER.debug("ApplicationUtilDaoImpl: getInstanceModuleByModuleId: Executing");
		return (List<InstanceModule>) template.findByNamedParam("from InstanceModule im where im.module.moduleId = :moduleId", "moduleId", moduleId);
	}

	public List<UserRole> getUserRoles() throws ApplicationUtilException {
		LOGGER.debug("ApplicationUtilDaoImpl: getUserRoles: Executing");
		return template.loadAll(UserRole.class);
	}

	public Status getStatusById(int statusId) throws ApplicationUtilException {
		LOGGER.debug("ApplicationUtilDaoImpl: getStatusId: Executing");
		return template.get(Status.class, statusId);
	}

	public List<InstanceQuiz> getInstanceQuizesByQuizId(int quizId) throws ApplicationUtilException {
		LOGGER.debug("ApplicationUtilDaoImpl: getInstanceQuizesByQuizId: Executing");
		return (List<InstanceQuiz>) template.findByNamedParam("from InstanceQuiz iq where iq.quiz.quizId = :quizId", "quizId", quizId);
	}

}
