/**
 * 
 */
package com.neu.ipco.controller;

import java.io.Serializable;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.ipco.entity.Quiz;
import com.neu.ipco.entity.QuizOption;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.exception.AdminException;
import com.neu.ipco.exception.ApplicationUtilException;
import com.neu.ipco.service.AdminService;
import com.neu.ipco.service.ApplicationUtilService;
import com.neu.ipco.utility.AppConstants;

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
	
	@RequestMapping(value="/deleteQuiz.action", method=RequestMethod.POST)
	public String deleteQuizAction(@RequestParam("deletableId") int deletableId, HttpSession session, Model model){
		
		LOGGER.debug("AdminController: deleteQuizAction: Start");
		
		try {
			adminService.deleteQuizById(deletableId);
			List<Topic> allTopics = adminService.loadAllTopics();
			session.setAttribute("allTopics", allTopics);
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: deleteQuizAction: End");
		return AppConstants.MANAGE_QUIZ;
	}
	
	@RequestMapping(value="/deleteQuizOption.action", method=RequestMethod.POST)
	public String deleteQuizOptionAction(@RequestParam("deletableId") int deletableId, HttpSession session, Model model){
		
		LOGGER.debug("AdminController: deleteQuizOptionAction: Start");
		
		try {
			adminService.deleteQuizOptionById(deletableId);
			List<Topic> allTopics = adminService.loadAllTopics();
			session.setAttribute("allTopics", allTopics);
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: deleteQuizOptionAction: End");
		return AppConstants.MANAGE_QUIZ;
	}

	@RequestMapping(value="/gotoEditQuizOption.action", method=RequestMethod.POST)
	public String gotoEditQuizOptionAction(@RequestParam("id") int quizOptionId, HttpSession session, Model model){
		
		LOGGER.debug("AdminDiagnosticController: gotoEditQuizOptionAction: Start");
		
		try {
			QuizOption quizOption = adminService.getQuizOptionById(quizOptionId);
			model.addAttribute("quizOption", quizOption);
//			model.addAttribute("quizId", quizId);
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		} 
		
		LOGGER.debug("AdminDiagnosticController: gotoEditQuizOptionAction: End");
		return AppConstants.EDIT_QUIZ_OPTION;
	}
	
	@ResponseBody
	@RequestMapping(value="/renameQuiz.action", method=RequestMethod.POST)
	public String renameQuiz(@RequestParam("quizName") String quizName, @RequestParam("quizId") int quizId, HttpSession session){
		
		LOGGER.debug("AdminController: renameQuiz: Start");
		Quiz quiz;
		try {
			quiz = adminService.getQuizById(quizId);
			quiz.setQuizName(quizName);
			quiz.setUpdatedTs(new Date());
			adminService.saveOrUpdateQuiz(quiz);
			List<Topic> allTopics = adminService.loadAllTopics();
			
			session.setAttribute("allTopics", allTopics);
			LOGGER.debug("AdminController: renameQuiz: End");
			
			return "true";
		} catch (AdminException e) {
			return "error";
		}
	}
	
}
