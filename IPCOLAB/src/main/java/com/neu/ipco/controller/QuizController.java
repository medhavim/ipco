/**
 * 
 */
package com.neu.ipco.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.neu.ipco.constants.AppConstants;
import com.neu.ipco.entity.ActivityOption;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.Quiz;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.exception.AdminException;
import com.neu.ipco.exception.ApplicationUtilException;
import com.neu.ipco.service.AdminService;

/**
 * @author harsh
 *
 */
@Controller
public class QuizController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6321490324812827969L;
	
	private Logger LOGGER = Logger.getLogger(QuizController.class);
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/manageQuiz.action")
	public String manageQuizAction(Model model, HttpSession session){
		
		LOGGER.debug("QuizController: manageQuizAction: Start");
		
		try {
			List<Topic> allTopics = adminService.loadAllTopics();
			
			session.setAttribute("allTopics", allTopics);
			
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("QuizController: manageQuizAction: End");
		return AppConstants.MANAGE_QUIZ;
	}
	
	@RequestMapping(value="/gotoAddQuiz.action")
	public String gotoAddQuizAction(@RequestParam("id") int topicId, Model model, HttpSession session){
		
		LOGGER.debug("QuizController: gotoAddQuizAction: Start");
		
		Quiz quiz = new Quiz();

		model.addAttribute("quiz", quiz);
		model.addAttribute("topicId", topicId);
		LOGGER.debug("QuizController: gotoAddQuizAction: End");
		return AppConstants.ADMIN_QUIZ;
	}
	
}
