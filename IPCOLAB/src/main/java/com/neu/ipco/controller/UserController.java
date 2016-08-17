/**
 * 
 */
package com.neu.ipco.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.ipco.constants.AppConstants;
import com.neu.ipco.entity.BasicInstanceUser;
import com.neu.ipco.entity.CustomizeInstanceUser;
import com.neu.ipco.entity.Diagnostic;
import com.neu.ipco.entity.Instance;
import com.neu.ipco.entity.InstanceModule;
import com.neu.ipco.entity.InstanceTopic;
import com.neu.ipco.entity.InstanceType;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.entity.User;
import com.neu.ipco.exception.AdminException;
import com.neu.ipco.exception.ApplicationUtilException;
import com.neu.ipco.exception.UserException;
import com.neu.ipco.service.AdminDiagnosticService;
import com.neu.ipco.service.ApplicationUtilService;
import com.neu.ipco.service.AuthenticationService;
import com.neu.ipco.service.UserService;

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
	private ApplicationUtilService applicationUtilService;
	
	@Autowired
	private AdminDiagnosticService adminDiagnosticService;
	
	@RequestMapping(value="/gotoBasicProfile.action", method=RequestMethod.POST)
	public String gotoBasicProfileAction(Model model, HttpSession session){
		
		LOGGER.debug("UserController: gotoBasicProfileAction: Start");
		
		try {
			
			User user = (User) session.getAttribute("user");
			
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
			session.setAttribute("instance", basicInstanceUser.getInstance());
			
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (ApplicationUtilException e1){
			e1.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("UserController: gotoBasicProfileAction: End");
		return AppConstants.USER_TOPIC;
	}
	
	@RequestMapping(value="/gotoModule.action", method=RequestMethod.POST)
	public String gotoModuleAction(@RequestParam("id") int instanceModuleId, Model model, HttpSession session){
		
		LOGGER.debug("UserController: gotoModuleAction: Start");
		
		try {
			
			InstanceModule instanceModule = userService.geInstanceModuleById(instanceModuleId);
			instanceModule.reorder();
			instanceModule.prepareStack();
			
			InstanceTopic instanceTopic = userService.getInstanceTopicById(instanceModule.getInstanceTopic().getInstanceTopicId());
			instanceTopic.reorder();
			instanceTopic.prepareStack(instanceModule);
			
			session.setAttribute("instanceModule", instanceModule);
			session.setAttribute("instanceTopic", instanceTopic);
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("UserController: gotoModuleAction: End");
		return AppConstants.USER_ACTIVITY;
	}
	
	@RequestMapping(value="/customizeTutorial.action", method=RequestMethod.POST)
	public String customizeTutorialAction(HttpServletRequest request, Model model, HttpSession session){
		
		LOGGER.debug("UserController: customizeTutorialAction: Start");
		
		try {
			
			User user = (User) session.getAttribute("user");
			
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
			
			session.setAttribute("customInstance", customizeInstanceUser);
			session.setAttribute("instance", instance);
			
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (ApplicationUtilException e1){
			e1.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (AdminException e1){
			e1.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("UserController: customizeTutorialAction: End");
		return AppConstants.USER_TOPIC;
	}

	private Set<Topic> getTopicsOfDiagnosticFromRequest(HttpServletRequest request) throws AdminException {
		Set<Topic> topics = new TreeSet<Topic>();
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
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new AdminException(e);
				}
			}
		}
		return topics;
	}
	
	@RequestMapping(value="/loadInstance.action", method=RequestMethod.POST)
	public String loadInstanceAction(@RequestParam("id") int instanceId, Model model, HttpSession session){
		
		LOGGER.debug("UserController: loadInstanceAction: Start");
		
		try {
			
			Instance instance = userService.getInstanceById(instanceId);
			instance.reorder();
			session.setAttribute("instance", instance);
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("UserController: loadInstanceAction: End");
		return AppConstants.USER_TOPIC;
	}
	
	
}
