/**
 * 
 */
package com.neu.ipco.controller;

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

import com.neu.ipco.constants.AppConstants;
import com.neu.ipco.entity.Module;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.exception.AdminException;
import com.neu.ipco.service.AdminService;

/**
 * @author Harsha
 *
 */
@Controller
public class AdminController {
	
	private Logger LOGGER = Logger.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/manageTutorial.action", method=RequestMethod.POST)
	public String manageTutorialAction(Model model, HttpSession session){
		
		LOGGER.debug("AdminController: manageTutorialAction: Start");
		
		try {
			List<Topic> allTopics = adminService.loadAllTopics();
			
			session.setAttribute("allTopics", allTopics);
			
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: manageTutorialAction: End");
		return AppConstants.MANAGE_TUTORIAL;
	}
	
	@RequestMapping(value="/addNewTopic.action", method=RequestMethod.POST)
	public String addNewTopicAction(@RequestParam("topicName") String topicName,
			@RequestParam("topicTypeId") int topicTypeId, Model model, HttpSession session){
		
		LOGGER.debug("AdminController: addNewTopicAction: Start");
		
		List<Topic> allTopics = (List<Topic>) session.getAttribute("allTopics");
		Topic newTopic = new Topic(topicName, topicTypeId);
		try {
			newTopic = adminService.addNewTopic(newTopic);
			allTopics.add(newTopic);
			
			session.setAttribute("allTopics", allTopics);
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: addNewTopicAction: End");
		return AppConstants.MANAGE_TUTORIAL;
	}

	@RequestMapping(value="/addNewModule.action", method=RequestMethod.POST)
	public String addNewModuleAction(@RequestParam("moduleName") String moduleName,
			@RequestParam("topicId") int topicId, Model model, HttpSession session){
		
		LOGGER.debug("AdminController: addNewModuleAction: Start");
		
		try {
			Topic topic = adminService.getTopicById(topicId);
			Module module = new Module(moduleName, topic);
			module = adminService.addNewModule(module);
			
			List<Topic> allTopics = adminService.loadAllTopics();
			session.setAttribute("allTopics", allTopics);
			model.addAttribute("moduleTopicId", module.getTopic().getTopicId());
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: addNewModuleAction: End");
		return AppConstants.MANAGE_TUTORIAL;
	}
	
	@RequestMapping(value="/deleteTopic.action", method=RequestMethod.POST)
	public String deleteTopicAction(@RequestParam("deletableId") int deletableId, HttpSession session, Model model){
		
		LOGGER.debug("AdminController: deleteTopicAction: Start");
		
		try {
			Topic topic = adminService.getTopicById(deletableId);
			adminService.deleteTopic(topic);
			
			List<Topic> allTopics = adminService.loadAllTopics();
			session.setAttribute("allTopics", allTopics);
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: deleteTopicAction: End");
		return AppConstants.MANAGE_TUTORIAL;
	}
	
	@RequestMapping(value="/deleteModule.action", method=RequestMethod.POST)
	public String deleteModuleAction(@RequestParam("deletableId") int deletableId, HttpSession session, Model model){
		
		LOGGER.debug("AdminController: deleteModuleAction: Start");
		
		try {
			Module module = adminService.getModuleById(deletableId);
			adminService.deleteModule(module);
			
			List<Topic> allTopics = adminService.loadAllTopics();
			session.setAttribute("allTopics", allTopics);
			model.addAttribute("moduleTopicId", module.getTopic().getTopicId());
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: deleteModuleAction: End");
		return AppConstants.MANAGE_TUTORIAL;
	}
	
	@ResponseBody
	@RequestMapping(value="/renameTopic.action", method=RequestMethod.POST)
	public String renameTopic(@RequestParam("topicName") String topicName, @RequestParam("topicId") int topicId, HttpSession session){
		
		LOGGER.debug("AdminController: renameTopic: Start");
		Topic topic;
		try {
			topic = adminService.getTopicById(topicId);
			topic.setTopicName(topicName);
			adminService.updateTopic(topic);
			List<Topic> allTopics = adminService.loadAllTopics();
			
			session.setAttribute("allTopics", allTopics);
			LOGGER.debug("AdminController: renameTopic: End");
			
			return "true";
		} catch (AdminException e) {
			return "error";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/renameModule.action", method=RequestMethod.POST)
	public String renameModule(@RequestParam("moduleName") String moduleName, @RequestParam("moduleId") int moduleId, HttpSession session){
		
		LOGGER.debug("AdminController: renameModule: Start");
		try {
			Module module = adminService.getModuleById(moduleId);
			module.setModuleName(moduleName);
			adminService.updateModule(module);
			List<Topic> allTopics = adminService.loadAllTopics();
			
			session.setAttribute("allTopics", allTopics);
			LOGGER.debug("AdminController: renameModule: End");
			
			return "true";
		} catch (AdminException e) {
			return "error";
		}
	}

}
