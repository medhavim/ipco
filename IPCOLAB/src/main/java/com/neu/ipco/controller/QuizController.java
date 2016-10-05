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
import com.neu.ipco.entity.QuizOption;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.exception.AdminException;
import com.neu.ipco.exception.ApplicationUtilException;
import com.neu.ipco.service.AdminService;
import com.neu.ipco.service.ApplicationUtilService;

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
	
	@Autowired
	private ApplicationUtilService applicationUtilService;
	
	
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
	
	@RequestMapping(value="/addQuizName.action")
	public String addQuizNameAction(@RequestParam("quizName") String quizName, 
			@RequestParam("quizDesc") String quizDesc, @RequestParam("quizForTopicId") int topicId,
			Model model, HttpSession session){
		
		LOGGER.debug("QuizController: addQuizNameAction: Start");
		
		try {
			Topic topic = adminService.getTopicById(topicId);
			Quiz quiz = new Quiz();
			quiz.setQuizName(quizName);
			quiz.setQuizDesc(quizDesc);
			topic.setQuiz(quiz);
			
			adminService.saveQuiz(quiz);
			adminService.saveOrUpdateTopic(topic);
			applicationUtilService.updateNewQuizToInstanceTopics(quiz, topic.getTopicId());
			return manageQuizAction(model, session);
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		} catch (ApplicationUtilException e) {
			return AppConstants.ERROR_PAGE;
		}
	}
	
	@RequestMapping(value="/gotoAddQuiz.action")
	public String gotoAddQuizAction(@RequestParam("quizId") int quizId, Model model, HttpSession session){
		
		LOGGER.debug("QuizController: gotoAddQuizAction: Start");
		
		QuizOption quizOption = new QuizOption();
		model.addAttribute("quizOption", quizOption);
		model.addAttribute("quizId", quizId);
		LOGGER.debug("QuizController: gotoAddQuizAction: End");
		return AppConstants.ADMIN_QUIZ;

	}
	
}
