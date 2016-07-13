/**
 * 
 */
package com.neu.ipco.controller;

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

import com.neu.ipco.constants.PageConstants;
import com.neu.ipco.entity.Credential;
import com.neu.ipco.entity.User;
import com.neu.ipco.entity.UserType;
import com.neu.ipco.exception.ApplicationUtilException;
import com.neu.ipco.exception.AuthenticationException;
import com.neu.ipco.service.ApplicationUtilService;
import com.neu.ipco.service.AuthenticationService;

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
			userType.setUserTypeDesc("user");
			try {
				userType = applicationUtilService.getUserType(userType);
			} catch (ApplicationUtilException e) {
				LOGGER.debug("Exception: While fetching UserType");
				return PageConstants.ERROR_PAGE;
			}
			userRegister.setUserType(userType);
			
			Credential userLogin = new Credential();
			Credential newCredential = new Credential();
			
			model.addAttribute("userLogin", userLogin);
			model.addAttribute("newCredential", newCredential);
			model.addAttribute("userRegister", userRegister);
		}
		
		
		LOGGER.debug("AuthenticationController: loadAuthPage: End");
		return PageConstants.USER_AUTH_PAGE;
	}
	
	@RequestMapping(value="/login.action", method=RequestMethod.POST)
	public String loginAction(@ModelAttribute("userLogin") Credential credential, Model model, HttpSession session){
		
		LOGGER.debug("AuthenticationController: loginAction: Start");
		
		try {
			User user = authenticationService.validUser(credential);
			if(user == null){
				model.addAttribute("errorMsg", "usernameerr");
				return loadAuthPage(model, session);
			}else{
				session.setAttribute("user", user);
			}
		} catch (AuthenticationException e) {
			return PageConstants.ERROR_PAGE;
		}
		LOGGER.debug("AuthenticationController: loginAction: End");
		return PageConstants.USER_HOME;
	}
	
	
	@RequestMapping(value="/signUp.action", method=RequestMethod.POST)
	public String signUpAction(@ModelAttribute("userRegister") User user, Model model, HttpSession session){
		
		LOGGER.debug("AuthenticationController: signUpAction: Start");
		
		try {
			user = authenticationService.userRegister(user);
			session.setAttribute("user", user);
		} catch (AuthenticationException e) {
			return PageConstants.ERROR_PAGE;
		}
		LOGGER.debug("AuthenticationController: signUpAction: End");
		return PageConstants.USER_HOME;
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
}
