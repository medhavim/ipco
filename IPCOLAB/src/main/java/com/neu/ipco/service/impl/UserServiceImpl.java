/**
 * 
 */
package com.neu.ipco.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.ipco.dao.ApplicationUtilDao;
import com.neu.ipco.dao.UserDao;
import com.neu.ipco.entity.ActivityAnswer;
import com.neu.ipco.entity.ActivityOption;
import com.neu.ipco.entity.BasicInstanceUser;
import com.neu.ipco.entity.CustomizeInstanceUser;
import com.neu.ipco.entity.Instance;
import com.neu.ipco.entity.InstanceModule;
import com.neu.ipco.entity.InstanceQuiz;
import com.neu.ipco.entity.InstanceTopic;
import com.neu.ipco.entity.Module;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.Quiz;
import com.neu.ipco.entity.QuizAnswer;
import com.neu.ipco.entity.QuizOption;
import com.neu.ipco.entity.Status;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.exception.ApplicationUtilException;
import com.neu.ipco.exception.UserException;
import com.neu.ipco.service.UserService;
import com.neu.ipco.utility.AppConstants;


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
//			Status status = userDao.getStatusByDesc(firstTopic?AppConstants.STATUS_INCOMPLETE:AppConstants.STATUS_NOT_STARTED);
			Status status = userDao.getStatusByDesc(AppConstants.STATUS_NOT_STARTED);
			instanceTopic.setStatus(status);
			instanceTopic.setCreatedTs(new Date());
			populateInstanceModule(topic.getModules(), instanceTopic.getInstanceModules());
			Quiz quiz = topic.getQuiz();
			if(null != quiz){
//				TODO create instance quiz and add to this topic
				InstanceQuiz instanceQuiz = new InstanceQuiz();
				instanceQuiz.setQuiz(quiz);
				instanceQuiz.setStatus(status);
				instanceQuiz.setCreatedTs(new Date());

				populateQuizAnswer(quiz.getQuizOptions(), instanceQuiz.getQuizAnswers());
				instanceTopic.setQuiz(instanceQuiz);
			}
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
	

	private void populateQuizAnswer(List<QuizOption> quizOptions, List<QuizAnswer> quizAnswers) throws UserException {
		LOGGER.debug("UserServiceImpl: populateQuizAnswer: Start");
		for(QuizOption quizOption : quizOptions){
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
			quizAnswers.add(quizAnswer);
		}
		LOGGER.debug("UserServiceImpl: populateQuizAnswer: End");
		
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

	public ActivityAnswer saveOrUpdateActivityAnswer(ActivityAnswer currActivity) throws UserException {
		LOGGER.debug("UserServiceImpl: saveOrUpdateActivityAnswer: Executing");
		try {
			return userDao.saveOrUpdateActivityAnswer(currActivity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	public InstanceModule saveOrUpdateInstanceModule(InstanceModule instanceModule) throws UserException {
		LOGGER.debug("UserServiceImpl: saveOrUpdateInstanceModule: Executing");
		try {
			return userDao.saveOrUpdateInstanceModule(instanceModule);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	public InstanceTopic saveOrUpdateInstanceTopic(InstanceTopic instanceTopic) throws UserException {
		LOGGER.debug("UserServiceImpl: saveOrUpdateInstanceTopic: Executing");
		try {
			return userDao.saveOrUpdateInstanceTopic(instanceTopic);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	public void deleteInstance(Instance instance) throws UserException {
		LOGGER.debug("UserServiceImpl: deleteInstance: Executing");
		try {
			userDao.deleteInstance(instance);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	public QuizAnswer getFirstQuizAnswer(InstanceQuiz quiz) throws UserException {
		
		LOGGER.debug("UserServiceImpl: getFirstQuizAnswer: Start");
		QuizAnswer currentQuizAnswer = null;
		List<QuizAnswer> quizAnswers = quiz.getQuizAnswers();
		Collections.sort(quizAnswers, new Comparator<QuizAnswer>() {

			public int compare(QuizAnswer qA1, QuizAnswer qA2) {
				return Integer.valueOf(qA1.getQuizOption().getOrderNo()).compareTo(Integer.valueOf(qA2.getQuizOption().getOrderNo()));
			}
		});
		for(QuizAnswer qa : quizAnswers){
			if(qa.getStatus().getStatusId() == AppConstants.STATUS_COMPLETE_ID){
				continue;
			}
			currentQuizAnswer = qa;
			break;
		}
		if(!quizAnswers.isEmpty() && null == currentQuizAnswer){
			currentQuizAnswer = quizAnswers.get(0);
		}
		
		LOGGER.debug("UserServiceImpl: getFirstQuizAnswer: End");
		return currentQuizAnswer;
	}

	public void updateQuizScore(InstanceQuiz instanceQuiz, QuizAnswer currentQuizAnswer) throws UserException {
		
		LOGGER.debug("UserServiceImpl: updateQuizScore: Start");
		int scoreSoFar = instanceQuiz.getScore();
		boolean wrongAnswer = false;
		
		List<Option> userAnswers = currentQuizAnswer.getUserAnswers();
		List<Option> correctAnswers = currentQuizAnswer.getQuizOption().getCorrectAnswers();
		
		for(Option correctAnswer : correctAnswers){
			for(Option userAnswer : userAnswers){
				if(correctAnswer.getOrderNo() == userAnswer.getOrderNo() && !correctAnswer.getIsCorrect().equalsIgnoreCase(userAnswer.getIsCorrect())){
					wrongAnswer = true;
				}
			}
		}
		
		if(!wrongAnswer){
			instanceQuiz.setScore(++scoreSoFar);
		}
		saveOrUpdateInstanceQuiz(instanceQuiz);
		LOGGER.debug("UserServiceImpl: updateQuizScore: End");
	}

	public void saveOrUpdateQuizAnswer(QuizAnswer currentQuizAnswer) throws UserException {
		LOGGER.debug("UserServiceImpl: saveOrUpdateQuizAnswer: Executing");
		try {
			userDao.saveOrUpdateQuizAnswer(currentQuizAnswer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	public QuizAnswer getNextCurrentQuizAnswer(int instanceQuizId, int orderNo, String navType) throws UserException {
		LOGGER.debug("UserServiceImpl: getNextCurrentQuizAnswer: Executing");
		try {
			if(navType.equalsIgnoreCase(AppConstants.NAV_TYPE_PREVIOUS_QUIZ)){
				orderNo--;
			}else if(navType.equalsIgnoreCase(AppConstants.NAV_TYPE_NEXT_QUIZ)){
				orderNo++;
			}
			return userDao.getNextCurrentQuizAnswer(instanceQuizId, orderNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	public void saveOrUpdateInstanceQuiz(InstanceQuiz instanceQuiz) throws UserException {
		LOGGER.debug("UserServiceImpl: saveOrUpdateInstanceQuiz: Executing");
		try {
			userDao.saveOrUpdateInstanceQuiz(instanceQuiz);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	public InstanceQuiz getInstanceQuizById(int instanceQuizId) throws UserException {
		LOGGER.debug("UserServiceImpl: getInstanceQuizById: Executing");
		try {
			return userDao.getInstanceQuizById(instanceQuizId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	public void updateQuizStatus(InstanceQuiz quiz, Status statusIncomplete) throws UserException {

		LOGGER.debug("UserServiceImpl: updateQuizStatus: Executing");
		if(quiz.getStatus().getStatusId() != AppConstants.STATUS_COMPLETE_ID){
			Status status = new Status(statusIncomplete.getStatusId(), statusIncomplete.getStatusDesc(), new Date());
			quiz.setStatus(status);
			saveOrUpdateInstanceQuiz(quiz);
		}
	}

	public void updateActivityAnswerStatus(ActivityAnswer currActivity, Status status) throws UserException {
		
		LOGGER.debug("UserServiceImpl: updateActivityAnswerStatus: Executing");
		if(currActivity.getStatus().getStatusId() != AppConstants.STATUS_COMPLETE_ID){
			Status statusIncomplete = new Status(status.getStatusId(), status.getStatusDesc(), new Date());
			currActivity.setStatus(statusIncomplete);
			saveOrUpdateActivityAnswer(currActivity);
		}
	}

	public void updateInstanceModuleStatus(InstanceModule currModule, Status status) throws UserException {
		
		LOGGER.debug("UserServiceImpl: updateInstanceModuleStatus: Executing");
		if(currModule.getStatus().getStatusId() != AppConstants.STATUS_COMPLETE_ID){
			Status statusIncomplete = new Status(status.getStatusId(), status.getStatusDesc(), new Date());
			currModule.setStatus(statusIncomplete);
			saveOrUpdateInstanceModule(currModule);
		}
	}

	public void updateInstanceTopicStatus(InstanceTopic instanceTopic, Status status) throws UserException {
		
		LOGGER.debug("UserServiceImpl: updateInstanceTopicStatus: Executing");
		if(instanceTopic.getStatus().getStatusId() != AppConstants.STATUS_COMPLETE_ID){
			Status statusIncomplete = new Status(status.getStatusId(), status.getStatusDesc(), new Date());
			instanceTopic.setStatus(statusIncomplete);
			saveOrUpdateInstanceTopic(instanceTopic);
		}
	}

	public InstanceModule getNextCurrentInstanceModule(int instanceTopicId, int orderNo) throws UserException {
		LOGGER.debug("UserServiceImpl: getNextCurrentInstanceModule: Executing");
		try {
			return userDao.getNextCurrentInstanceModule(instanceTopicId, orderNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	public InstanceModule getPrevInstanceModuleForNavigation(InstanceModule instanceModule) throws UserException {

		LOGGER.debug("UserServiceImpl: getPrevInstanceModuleForNavigation: Executing");
		int orderNo = instanceModule.getModule().getOrderNo();
		int instanceTopicId = instanceModule.getInstanceTopic().getInstanceTopicId();
		InstanceModule prevInstanceModule = getNextCurrentInstanceModule(instanceTopicId, --orderNo);
		if(null != prevInstanceModule){
			prevInstanceModule.reorder();
			prevInstanceModule.preparePreviousModuleStack();
			instanceModule = prevInstanceModule;
		}
		return instanceModule;
	}
	
	public InstanceModule getNextInstanceModuleForNavigation(InstanceModule instanceModule) throws UserException, ApplicationUtilException {

		LOGGER.debug("UserServiceImpl: getNextInstanceModuleForNavigation: Executing");
		Status statusComplete = applicationUtilDao.getStatusById(AppConstants.STATUS_COMPLETE_ID);
		Status statusIncomplete = applicationUtilDao.getStatusById(AppConstants.STATUS_INCOMPLETE_ID);
		updateInstanceModuleStatus(instanceModule, statusComplete);
		int orderNo = instanceModule.getModule().getOrderNo();
		int instanceTopicId = instanceModule.getInstanceTopic().getInstanceTopicId();
		InstanceModule nextInstanceModule = getNextCurrentInstanceModule(instanceTopicId, ++orderNo);
		if(null != nextInstanceModule){
			nextInstanceModule.reorder();
			nextInstanceModule.prepareNextModuleStack();
			updateActivityAnswerStatus(nextInstanceModule.getCurrActivity(), statusIncomplete);
			updateInstanceModuleStatus(nextInstanceModule, statusIncomplete);
			instanceModule = nextInstanceModule;
		}
		return instanceModule;
	}

	public void updateQuizAnswerStatus(QuizAnswer currentQuizAnswer, Status statusIncomplete) throws UserException {
		
		LOGGER.debug("UserServiceImpl: updateQuizAnswerStatus: Executing");
		if(currentQuizAnswer.getStatus().getStatusId() != AppConstants.STATUS_COMPLETE_ID){
			Status status = new Status(statusIncomplete.getStatusId(), statusIncomplete.getStatusDesc(), new Date());
			currentQuizAnswer.setStatus(status);
			saveOrUpdateQuizAnswer(currentQuizAnswer);
		}
	}

	@Override
	public InstanceTopic getInstanceTopicByInstanceQuizId(int instanceQuizId) throws UserException {
		LOGGER.debug("UserServiceImpl: getNextCurrentInstanceModule: Executing");
		try {
			return userDao.getInstanceTopicByInstanceQuizId(instanceQuizId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	@Override
	public CustomizeInstanceUser getCustomizeInstanceByUserId(int deleteUserId) throws UserException {
		LOGGER.debug("UserServiceImpl: getCustomizeInstanceByUserId: Executing");
		try {
			return userDao.getCustomizeInstanceByUserId(deleteUserId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
	}

	@Override
	public void updateInstanceQuizStatus(InstanceQuiz instanceQuiz, Status status) throws UserException {
		
		LOGGER.debug("UserServiceImpl: updateInstanceQuizStatus: Executing");
		if(instanceQuiz.getStatus().getStatusId() != AppConstants.STATUS_COMPLETE_ID){
			Status newStatus = new Status(status.getStatusId(), status.getStatusDesc(), new Date());
			instanceQuiz.setStatus(newStatus);
			saveOrUpdateInstanceQuiz(instanceQuiz);
		}
	}

}
