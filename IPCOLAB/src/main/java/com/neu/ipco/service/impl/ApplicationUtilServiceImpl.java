/**
 * 
 */
package com.neu.ipco.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neu.ipco.dao.ApplicationUtilDao;
import com.neu.ipco.dao.UserDao;
import com.neu.ipco.entity.ActivityAnswer;
import com.neu.ipco.entity.ActivityOption;
import com.neu.ipco.entity.Instance;
import com.neu.ipco.entity.InstanceModule;
import com.neu.ipco.entity.InstanceQuiz;
import com.neu.ipco.entity.InstanceTopic;
import com.neu.ipco.entity.InstanceType;
import com.neu.ipco.entity.Module;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.Quiz;
import com.neu.ipco.entity.QuizAnswer;
import com.neu.ipco.entity.QuizOption;
import com.neu.ipco.entity.Status;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.entity.UserRole;
import com.neu.ipco.entity.UserType;
import com.neu.ipco.exception.ApplicationUtilException;
import com.neu.ipco.exception.UserException;
import com.neu.ipco.service.ApplicationUtilService;
import com.neu.ipco.utility.AppConstants;

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
	
	private static Status incompleteStatus = null;
	
	private static Status notStartedStatus = null;
	
	private static Status completedStatus = null;

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
		try {
			List<Instance> basicInstances = applicationUtilDao.getInstancesByType(AppConstants.INSTANCE_TYPE_ID_BASIC);
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
		
		try {
			List<InstanceTopic> instanceTopics = applicationUtilDao.getInstanceTopicByTopicId(module.getTopic().getTopicId());
			for(InstanceTopic instanceTopic : instanceTopics){
				InstanceModule instanceModule = new InstanceModule();
				instanceModule.setModule(module);
				Status status = userDao.getStatusByDesc(module.getOrderNo()==1?AppConstants.STATUS_INCOMPLETE:AppConstants.STATUS_NOT_STARTED);
				instanceModule.setStatus(status);
				instanceModule.setCreatedTs(new Date());
				instanceTopic.getInstanceModules().add(instanceModule);
				userDao.saveOrUpdateInstanceTopic(instanceTopic);
				changeInstanceTopicStatusIfCompleted(instanceTopic.getInstanceTopicId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationUtilException(e);
		}
	}

	private void changeInstanceTopicStatusIfCompleted(int instanceTopicId) throws ApplicationUtilException, UserException {
		Status statusIncomplete = getIncompleteStatus();
		InstanceTopic instanceTopic = userDao.getInstanceTopicById(instanceTopicId);
		if(instanceTopic.getStatus().getStatusId() == AppConstants.STATUS_COMPLETE_ID){
			instanceTopic.setStatus(statusIncomplete);
		}
		userDao.saveOrUpdateInstanceTopic(instanceTopic);
	}

	private void changeInstanceModuleStatusIfCompleted(int instanceModuleId) throws UserException, ApplicationUtilException {
		Status statusIncomplete = getIncompleteStatus();
		InstanceModule instanceModule = userDao.geInstanceModuleById(instanceModuleId);
		if(instanceModule.getStatus().getStatusId() == AppConstants.STATUS_COMPLETE_ID){
			instanceModule.setStatus(statusIncomplete);
		}
		userDao.saveOrUpdateInstanceModule(instanceModule);
	}

	private void changeInstanceQuizStatusIfCompleted(int instanceQuizId) throws UserException, ApplicationUtilException {
		Status statusIncomplete = getIncompleteStatus();
		InstanceQuiz instanceQuiz = userDao.getInstanceQuizById(instanceQuizId);
		if(instanceQuiz.getStatus().getStatusId() == AppConstants.STATUS_COMPLETE_ID){
			instanceQuiz.setStatus(statusIncomplete);
		}
		userDao.saveOrUpdateInstanceQuiz(instanceQuiz);
	
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
				changeInstanceModuleStatusIfCompleted(instanceModule.getInstanceModuleId());
				changeInstanceTopicStatusIfCompleted(instanceModule.getInstanceTopic().getInstanceTopicId());
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

	public Status getStatusById(int statusId) throws ApplicationUtilException {
		LOGGER.debug("ApplicationUtilServiceImpl: getStatusById: Executing");
		try {
			return applicationUtilDao.getStatusById(statusId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationUtilException(e);
		}
	}

	public void updateNewQuizToInstanceTopics(Quiz quiz, Integer topicId) throws ApplicationUtilException {
		
		List<InstanceTopic> instanceTopics = applicationUtilDao.getInstanceTopicByTopicId(topicId);
		try {
			for(InstanceTopic instanceTopic : instanceTopics){
				if(null == instanceTopic.getQuiz()){
					InstanceQuiz instanceQuiz = new InstanceQuiz();
					instanceQuiz.setCreatedTs(new Date());
					instanceQuiz.setQuiz(quiz);
					Status status = userDao.getStatusByDesc(AppConstants.STATUS_NOT_STARTED);
					instanceQuiz.setStatus(status);
					instanceTopic.setQuiz(instanceQuiz);
					userDao.saveOrUpdateInstanceTopic(instanceTopic);
					changeInstanceTopicStatusIfCompleted(instanceTopic.getInstanceTopicId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationUtilException(e);
		}
	}

	public void updateNewQuizOptionToInstanceQuiz(QuizOption quizOption, int quizId) throws ApplicationUtilException {
		
		List<InstanceQuiz> instanceQuizes = applicationUtilDao.getInstanceQuizesByQuizId(quizId);
		try {
			for(InstanceQuiz instanceQuiz : instanceQuizes){
				QuizAnswer quizAnswer = new QuizAnswer();
				quizAnswer.setQuizOption(quizOption);
				quizAnswer.setCreatedTs(new Date());
				Status status = userDao.getStatusByDesc(AppConstants.STATUS_NOT_STARTED);
				quizAnswer.setStatus(status);
				Set<Option> correctAnswers = new HashSet<Option>(quizOption.getCorrectAnswers());
				Set<Option> userAnswers = new HashSet<Option>();
				populateAnswers(correctAnswers, userAnswers);
				quizAnswer.setUserAnswers(new ArrayList<Option>(userAnswers));

				quizAnswer = userDao.saveQuizAnswer(quizAnswer);
				instanceQuiz.getQuizAnswers().add(quizAnswer);
				userDao.saveOrUpdateInstanceQuiz(instanceQuiz);
				changeInstanceQuizStatusIfCompleted(instanceQuiz.getInstanceQuizId());
				InstanceTopic instanceTopic = userDao.getInstanceTopicByInstanceQuizId(instanceQuiz.getInstanceQuizId());
				changeInstanceTopicStatusIfCompleted(instanceTopic.getInstanceTopicId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationUtilException(e);
		}
		
	}
	
	public Status getIncompleteStatus() throws ApplicationUtilException {
		
		if(incompleteStatus == null){
			incompleteStatus = getStatusById(AppConstants.STATUS_INCOMPLETE_ID);
		}
		return incompleteStatus;
	}
	
	public Status getNotStartedStatus() throws ApplicationUtilException {
		
		if(notStartedStatus == null){
			notStartedStatus = getStatusById(AppConstants.STATUS_NOT_STARTED_ID);
		}
		return notStartedStatus;
	}

	public Status getCompletedStatus() throws ApplicationUtilException {
		
		if(completedStatus == null){
			completedStatus = getStatusById(AppConstants.STATUS_COMPLETE_ID);
		}
		return completedStatus;
	}

}
