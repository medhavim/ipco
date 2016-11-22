/**
 * 
 */
package com.neu.ipco.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.coyote.ajp.AjpProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.ipco.entity.ActivityAnswer;
import com.neu.ipco.entity.BasicInstanceUser;
import com.neu.ipco.entity.CustomizeInstanceUser;
import com.neu.ipco.entity.Diagnostic;
import com.neu.ipco.entity.Instance;
import com.neu.ipco.entity.InstanceModule;
import com.neu.ipco.entity.InstanceQuiz;
import com.neu.ipco.entity.InstanceTopic;
import com.neu.ipco.entity.InstanceType;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.QuizAnswer;
import com.neu.ipco.entity.RelatedDiagnostic;
import com.neu.ipco.entity.Status;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.entity.User;
import com.neu.ipco.exception.AdminException;
import com.neu.ipco.exception.ApplicationUtilException;
import com.neu.ipco.exception.UserException;
import com.neu.ipco.service.AdminDiagnosticService;
import com.neu.ipco.service.AdminService;
import com.neu.ipco.service.ApplicationUtilService;
import com.neu.ipco.service.UserService;
import com.neu.ipco.utility.AppConstants;

/**
 * @author harsh
 *
 */
@Controller
public class UserController {

	private Logger LOGGER = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ApplicationUtilService applicationUtilService;
	
	@Autowired
	private AdminDiagnosticService adminDiagnosticService;
	
