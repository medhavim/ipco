/**
 * 
 */
package com.neu.ipco.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.ipco.constants.AppConstants;
import com.neu.ipco.dao.ApplicationUtilDao;
import com.neu.ipco.dao.UserDao;
import com.neu.ipco.entity.ActivityAnswer;
import com.neu.ipco.entity.ActivityOption;
import com.neu.ipco.entity.BasicInstanceUser;
import com.neu.ipco.entity.CustomizeInstanceUser;
import com.neu.ipco.entity.Instance;
import com.neu.ipco.entity.InstanceModule;
import com.neu.ipco.entity.InstanceTopic;
import com.neu.ipco.entity.InstanceType;
import com.neu.ipco.entity.Module;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.Status;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.exception.UserException;
import com.neu.ipco.service.UserService;


/**
 * @author harsh
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	private Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ApplicationUtilDao applicationUtilDao;

	/* (non-Javadoc)
	 * @see com.neu.ipco.service.UserService#getBasicInstanceByUserId(java.lang.Integer)
	 */
	public BasicInstanceUser getInstanceByUserId(Integer userId, int instanceTypeId) throws UserException {
		LOGGER.debug("UserServiceImpl: getInstanceByUserId: Executing");
		try {
			BasicInstanceUser basicInstanceUser = userDao.getInstanceByUserId(userId, instanceTypeId);
//			This is to add new topics to the instance and reorder
			if(null != basicInstanceUser){
				basicInstanceUser.getInstance().reorder();
			}
			return basicInstanceUser;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	public void populateTopicsForInstance(Instance instance) throws UserException {
		LOGGER.debug("UserServiceImpl: populateTopicsForInstance: Start");
		List<Topic> topics = userDao.getTopicsByTypeId(instance.getInstanceType().getInstanceTypeId());
		try {
			populateInstanceTopics(topics, instance.getInstanceTopics());
//			This is to sort the instance topics by order no
			instance.reorder();
			LOGGER.debug("UserServiceImpl: populateTopicsForInstance: End");
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	private void populateInstanceTopics(List<Topic> topics, Set<InstanceTopic> instanceTopics) throws UserException{
		
		LOGGER.debug("UserServiceImpl: populateInstanceTopics: Start");
		for(Topic topic : topics){
			InstanceTopic instanceTopic = new InstanceTopic();
			instanceTopic.setTopic(topic);
			Status status = userDao.getStatusByDesc(topic.getOrderNo()==1?AppConstants.STATUS_INCOMPLETE:AppConstants.STATUS_NOT_STARTED);
			instanceTopic.setStatus(status);
			instanceTopic.setCreatedTs(new Date());
			populateInstanceModule(topic.getModules(), instanceTopic.getInstanceModules());
			instanceTopics.add(instanceTopic);
		}
		LOGGER.debug("UserServiceImpl: populateInstanceTopics: End");
		
	}

	private void populateInstanceModule(Set<Module> modules, Set<InstanceModule> instanceModules) throws UserException{
		
		LOGGER.debug("UserServiceImpl: populateInstanceModule: Start");
		for(Module module : modules){
			InstanceModule instanceModule = new InstanceModule();
			instanceModule.setModule(module);
			Status status = userDao.getStatusByDesc(module.getOrderNo()==1?AppConstants.STATUS_INCOMPLETE:AppConstants.STATUS_NOT_STARTED);
			instanceModule.setStatus(status);
			instanceModule.setCreatedTs(new Date());
			populateActivityAnswer(module.getActivityOptions(), instanceModule.getActivityAnswers());
			instanceModules.add(instanceModule);
		}
		LOGGER.debug("UserServiceImpl: populateInstanceModule: End");
	}

	private void populateActivityAnswer(Set<ActivityOption> activityOptions, Set<ActivityAnswer> activityAnswers) throws UserException {

		LOGGER.debug("UserServiceImpl: populateActivityAnswer: Start");
		for(ActivityOption activityOption : activityOptions){
			ActivityAnswer activityAnswer = new ActivityAnswer();
			activityAnswer.setActivityOption(activityOption);
			activityAnswer.setCreatedTs(new Date());
			Status status = userDao.getStatusByDesc(activityOption.getOrderNo()==1?AppConstants.STATUS_INCOMPLETE:AppConstants.STATUS_NOT_STARTED);
			activityAnswer.setStatus(status);
			if(activityOption.getActivity().getActivityTemplate().getActivityTemplateId() != AppConstants.TEMPLATE_IMAGE_DESC
					&& activityOption.getActivity().getActivityTemplate().getActivityTemplateId() != AppConstants.TEMPLATE_VIDEO_DESC){
				populateAnswers(activityOption.getOptions(), activityAnswer.getAnswers());
			}
			activityAnswer = userDao.saveActivityAnswer(activityAnswer);
			activityAnswers.add(activityAnswer);
		}
		LOGGER.debug("UserServiceImpl: populateActivityAnswer: End");
	}

	private void populateAnswers(Set<Option> options, Set<Option> answers) {
		
		for(Option option : options){
			Option answer = new Option();
			answer.setOptionText(option.getOptionText());
			answer.setOrderNo(option.getOrderNo());
			answer.setCreatedTs(new Date());
			answers.add(answer);
		}
	}

	public Instance saveInstance(Instance instance) throws UserException {
		LOGGER.debug("UserServiceImpl: saveInstance: Executing");
		try {
			return userDao.saveInstance(instance);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	public BasicInstanceUser saveBasicInstance(BasicInstanceUser basicInstanceUser) throws UserException {
		LOGGER.debug("UserServiceImpl: saveBasicInstance: Executing");
		try {
			return userDao.saveBasicInstance(basicInstanceUser);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}
	
	public BasicInstanceUser saveOrUpdateBasicInstance(BasicInstanceUser basicInstanceUser) throws UserException {
		LOGGER.debug("UserServiceImpl: saveBasicInstance: Executing");
		try {
			return userDao.saveOrUpdateBasicInstance(basicInstanceUser);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}


	public InstanceModule geInstanceModuleById(int instanceModuleId) throws UserException {
		LOGGER.debug("UserServiceImpl: geInstanceModuleById: Executing");
		try {
			return userDao.geInstanceModuleById(instanceModuleId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	public InstanceTopic getInstanceTopicById(Integer instanceTopicId) throws UserException {
		LOGGER.debug("UserServiceImpl: getInstanceTopicById: Executing");
		try {
			return userDao.getInstanceTopicById(instanceTopicId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	public Instance getInstanceById(Integer instanceId) throws UserException {
		LOGGER.debug("UserServiceImpl: getInstanceById: Executing");
		try {
			return userDao.getInstanceById(instanceId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	public void populateTopicsForInstance(List<Topic> topics, Instance instance) throws UserException {
		LOGGER.debug("UserServiceImpl: populateTopicsForInstance: Start");
		try {
			populateInstanceTopics(topics, instance.getInstanceTopics());
//			This is to sort the instance topics by order no
			instance.reorder();
			LOGGER.debug("UserServiceImpl: populateTopicsForInstance: End");
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	public CustomizeInstanceUser saveOrUpdateCustomInstance(CustomizeInstanceUser customizeInstanceUser)
			throws UserException {
		LOGGER.debug("UserServiceImpl: saveOrUpdateCustomInstance: Executing");
		try {
			return userDao.saveOrUpdateCustomInstance(customizeInstanceUser);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

}
