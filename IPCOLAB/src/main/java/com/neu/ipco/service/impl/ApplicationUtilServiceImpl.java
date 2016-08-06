/**
 * 
 */
package com.neu.ipco.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neu.ipco.constants.AppConstants;
import com.neu.ipco.dao.ApplicationUtilDao;
import com.neu.ipco.dao.UserDao;
import com.neu.ipco.entity.ActivityAnswer;
import com.neu.ipco.entity.ActivityOption;
import com.neu.ipco.entity.Instance;
import com.neu.ipco.entity.InstanceModule;
import com.neu.ipco.entity.InstanceTopic;
import com.neu.ipco.entity.InstanceType;
import com.neu.ipco.entity.Module;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.Status;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.entity.UserRole;
import com.neu.ipco.entity.UserType;
import com.neu.ipco.exception.ApplicationUtilException;
import com.neu.ipco.exception.UserException;
import com.neu.ipco.service.ApplicationUtilService;

/**
 * @author Harsha
 *
 */
@Service("applicationUtilService")
public class ApplicationUtilServiceImpl implements ApplicationUtilService {
	
	private Logger LOGGER = Logger.getLogger(ApplicationUtilServiceImpl.class);
	
	@Autowired
	private ApplicationUtilDao applicationUtilDao;
	
	@Autowired
	private UserDao userDao;

	/* (non-Javadoc)
	 * @see com.neu.ipco.service.ApplicationUtilService#getUesrType(com.neu.ipco.entity.UserType)
	 */
	@Transactional
	public UserType getUserType(UserType userType) throws ApplicationUtilException {
		LOGGER.debug("ApplicationUtilServiceImpl: getUserType: Executing");
		try {
			return applicationUtilDao.getUserType(userType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationUtilException(e);
		}
	}

	public List<UserRole> getUserRoles() throws ApplicationUtilException {
		LOGGER.debug("ApplicationUtilServiceImpl: getUserRoles: Executing");
		try {
			List<UserRole> userRoles = applicationUtilDao.getUserRoles();
			if(userRoles.isEmpty()){
				return new ArrayList<UserRole>();
			}
			return userRoles;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationUtilException(e);
		}
	}

	public UserType addUserType(UserType userType) throws ApplicationUtilException {
		LOGGER.debug("ApplicationUtilServiceImpl: addUserType: Executing");
		try {
			return applicationUtilDao.addUserType(userType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationUtilException(e);
		}
	}

	public InstanceType getInstanceTypeByDesc(String instanceTypeBasic) throws ApplicationUtilException {
		LOGGER.debug("ApplicationUtilServiceImpl: getInstanceTypeByDesc: Executing");
		try {
			return applicationUtilDao.getInstanceTypeByDesc(instanceTypeBasic);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationUtilException(e);
		}
	}

	public void updateNewTopicToBasicInstances(Topic newTopic) throws ApplicationUtilException{
		List<Instance> basicInstances = applicationUtilDao.getInstancesByType(AppConstants.INSTANCE_TYPE_ID_BASIC);
		try {
			for(Instance instance : basicInstances){
				InstanceTopic instanceTopic = new InstanceTopic();
				instanceTopic.setTopic(newTopic);
				Status status;
				status = userDao.getStatusByDesc(AppConstants.STATUS_NOT_STARTED);
				instanceTopic.setStatus(status);
				instanceTopic.setCreatedTs(new Date());
				instance.getInstanceTopics().add(instanceTopic);
				userDao.saveOrUpdateInstance(instance);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationUtilException(e);
		}
	}

	public void updateNewModuleToInstanceTopics(Module module) throws ApplicationUtilException {
		
		List<InstanceTopic> instanceTopics = applicationUtilDao.getInstanceTopicByTopicId(module.getTopic().getTopicId());
		try {
			for(InstanceTopic instanceTopic : instanceTopics){
				InstanceModule instanceModule = new InstanceModule();
				instanceModule.setModule(module);
				Status status = userDao.getStatusByDesc(module.getOrderNo()==1?AppConstants.STATUS_INCOMPLETE:AppConstants.STATUS_NOT_STARTED);
				instanceModule.setStatus(status);
				instanceModule.setCreatedTs(new Date());
				instanceTopic.getInstanceModules().add(instanceModule);
				userDao.saveOrUpdateInstanceTopic(instanceTopic);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationUtilException(e);
		}
	}

	public void updateNewActivityToInstanceModule(ActivityOption activityOption) throws ApplicationUtilException {
		
		List<InstanceModule> instanceModules = applicationUtilDao.getInstanceModuleByModuleId(activityOption.getModule().getModuleId());
		try {
			for(InstanceModule instanceModule : instanceModules){
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
				instanceModule.getActivityAnswers().add(activityAnswer);
				userDao.saveOrUpdateInstanceModule(instanceModule);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationUtilException(e);
		}
		
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

}
