/**
 * 
 */
package com.neu.ipco.controller;

import java.util.Collections;
import java.util.Comparator;
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

import com.neu.ipco.entity.BasicInstanceUser;
import com.neu.ipco.entity.CustomizeInstanceUser;
import com.neu.ipco.entity.Instance;
import com.neu.ipco.entity.InstanceModule;
import com.neu.ipco.entity.InstanceTopic;
import com.neu.ipco.entity.InstanceType;
import com.neu.ipco.entity.User;
import com.neu.ipco.entity.UserRole;
import com.neu.ipco.exception.AdminException;
import com.neu.ipco.exception.AuthenticationException;
import com.neu.ipco.exception.UserException;
import com.neu.ipco.service.AdminService;
import com.neu.ipco.service.ApplicationUtilService;
import com.neu.ipco.service.AuthenticationService;
import com.neu.ipco.service.UserService;
import com.neu.ipco.utility.AppConstants;
import com.neu.ipco.utility.ApplicationUtil;

/**
 * @author harsh
 *
 */
@Controller
public class ManageUserController {

	private Logger LOGGER = Logger.getLogger(ManageUserController.class);
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private ApplicationUtilService applicationUtilService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/manageUser.action")
	public String manageUserAction(Model model, HttpSession session){
		
		LOGGER.debug("ManageUserController: manageUserAction: Start");
		
		try {
			List<User> allUsers = adminService.loadAllUsers();
			List<UserRole> userRoles = adminService.loadAllUserRoles();
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_SORT_BY, AppConstants.DEFAULT_SORT_BY);
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_USER_ROLES, userRoles);
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_REGISTERED_USERS, allUsers);
			
		} catch (AdminException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		LOGGER.debug("ManageUserController: manageUserAction: End");
		return AppConstants.MANAGE_USER;
		
	}
	
	@RequestMapping(value="/sortUsers.action")
	public String sortUsersAction(@RequestParam("id") String sortBy, Model model, HttpSession session){
		
		LOGGER.debug("ManageUserController: sortUsersAction: Start");
		
		try {
			List<User> allUsers = (List<User>) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_REGISTERED_USERS);
			List<UserRole> userRoles = (List<UserRole>) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER_ROLES);
			if(null == allUsers){
				allUsers = adminService.loadAllUsers();
			}
			Comparator<User> sortComparator = ApplicationUtil.getUserSortComparator(sortBy);
			Collections.sort(allUsers, sortComparator);
			if(null == userRoles){
				userRoles = adminService.loadAllUserRoles();
			}
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_USER_ROLES, userRoles);
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_SORT_BY, sortBy);
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_REGISTERED_USERS, allUsers);
			
		} catch (Exception e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		LOGGER.debug("ManageUserController: sortUsersAction: End");
		return AppConstants.MANAGE_USER;
		
	}
	
	@RequestMapping(value="/manageUserProfile.action")
	public String manageUserProfileAction(@RequestParam(value="id", required=false) Integer userId, Model model, HttpSession session){
		
		LOGGER.debug("ManageUserController: manageUserProfileAction: Start");
		
		try {
//			Managing call to this method from various breadcrumbs
			User reviewUser = (User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_REVIEW_USER);
			if(null == reviewUser){
				if(null != userId){
					reviewUser = adminService.getUserById(userId);
					session.setAttribute(AppConstants.SESSION_ATTRIBUTE_REVIEW_USER, reviewUser);					
				}else{
//					TODO chain to check what the session holds and redirect to the page accordingly
//					All jsp pages will have a modal defined which will show the notification as to why it was abruptly
//					redirected to that page.
					throw new AuthenticationException();
				}
			}
			authenticationService.loadUserInstancesToSession(session, reviewUser);
		} catch (AdminException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		LOGGER.debug("ManageUserController: manageUserProfileAction: End");
		return AppConstants.REVIEW_USER_PROFILE;
	}
	
	@RequestMapping(value="/manageUserInstance.action")
	public String manageUserInstanceAction(@RequestParam(value="id", required=false) Integer instanceId, Model model, HttpSession session){
		
		LOGGER.debug("ManageUserController: manageUserInstanceAction: Start");
		
		try {
			Instance instance = (Instance) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE);
			if(null == instance){
				if(null != instanceId){
					instance = userService.getInstanceById(instanceId);
					instance.reorder();
					session.setAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE, instance);
				}else{
//					TODO chain to check what the session holds and redirect to the page accordingly
//					All jsp pages will have a modal defined which will show the notification as to why it was abruptly
//					redirected to that page.
					throw new AuthenticationException();
				}
			}
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (Exception e){
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("ManageUserController: manageUserInstanceAction: End");
		return AppConstants.REVIEW_USER_INSTANCE;
	}
	
	@RequestMapping(value="/manageInstanceModule.action", method=RequestMethod.POST)
	public String manageInstanceModuleAction(@RequestParam("id") int instanceModuleId, Model model, HttpSession session){
		
		LOGGER.debug("ManageUserController: manageInstanceModuleAction: Start");
		
		try {
			InstanceModule instanceModule = userService.geInstanceModuleById(instanceModuleId);
			InstanceTopic instanceTopic = userService.getInstanceTopicById(instanceModule.getInstanceTopic().getInstanceTopicId());
			instanceTopic.reorder();
			model.addAttribute(AppConstants.MODEL_ATTRIBUTE_CURRENT_INSTANCE_MODULE_ID, instanceModuleId);
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_TOPIC, instanceTopic);
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (Exception e){
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("ManageUserController: manageInstanceModuleAction: End");
		return AppConstants.REVIEW_USER_TOPIC;
	}
	
	@RequestMapping(value="/manageInstanceQuiz.action", method=RequestMethod.POST)
	public String manageInstanceQuizAction(@RequestParam("id") int instanceQuizId, Model model, HttpSession session){
		
		LOGGER.debug("ManageUserController: manageInstanceQuizAction: Start");
		
		try {
			InstanceTopic instanceTopic = userService.getInstanceTopicByInstanceQuizId(instanceQuizId);
			instanceTopic.reorder();
			model.addAttribute(AppConstants.MODEL_ATTRIBUTE_CURRENT_INSTANCE_QUIZ_ID, instanceQuizId);
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_INSTANCE_TOPIC, instanceTopic);
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (Exception e){
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("ManageUserController: manageInstanceQuizAction: End");
		return AppConstants.REVIEW_USER_TOPIC;
	}
	
	@RequestMapping(value="/deleteRegUser.action", method=RequestMethod.POST)
	public String deleteRegUserAction(@RequestParam("id") int deleteUserId, Model model, HttpSession session){
		
		LOGGER.debug("ManageUserController: deleteRegUserAction: Start");
		
		try {
			User deletableUser = adminService.getUserById(deleteUserId);
			InstanceType basicInstanceType = applicationUtilService.getInstanceTypeByDesc(AppConstants.INSTANCE_TYPE_BASIC);
			BasicInstanceUser basicInstanceUser = userService.getInstanceByUserId(deleteUserId, basicInstanceType.getInstanceTypeId());
			CustomizeInstanceUser customizeInstanceUser = userService.getCustomizeInstanceByUserId(deleteUserId);
			
			adminService.deleteBasicInstanceUser(basicInstanceUser);
			userService.deleteInstance(basicInstanceUser.getInstance());
			adminService.deleteCustomizeInstanceUser(customizeInstanceUser);
			adminService.deleteUser(deletableUser);
		} catch (UserException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (Exception e){
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		session.removeAttribute(AppConstants.SESSION_ATTRIBUTE_REGISTERED_USERS);
		String sortBy = (String) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_SORT_BY);
		
		LOGGER.debug("ManageUserController: deleteRegUserAction: End");
		return sortUsersAction(sortBy, model, session);
	}
	
	@RequestMapping(value="/addUserRole.action", method=RequestMethod.POST)
	public String addUserRoleAction(@RequestParam("userRoleDesc") String userRoleDesc, Model model, HttpSession session){
		
		LOGGER.debug("ManageUserController: addUserRoleAction: Start");
		
		try {
			UserRole userRole = new UserRole();
			userRole.setUserRoleDesc(userRoleDesc);
			userRole.setCreatedTs(new Date());
			adminService.saveOrUpdateUserRole(userRole);
			
		} catch (AdminException e){
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		session.removeAttribute(AppConstants.SESSION_ATTRIBUTE_USER_ROLES);
		String sortBy = (String) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_SORT_BY);
		
		LOGGER.debug("ManageUserController: addUserRoleAction: End");
		return sortUsersAction(sortBy, model, session);
	}
	
	@RequestMapping(value="/updateUserRole.action", method=RequestMethod.POST)
	public String updateUserRoleAction(@RequestParam("userRoleId") int userRoleId, @RequestParam("userRoleDesc") String userRoleDesc, Model model, HttpSession session){
		
		LOGGER.debug("ManageUserController: updateUserRoleAction: Start");
		
		try {
			UserRole userRole = adminService.geUserRoleById(userRoleId);
			if(userRole == null){
				userRole = new UserRole();
			}
			userRole.setUserRoleDesc(userRoleDesc);
			userRole.setUpdatedTs(new Date());
			adminService.saveOrUpdateUserRole(userRole);
			
		} catch (AdminException e){
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		session.removeAttribute(AppConstants.SESSION_ATTRIBUTE_USER_ROLES);
		String sortBy = (String) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_SORT_BY);
		
		LOGGER.debug("ManageUserController: updateUserRoleAction: End");
		return sortUsersAction(sortBy, model, session);
	}

}
