/**
 * 
 */
package com.neu.ipco.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.ipco.constants.PageConstants;
import com.neu.ipco.entity.Credential;
import com.neu.ipco.entity.User;

/**
 * @author Harsha
 *
 */
@Controller
public class AuthenticationController {

	@RequestMapping(value="/userAuth.action", method=RequestMethod.GET)
	public String loadAuthPage(Model model, HttpSession session){
		
		User userRegister = new User();
		Credential userLogin = new Credential();
		
		model.addAttribute("userLogin", userLogin);
		model.addAttribute("userRegister", userRegister);
		
		return PageConstants.USER_AUTH_PAGE;
	}
}