	@RequestMapping(value="/gotoBasicProfile.action", method=RequestMethod.POST)
	public String gotoBasicProfileAction(Model model, HttpSession session){
		
		LOGGER.debug("UserController: gotoBasicProfileAction: Start");
		
		try {
			
			User user = (User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER);
			
			InstanceType instanceType = applicationUtilService.getInstanceTypeByDesc(AppConstants.INSTANCE_TYPE_BASIC);
			BasicInstanceUser basicInstanceUser = userService.getInstanceByUserId(user.getUserId(), instanceType.getInstanceTypeId());
			
			if(null == basicInstanceUser){
				
				basicInstanceUser = new BasicInstanceUser();
				basicInstanceUser.setUser(user);

				Instance instance = new Instance();
				instance.setInstanceType(instanceType);
				instance.setInstanceName("Basic Instance");
				instance.setCreatedTs(new Date());
				instance.setUpdatedTs(new Date());
				userService.populateTopicsForInstance(instance);
				instance = userService.saveInstance(instance);
				basicInstanceUser.setInstance(instance);
				basicInstanceUser.setCreatedTs(new Date());
				basicInstanceUser = userService.saveBasicInstance(basicInstanceUser);
			}
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE, basicInstanceUser.getInstance());
			
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (ApplicationUtilException e1){
			e1.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (Exception e){
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("UserController: gotoBasicProfileAction: End");
		return AppConstants.USER_TOPIC;
	}
	
	@RequestMapping(value="/gotoActivityAnswer.action", method=RequestMethod.POST)
	public String gotoActivityAnswerAction(@RequestParam("id") int activityAnswerId, Model model, HttpSession session){
		
		LOGGER.debug("UserController: gotoActivityAnswerAction: Start");
		
		try {
			
			InstanceModule instanceModule = (InstanceModule) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_MODULE);
			instanceModule.reorder();
			instanceModule.prepareStack(activityAnswerId);
			Status incompleteStatus = applicationUtilService.getIncompleteStatus();
			userService.updateActivityAnswerStatus(instanceModule.getCurrActivity(), incompleteStatus);
			
			InstanceTopic instanceTopic = userService.getInstanceTopicById(instanceModule.getInstanceTopic().getInstanceTopicId());
			instanceTopic.reorder();
//			instanceTopic.prepareStack(instanceModule);
			userService.updateInstanceModuleStatus(instanceModule, incompleteStatus);
			userService.updateInstanceTopicStatus(instanceTopic, incompleteStatus);
			
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_MODULE, instanceModule);
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_TOPIC, instanceTopic);
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (Exception e){
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("UserController: gotoActivityAnswerAction: End");
		return AppConstants.USER_ACTIVITY;
	}
	
	
	@RequestMapping(value="/gotoModule.action", method=RequestMethod.POST)
	public String gotoModuleAction(@RequestParam("id") int instanceModuleId, Model model, HttpSession session){
		
		LOGGER.debug("UserController: gotoModuleAction: Start");
		
		try {
			
			InstanceModule instanceModule = userService.geInstanceModuleById(instanceModuleId);
			instanceModule.reorder();
			instanceModule.prepareStack();
			Status incompleteStatus = applicationUtilService.getIncompleteStatus();
			userService.updateActivityAnswerStatus(instanceModule.getCurrActivity(), incompleteStatus);
			
			InstanceTopic instanceTopic = userService.getInstanceTopicById(instanceModule.getInstanceTopic().getInstanceTopicId());
			instanceTopic.reorder();
//			instanceTopic.prepareStack(instanceModule);
			userService.updateInstanceModuleStatus(instanceModule, incompleteStatus);
			userService.updateInstanceTopicStatus(instanceTopic, incompleteStatus);
			
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_MODULE, instanceModule);
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_TOPIC, instanceTopic);
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (Exception e){
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("UserController: gotoModuleAction: End");
		return AppConstants.USER_ACTIVITY;
	}
	
	@RequestMapping(value="/gotoUserQuiz.action", method=RequestMethod.POST)
	public String gotoUserQuizAction(@RequestParam("id") int instanceQuizId, Model model, HttpSession session){
		
		LOGGER.debug("UserController: gotoUserQuizAction: Start");
		
		try {
			InstanceQuiz quiz = userService.getInstanceQuizById(instanceQuizId);
			if(quiz.getStatus().getStatusId() != AppConstants.STATUS_COMPLETE_ID){
				QuizAnswer currentQuizAnswer = userService.getFirstQuizAnswer(quiz);
				Status statusIncomplete = applicationUtilService.getStatusById(AppConstants.STATUS_INCOMPLETE_ID);
				userService.updateQuizAnswerStatus(currentQuizAnswer, statusIncomplete);
				userService.updateQuizStatus(quiz, statusIncomplete);
				session.setAttribute(AppConstants.SESSION_ATTRIBUTE_CURRENT_QUIZ_ANSWER, currentQuizAnswer);
			}
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_QUIZ, quiz);
			
			LOGGER.debug("UserController: gotoUserQuizAction: End");
			if(quiz.getStatus().getStatusId() == AppConstants.STATUS_COMPLETE_ID){
				return AppConstants.USER_QUIZ_REPORT;
			}
			return AppConstants.USER_QUIZ;
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (ApplicationUtilException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (Exception e){
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
	}
	
	@RequestMapping(value="/saveActivity.action", method=RequestMethod.POST)
	public String saveActivityAction(@RequestParam("navType") String navType, 
			HttpServletRequest request, Model model, HttpSession session){
		
		LOGGER.debug("UserController: saveActivityAction: Start");
		
		try {
			
//			Steps to save activity:
//			1. Get instanceModule from session.
			InstanceModule instanceModule = (InstanceModule) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_MODULE);
//			2. Get currentActivity from instanceModule
			ActivityAnswer currActivity = instanceModule.getCurrActivity();
//			3. populate currActivity status to complete
			Status statusComplete = applicationUtilService.getStatusById(AppConstants.STATUS_COMPLETE_ID);
			userService.updateActivityAnswerStatus(currActivity, statusComplete);
//			4. populate userAnswers to currActivity
			int activityTemplateId = currActivity.getActivityOption().getActivity().getActivityTemplate().getActivityTemplateId();
			currActivity.setUpdatedTs(new Date());
			if(activityTemplateId != AppConstants.TEMPLATE_INFO){
				Set<Option> userAnswers = updateAnswers(request, activityTemplateId, currActivity.getAnswers());
				if(!userAnswers.isEmpty()){
					currActivity.setAnswers(userAnswers);
				}
			}
//			5. update currActivity to db
			currActivity = userService.saveOrUpdateActivityAnswer(currActivity);
//			5.1. save the instanceModule onto session.
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_MODULE, instanceModule);
//			6. transfer control to navActiviyAction
			LOGGER.debug("UserController: saveActivityAction: End");
			return navigateActivityAction(navType, model, session);
		} catch (ApplicationUtilException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (Exception e){
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
	}
	
	@RequestMapping(value="/navigateActivity.action", method=RequestMethod.POST)
	public String navigateActivityAction(@RequestParam(value="navType", required=false) String navType, 
			Model model, HttpSession session){
		try {
			Status statusIncomplete = applicationUtilService.getStatusById(AppConstants.STATUS_INCOMPLETE_ID);
			Status statusComplete = applicationUtilService.getStatusById(AppConstants.STATUS_COMPLETE_ID);
			InstanceModule instanceModule = (InstanceModule) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_MODULE);
			switch (navType) {
				case AppConstants.NAV_TYPE_PREV_ACTIVITY:
					instanceModule.configurePrevActivity();
					break;
				case AppConstants.NAV_TYPE_NEXT_ACTIVITY:
					instanceModule.configureNextActivity();
					userService.updateActivityAnswerStatus(instanceModule.getCurrActivity(), statusIncomplete);
					break;
				case AppConstants.NAV_TYPE_PREV_MODULE:
					instanceModule = userService.getPrevInstanceModuleForNavigation(instanceModule);
					break;
				case AppConstants.NAV_TYPE_NEXT_MODULE:
					instanceModule = userService.getNextInstanceModuleForNavigation(instanceModule);
					
					break;
				case AppConstants.NAV_TYPE_TAKE_QUIZ:
					userService.updateInstanceModuleStatus(instanceModule, statusComplete);
					InstanceQuiz quiz = instanceModule.getInstanceTopic().getQuiz();
					if(quiz != null){
						return gotoUserQuizAction(quiz.getInstanceQuizId(), model, session);
					}else{
						return updateInstanceTopicToCompleteGotoUserInstance(statusComplete, model, session);
					}
					
				case AppConstants.NAV_TYPE_NEXT_QUIZ:
				case AppConstants.NAV_TYPE_PREVIOUS_QUIZ:
					return AppConstants.USER_QUIZ;
					
				case AppConstants.NAV_TYPE_GOTO_DASHBOARD:
					userService.updateInstanceModuleStatus(instanceModule, statusComplete);
					return navigateToUserInstanceFromActivityOrQuiz(model, session);
				case AppConstants.NAV_TYPE_QUIZ_FINISH:
					return updateInstanceTopicToCompleteGotoUserInstance(statusComplete, model, session);
				default:
					return navigateToUserInstanceFromActivityOrQuiz(model, session);
			}
			
			InstanceTopic instanceTopic = userService.getInstanceTopicById(instanceModule.getInstanceTopic().getInstanceTopicId());
			instanceTopic.reorder();
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_MODULE, instanceModule);
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_TOPIC, instanceTopic);
		} catch (ApplicationUtilException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		return AppConstants.USER_ACTIVITY;
	}

	private String updateInstanceTopicToCompleteGotoUserInstance(Status statusComplete, Model model, HttpSession session) throws UserException {
	
		InstanceQuiz instanceQuiz = (InstanceQuiz) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_QUIZ);
		InstanceTopic instanceTopicFromSession = (InstanceTopic) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_TOPIC);
		if(null != instanceTopicFromSession){
			userService.updateInstanceTopicStatus(instanceTopicFromSession, statusComplete);
		}else if(null != instanceQuiz){
			InstanceTopic instanceTopic = userService.getInstanceTopicByInstanceQuizId(instanceQuiz.getInstanceQuizId());
			if(null != instanceTopic){
				userService.updateInstanceTopicStatus(instanceTopic, statusComplete);
			}
		}
		return navigateToUserInstanceFromActivityOrQuiz(model, session);
	}

	private String navigateToUserInstanceFromActivityOrQuiz(Model model, HttpSession session) {
		Instance instance = (Instance) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE);
		if(null != instance){
			session.removeAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_MODULE);
			session.removeAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_TOPIC);
			session.removeAttribute(AppConstants.SESSION_ATTRIBUTE_CURRENT_QUIZ_ANSWER);
			session.removeAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_QUIZ);
			return loadInstanceAction(instance.getInstanceId(), model, session);
		}else {
			return AppConstants.ERROR_PAGE;
		}
	}

	@RequestMapping(value="/saveQuiz.action", method=RequestMethod.POST)
	public String saveQuizAction(@RequestParam("navType") String navType, HttpServletRequest request, Model model, HttpSession session){
		
		LOGGER.debug("UserController: saveQuizAction: Start");
		
		try {

			InstanceQuiz instanceQuiz = (InstanceQuiz) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_QUIZ);
			QuizAnswer currentQuizAnswer = (QuizAnswer) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_CURRENT_QUIZ_ANSWER);
			Status statusIncomplete = applicationUtilService.getStatusById(AppConstants.STATUS_INCOMPLETE_ID);
			Status statusComplete = applicationUtilService.getStatusById(AppConstants.STATUS_COMPLETE_ID);
			int activityTemplateId = currentQuizAnswer.getQuizOption().getActivity().getActivityTemplate().getActivityTemplateId();
			
			updateQuizStatusScore(currentQuizAnswer, navType, statusComplete, request, activityTemplateId, instanceQuiz, statusIncomplete);
			
			QuizAnswer nextQuizAnswer = userService.getNextCurrentQuizAnswer(instanceQuiz.getInstanceQuizId(), currentQuizAnswer.getQuizOption().getOrderNo(), navType);
			userService.updateQuizAnswerStatus(nextQuizAnswer, statusIncomplete);
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_CURRENT_QUIZ_ANSWER, nextQuizAnswer);
			
			instanceQuiz = userService.getInstanceQuizById(instanceQuiz.getInstanceQuizId());
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_QUIZ, instanceQuiz);
			
		} catch (ApplicationUtilException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (Exception e){
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		LOGGER.debug("UserController: saveQuizAction: End");
		return navigateActivityAction(navType, model, session);
	}
	
	private void updateInstanceQuizStatus(InstanceQuiz instanceQuiz, String navType, Status statusIncomplete, Status statusComplete) throws UserException {
		Status status = statusIncomplete;
		if(navType.equalsIgnoreCase(AppConstants.NAV_TYPE_QUIZ_FINISH)){
			status = statusComplete;
		}
		userService.updateQuizStatus(instanceQuiz, status);
	}

	private void updateQuizStatusScore(QuizAnswer currentQuizAnswer, String navType, Status statusComplete, 
			HttpServletRequest request, int activityTemplateId, InstanceQuiz instanceQuiz, 
			Status statusIncomplete) throws UserException {

		if(navType.equalsIgnoreCase(AppConstants.NAV_TYPE_NEXT_QUIZ) 
				|| navType.equalsIgnoreCase(AppConstants.NAV_TYPE_QUIZ_FINISH)){
			if(currentQuizAnswer.getStatus().getStatusId() != AppConstants.STATUS_COMPLETE_ID){
				updateUserAnswersToCurrentQuizAnswer(request, activityTemplateId, currentQuizAnswer);
				updateInstanceQuizStatus(instanceQuiz, navType, statusIncomplete, statusComplete);
				userService.updateQuizScore(instanceQuiz, currentQuizAnswer);
				userService.updateQuizAnswerStatus(currentQuizAnswer, statusComplete);
			}
		}
	}

	private void updateUserAnswersToCurrentQuizAnswer(HttpServletRequest request, int activityTemplateId,
			QuizAnswer currentQuizAnswer) throws UserException {
		if(activityTemplateId != AppConstants.TEMPLATE_INFO){
			Set<Option> userAnswers = updateAnswers(request, activityTemplateId, new HashSet<Option>(currentQuizAnswer.getUserAnswers()));
			if(!userAnswers.isEmpty()){
				currentQuizAnswer.setUserAnswers(new ArrayList<Option>(userAnswers));
			}
		}
	}

	private Set<Option> updateAnswers(HttpServletRequest request, Integer activityTemplateId, Set<Option> userOptions) throws UserException {
		
		Set<Option> userAnswers = new TreeSet<Option>();
		
		if(activityTemplateId == AppConstants.TEMPLATE_MCQ
				|| activityTemplateId == AppConstants.TEMPLATE_IMAGE_MCQ 
				|| activityTemplateId == AppConstants.TEMPLATE_VIDEO_MCQ){
			userAnswers = updateMCQOptoins(userOptions, request);
		}else if(activityTemplateId == AppConstants.TEMPLATE_YESNO
				|| activityTemplateId == AppConstants.TEMPLATE_IMAGE_YESNO 
				|| activityTemplateId == AppConstants.TEMPLATE_VIDEO_YESNO){
			userAnswers = updateYESNOOptions(userOptions, request);
		}else if(activityTemplateId == AppConstants.TEMPLATE_IMAGE_DESC || activityTemplateId == AppConstants.TEMPLATE_VIDEO_DESC){
			userAnswers = updateDescOptions(userOptions, request);
		}
		
		return userAnswers;
	}
	
	private Set<Option> updateMCQOptoins(Set<Option> userOptions, HttpServletRequest request) {
		Set<Option> userAnswers = new TreeSet<Option>();
		String[] paramValues = request.getParameterValues("selectedAnswer");
		if(null != paramValues){
			List<String> selectedAnswers = new ArrayList<String>(Arrays.asList(paramValues));
			
			for(Option o : userOptions){
				if(selectedAnswers.contains("selectedAnswer_"+o.getOptionId())){
					o.setIsCorrect(AppConstants.TRUE);
					o.setUpdatedTs(new Date());
				}else{
					o.setIsCorrect(AppConstants.FALSE);
					o.setUpdatedTs(new Date());
				}
				userAnswers.add(o);
			}
		}
		
		return userAnswers;
	}
	
	private Set<Option> updateYESNOOptions(Set<Option> userOptions, HttpServletRequest request) {
		
		String requestParam = request.getParameter("yesno-option");
		Set<Option> userAnswers = new TreeSet<Option>();
		
		if(null != requestParam){
			int correctAnswerId = Integer.valueOf(requestParam.split("_")[1]);
			for(Option o : userOptions){
				if(o.getOptionId() == correctAnswerId){
					o.setIsCorrect(AppConstants.TRUE);
					o.setUpdatedTs(new Date());
				}else{
					o.setIsCorrect(AppConstants.FALSE);
					o.setUpdatedTs(new Date());
				}
				userAnswers.add(o);
			}
		} else{
			return userOptions;
		}
		
		return userAnswers;
	}
	
	private Set<Option> updateDescOptions(Set<Option> userOptions, HttpServletRequest request) {
		
		String userAnswer = request.getParameter("userAnswer");
		Set<Option> userAnswers = new TreeSet<Option>();
		
		if(null != userAnswer){
			
			Option option = new Option();
			option.setOptionText(userAnswer.trim());
			option.setOrderNo(1);
			option.setIsCorrect("true");
			option.setCreatedTs(new Date());
			userAnswers.add(option);
			return userAnswers;
		}else{
			return userOptions;
		}
		
	}
	
	@RequestMapping(value="/customizeTutorial.action", method=RequestMethod.POST)
	public String customizeTutorialAction(HttpServletRequest request, Model model, HttpSession session){
		
		LOGGER.debug("UserController: customizeTutorialAction: Start");
		
		try {
			
			User user = (User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER);
			
			InstanceType instanceType = applicationUtilService.getInstanceTypeByDesc(AppConstants.INSTANCE_TYPE_CUSTOM);
			Instance instance = new Instance();
			instance.setInstanceType(instanceType);
			String instanceName = request.getParameter("instanceName");
			instance.setInstanceName(instanceName);
			instance.setCreatedTs(new Date());
			instance.setUpdatedTs(new Date());
			
			Set<Topic> topics = getTopicsOfDiagnosticFromRequest(request);
			
//			TODO Handle a scenarion where no topics are selected out of the diagnostic questions.
			
			userService.populateTopicsForInstance(new ArrayList<Topic>(topics), instance);
			
			instance = userService.saveInstance(instance);
			
			CustomizeInstanceUser customizeInstanceUser = (CustomizeInstanceUser) session.getAttribute("customInstance");
			
			if(null == customizeInstanceUser){
				
				customizeInstanceUser = new CustomizeInstanceUser();
				customizeInstanceUser.setUser(user);
			}
			customizeInstanceUser.getInstances().add(instance);
			customizeInstanceUser.setCreatedTs(new Date());
			customizeInstanceUser.setUpdatedTs(new Date());
			customizeInstanceUser = userService.saveOrUpdateCustomInstance(customizeInstanceUser);
			
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_CUSTOM_INSTANCE, customizeInstanceUser);
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE, instance);
			
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (ApplicationUtilException e1){
			e1.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (AdminException e1){
			e1.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (Exception e){
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("UserController: customizeTutorialAction: End");
		return AppConstants.USER_TOPIC;
	}

	private Set<Topic> getTopicsOfDiagnosticFromRequest(HttpServletRequest request) throws AdminException {
		Set<Topic> topics = new TreeSet<Topic>();
		Set<Integer> diagnosticIdSet = new HashSet<Integer>();
		Set<RelatedDiagnostic> relatedDiagnosticsSet = new HashSet<RelatedDiagnostic>();
		Enumeration<String> parameters = request.getParameterNames();
		while(parameters.hasMoreElements()){
			String param = (String) parameters.nextElement();
			if(param.contains("diagnostic_")){
				int diagnosticId = Integer.valueOf(param.split("_")[1]);
				try {
					Diagnostic diagnostic = adminDiagnosticService.getDiagnosticById(diagnosticId);
					String paramVal = request.getParameter(param);
					Option correctOption = null;
					for(Option optn : diagnostic.getOptions()){
						if(optn.getIsCorrect().equalsIgnoreCase(AppConstants.TRUE)){
							correctOption = optn;
							break;
						}
					}
					if(null != correctOption && correctOption.getOptionText().equalsIgnoreCase(paramVal)){
						topics.addAll(diagnostic.getTopics());
						if(!diagnostic.getRelatedDiagnostics().isEmpty()){
							diagnosticIdSet.add(diagnostic.getDiagnosticId());
							relatedDiagnosticsSet.addAll(diagnostic.getRelatedDiagnostics());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new AdminException(e);
				}
			}
		}
//		Populating the topics set with additional topics from related diagnostic questions.
		getTopicsFromRelatedDiagnostics(relatedDiagnosticsSet, diagnosticIdSet, topics);
		return topics;
	}
	
	private void getTopicsFromRelatedDiagnostics(Set<RelatedDiagnostic> relatedDiagnosticsSet,
			Set<Integer> diagnosticIdSet, Set<Topic> topics) {
		
		for(RelatedDiagnostic relatedDiagnostic : relatedDiagnosticsSet){
			
			boolean allQuestionsAnsweredCorrect = true;
			for(Diagnostic diagnostic : relatedDiagnostic.getDiagnostics()){
				if(!diagnosticIdSet.contains(diagnostic.getDiagnosticId())){
					allQuestionsAnsweredCorrect = false;
					break;
				}
			}
			if(allQuestionsAnsweredCorrect){
				topics.addAll(relatedDiagnostic.getTopics());
			}
		}
	}

	@RequestMapping(value="/loadInstance.action")
	public String loadInstanceAction(@RequestParam("id") int instanceId, Model model, HttpSession session){
		
		LOGGER.debug("UserController: loadInstanceAction: Start");
		
		try {
			
			Instance instance = userService.getInstanceById(instanceId);
			instance.reorder();
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE, instance);
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (Exception e){
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("UserController: loadInstanceAction: End");
		return AppConstants.USER_TOPIC;
	}
	
	@RequestMapping(value="/deleteInstance.action", method=RequestMethod.POST)
	public String deleteInstanceAction(@RequestParam("id") int instanceId, Model model, HttpSession session){
		
		LOGGER.debug("UserController: deleteInstanceAction: Start");
		
		try {
			CustomizeInstanceUser customizeInstanceUser = (CustomizeInstanceUser) session.getAttribute("customInstance");
			if(null == customizeInstanceUser){
				throw new UserException("Custom instance User not found in the session.");
			}
			
			Instance instance = userService.getInstanceById(instanceId);
			for(Iterator<Instance> itr = customizeInstanceUser.getInstances().iterator(); itr.hasNext();){
				Instance currInstance = itr.next();
				if(currInstance.getInstanceId() == instance.getInstanceId()){
					customizeInstanceUser.getInstances().remove(currInstance);
					userService.deleteInstance(instance);
					break;
				}
			}
//			userService.saveOrUpdateCustomInstance(customizeInstanceUser);
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_CUSTOM_INSTANCE, customizeInstanceUser);
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (Exception e){
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		
		LOGGER.debug("UserController: deleteInstanceAction: End");
		return AppConstants.USER_PROFILE;
	}
	
}
