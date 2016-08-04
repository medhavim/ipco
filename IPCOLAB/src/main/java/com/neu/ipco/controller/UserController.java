/**
 * 
 */
package com.neu.ipco.controller;

import java.util.Date;
import java.util.List;

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
import com.neu.ipco.entity.Instance;
import com.neu.ipco.entity.InstanceModule;
import com.neu.ipco.entity.InstanceTopic;
import com.neu.ipco.entity.InstanceType;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.entity.User;
import com.neu.ipco.entity.UserType;
import com.neu.ipco.exception.AdminException;
import com.neu.ipco.exception.ApplicationUtilException;
import com.neu.ipco.exception.UserException;
import com.neu.ipco.service.ApplicationUtilService;
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
	
}
