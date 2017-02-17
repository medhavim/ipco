/**
 * 
 */
package com.neu.ipco.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.ipco.entity.Credential;
import com.neu.ipco.entity.User;
import com.neu.ipco.entity.UserRole;
import com.neu.ipco.entity.UserType;
import com.neu.ipco.exception.ApplicationUtilException;
import com.neu.ipco.exception.AuthenticationException;
import com.neu.ipco.service.ApplicationUtilService;
import com.neu.ipco.service.AuthenticationService;
import com.neu.ipco.utility.AppConstants;

/**
 * @author Harsha
 *
 */
@Controller
public class AuthenticationController {
	
	Logger LOGGER = Logger.getLogger(AuthenticationController.class);
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private ApplicationUtilService applicationUtilService;
	
	@RequestMapping(value="/userAuth.action", method=RequestMethod.GET)
	public String loadAuthPage(Model model, HttpSession session){
		
		LOGGER.debug("AuthenticationController: loadAuthPage: Start");
		
		if(!model.containsAttribute("userLogin") || !model.containsAttribute("userRegister")){
			User userRegister = new User();
			
			UserType userType = new UserType();
			userType.setUserTypeDesc(AppConstants.USER_TYPE_USER);
			
			List<UserRole> userRoles = new ArrayList<UserRole>();
			try {
				userType = applicationUtilService.getUserType(userType);
				userRoles = applicationUtilService.getUserRoles();
			} catch (ApplicationUtilException e) {
				LOGGER.debug("Exception: While fetching UserType");
				return AppConstants.ERROR_PAGE;
			} catch (Exception e1){
				LOGGER.debug("Error occured while fetching userType and userRoles");
				return AppConstants.ERROR_PAGE;
			}
			
			userRegister.setUserType(userType);
			
			Credential userLogin = new Credential();
			Credential newCredential = new Credential();
			
			model.addAttribute("userRoles", userRoles);
			model.addAttribute("userLogin", userLogin);
			model.addAttribute("newCredential", newCredential);
			model.addAttribute("userRegister", userRegister);
		}
		
		
		LOGGER.debug("AuthenticationController: loadAuthPage: End");
		return AppConstants.USER_AUTH_PAGE;
	}

	@RequestMapping(value="/adminAuth.action", method=RequestMethod.GET)
	public String loadAdminAuthPage(Model model, HttpSession session){
		
		LOGGER.debug("AuthenticationController: loadAdminAuthPage: Start");
		
//		if(!model.containsAttribute("adminLogin")){

			Credential adminLogin = new Credential();
			Credential newAdminCredential = new Credential();
			
			model.addAttribute("adminLogin", adminLogin);
			model.addAttribute("newAdminCredential", newAdminCredential);
//		}
		
		
		LOGGER.debug("AuthenticationController: loadAdminAuthPage: End");
		return AppConstants.ADMIN_AUTH_PAGE;
	}
	
	@RequestMapping(value="/login.action", method=RequestMethod.POST)
	public String loginAction(@ModelAttribute("userLogin") Credential credential, Model model, HttpSession session){
		
		LOGGER.debug("AuthenticationController: loginAction: Start");
		
		try {
			User user = authenticationService.validUser(credential, AppConstants.USER_TYPE_USER);
			if(user == null){
				model.addAttribute("errorMsg", "usernameerr");
				return loadAuthPage(model, session);
			}else{
				authenticationService.updateUserLoginTimestamp(user);
				session.setAttribute(AppConstants.SESSION_ATTRIBUTE_USER, user);
				session.removeAttribute(AppConstants.SESSION_ATTRIBUTE_ADMIN);
			}
		} catch (AuthenticationException e) {
			return loadAuthPage(model, session);
		} catch (Exception e1){
			LOGGER.debug("Error occured while fetching userType and userRoles");
			return loadAuthPage(model, session);
		}
		LOGGER.debug("AuthenticationController: loginAction: End");
		return loadUserProfileAction(model, session);
	}
	
	@RequestMapping(value="/adminLogin.action", method=RequestMethod.POST)
	public String adminLoginAction(@ModelAttribute("adminLogin") Credential credential, Model model, HttpSession session){
		
		LOGGER.debug("AuthenticationController: adminLoginAction: Start");
		
		try {
			User admin = authenticationService.validUser(credential, AppConstants.USER_TYPE_ADMIN);
			if(admin == null){
				model.addAttribute("errorMsg", "usernameerr");
				return loadAdminAuthPage(model, session);
			}else{
				session.setAttribute(AppConstants.SESSION_ATTRIBUTE_ADMIN, admin);
				session.removeAttribute(AppConstants.SESSION_ATTRIBUTE_USER);
			}
		} catch (AuthenticationException e) {
			return loadAdminAuthPage(model, session);
		} catch (Exception e) {
			return loadAdminAuthPage(model, session);
		}
		LOGGER.debug("AuthenticationController: adminLoginAction: End");
		return AppConstants.ADMIN_HOME;
	}
	
	@RequestMapping(value="/signUp.action", method=RequestMethod.POST)
	public String signUpAction(@ModelAttribute("userRegister") User user, Model model, HttpSession session){
		
		LOGGER.debug("AuthenticationController: signUpAction: Start");
		
		try {
			user = authenticationService.userRegister(user);
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_USER, user);
			session.removeAttribute(AppConstants.SESSION_ATTRIBUTE_ADMIN);
		} catch (AuthenticationException e) {
			return loadAuthPage(model, session);
		} catch (Exception e) {
			return loadAuthPage(model, session);
		}
		LOGGER.debug("AuthenticationController: signUpAction: End");
		return loadUserProfileAction(model, session);
	}
	
	@RequestMapping(value="/resetCredential.action", method=RequestMethod.POST)
	public String resetCredAction(@ModelAttribute("newCredential") Credential credential, Model model, HttpSession session){
		
		LOGGER.debug("AuthenticationController: resetCredAction: Start");
		
		try {
			User user = authenticationService.resetCredentials(credential, AppConstants.USER_TYPE_USER);
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_USER, user);
			session.removeAttribute(AppConstants.SESSION_ATTRIBUTE_ADMIN);
		} catch (AuthenticationException e) {
			return loadAuthPage(model, session);
		}
		LOGGER.debug("AuthenticationController: resetCredAction: End");
		return loadUserProfileAction(model, session);
	}

	@RequestMapping(value="/resetAdminCredential.action", method=RequestMethod.POST)
	public String resetAdminCredAction(@ModelAttribute("newAdminCredential") Credential credential, Model model, HttpSession session){
		
		LOGGER.debug("AuthenticationController: resetAdminCredAction: Start");
		
		try {
			User user = authenticationService.resetCredentials(credential, AppConstants.USER_TYPE_ADMIN);
			session.setAttribute(AppConstants.SESSION_ATTRIBUTE_USER, user);
			session.removeAttribute(AppConstants.SESSION_ATTRIBUTE_ADMIN);
		} catch (AuthenticationException e) {
			return loadAdminAuthPage(model, session);
		}
		LOGGER.debug("AuthenticationController: resetAdminCredAction: End");
		return AppConstants.ADMIN_HOME;
	}
	
	@RequestMapping(value="/userProfile.action")
	public String loadUserProfileAction(Model model, HttpSession session){
		
		LOGGER.debug("AuthenticationController: loadUserProfileAction: Start");
		
		User user = (User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER);
		
		if(null == user){
			return loadAuthPage(model, session);
		}
		
		try {
			authenticationService.loadUserInstancesToSession(session, user);
		} catch (AuthenticationException e) {
			return loadAuthPage(model, session);
		} catch (Exception e) {
			return loadAuthPage(model, session);
		}
		LOGGER.debug("AuthenticationController: loadUserProfileAction: End");
		return AppConstants.USER_PROFILE;
	}
	
	@RequestMapping(value="/startTutorial.action")
	public String loadUserTutorialAction(Model model, HttpSession session){
		
		LOGGER.debug("AuthenticationController: loadUserTutorialAction: Start");
		
		User user = (User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER);
		
		if(null == user){
			return loadAuthPage(model, session);
		}
		
		LOGGER.debug("AuthenticationController: loadUserTutorialAction: End");
		return AppConstants.USER_HOME;
	}
	

	@RequestMapping(value="/logout.action", method=RequestMethod.GET)
	public String logoutUser(HttpSession session, Model model){
		
		User admin = (User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_ADMIN);
		if(session != null)
			session.invalidate();
		
		if(null != admin)
			return loadAdminAuthPage(model, session);
		else
			return loadAuthPage(model, session);
	}
	
	@ResponseBody
	@RequestMapping(value="/checkEmail.action", method = RequestMethod.POST)
	public String checkEmail(@RequestParam("email") String email)
	{
		try {
			if(authenticationService.checkEmail(email)){
				return "true";
			}else{
				return "false";
			}
		} catch (AuthenticationException e) {
			return "false";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/checkUsername.action", method = RequestMethod.POST)
	public String checkUsername(@RequestParam("username") String username)
	{
		try {
			if(authenticationService.checkUsername(username)){
				return "true";
			}else{
				return "false";
			}
		} catch (AuthenticationException e) {
			return "false";
		}
	}

	@ResponseBody
	@RequestMapping(value="/checkEmailReset.action", method = RequestMethod.POST)
	public String checkEmailReset(@RequestParam("email") String email)
	{
		try {
			User user = authenticationService.checkEmailReset(email, AppConstants.USER_TYPE_USER); 
			if(null == user){
				return "false";
			}else{
				return user.getCredential().getCredentialId()+" "+user.getCredential().getUsername();
			}
		} catch (AuthenticationException e) {
			return "false";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/checkAdminEmailReset.action", method = RequestMethod.POST)
	public String checkAdminEmailReset(@RequestParam("email") String email)
	{
		try {
			User user = authenticationService.checkEmailReset(email, AppConstants.USER_TYPE_ADMIN); 
			if(null == user){
				return "false";
			}else{
				return user.getCredential().getCredentialId()+" "+user.getCredential().getUsername();
			}
		} catch (AuthenticationException e) {
			return "false";
		}
	}
}
