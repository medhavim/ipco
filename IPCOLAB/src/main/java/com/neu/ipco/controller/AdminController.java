/**
 * 
 */
package com.neu.ipco.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.neu.ipco.constants.AppConstants;
import com.neu.ipco.entity.ActivityOption;
import com.neu.ipco.entity.Credential;
import com.neu.ipco.entity.Module;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.exception.AdminException;
import com.neu.ipco.exception.ApplicationUtilException;
import com.neu.ipco.exception.UserException;
import com.neu.ipco.service.AdminService;
import com.neu.ipco.service.ApplicationUtilService;

/**
 * @author Harsha
 *
 */
@Controller
public class AdminController {
	
//	TODO Logic to handle duplicate related diagnostic questions to be added
	
	private Logger LOGGER = Logger.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ApplicationUtilService applicationUtilService;
	
	@RequestMapping(value="/adminHome.action", method=RequestMethod.GET)
	public String loadAdminHomePage(Model model, HttpSession session){
		
		LOGGER.debug("AuthenticationController: loadAdminHomePage: Redirecting");
		return AppConstants.ADMIN_HOME;
	}
	
	@RequestMapping(value="/manageTutorial.action")
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
	public String addNewTopicAction(@RequestParam("topicName") String topicName, @RequestParam("topicDesc") String topicDesc,
			@RequestParam("topicTypeId") int topicTypeId, Model model, HttpSession session){
		
		LOGGER.debug("AdminController: addNewTopicAction: Start");
		
		List<Topic> allTopics = (List<Topic>) session.getAttribute("allTopics");
		Topic newTopic = new Topic(topicName, topicDesc, topicTypeId);
		try {
			newTopic = adminService.addNewTopic(newTopic);
			if(topicTypeId == AppConstants.TOPIC_TYPE_ID_BASIC){
				applicationUtilService.updateNewTopicToBasicInstances(newTopic);
			}
			allTopics.add(newTopic);
			
			session.setAttribute("allTopics", allTopics);
		} catch (AdminException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		} catch (ApplicationUtilException e1){
			e1.printStackTrace();
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
			applicationUtilService.updateNewModuleToInstanceTopics(module);
			List<Topic> allTopics = adminService.loadAllTopics();
			session.setAttribute("allTopics", allTopics);
			model.addAttribute("moduleTopicId", module.getTopic().getTopicId());
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		} catch (ApplicationUtilException e1){
			e1.printStackTrace();
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
			topic.setUpdatedTs(new Date());
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
			module.setUpdatedTs(new Date());
			adminService.updateModule(module);
			List<Topic> allTopics = adminService.loadAllTopics();
			
			session.setAttribute("allTopics", allTopics);
			LOGGER.debug("AdminController: renameModule: End");
			
			return "true";
		} catch (AdminException e) {
			return "error";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="/updateTopicDesc.action", method=RequestMethod.POST)
	public String updateTopicDescAction(@RequestParam("topicDesc") String topicDesc, @RequestParam("topicId") int topicId, HttpSession session){
		
		LOGGER.debug("AdminController: updateTopicDescAction: Start");
		Topic topic;
		try {
			topic = adminService.getTopicById(topicId);
			topic.setTopicDesc(topicDesc);
			topic.setUpdatedTs(new Date());
			adminService.updateTopic(topic);
			List<Topic> allTopics = adminService.loadAllTopics();
			
			session.setAttribute("allTopics", allTopics);
			LOGGER.debug("AdminController: updateTopicDescAction: End");
			
			return "true";
		} catch (AdminException e) {
			return "error";
		}
	}
	
	@RequestMapping(value="/gotoAddActivity.action", method=RequestMethod.POST)
	public String gotoAddActivity(@RequestParam("moduleId") int moduleId, HttpSession session, Model model){
		
		LOGGER.debug("AdminController: gotoAddActivity: Start");
		
		ActivityOption activityOption = new ActivityOption();
		activityOption.getModule().setModuleId(moduleId);
		
		model.addAttribute("activityOption", activityOption);
		
		LOGGER.debug("AdminController: gotoAddActivity: End");
		return AppConstants.ADMIN_ACTIVITY;
	}
	
	@RequestMapping(value="/deleteActivity.action", method=RequestMethod.POST)
	public String deleteActivityAction(@RequestParam("deletableId") int deletableId, HttpSession session, Model model){
		
		LOGGER.debug("AdminController: deleteActivityAction: Start");
		
		try {
			ActivityOption activityOption = adminService.getActivityOptionById(deletableId);
			adminService.deleteActivityOption(activityOption);
			
			List<Topic> allTopics = adminService.loadAllTopics();
			session.setAttribute("allTopics", allTopics);
			model.addAttribute("moduleTopicId", activityOption.getModule().getTopic().getTopicId());
			model.addAttribute("activityModuleId", activityOption.getModule().getModuleId());
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: deleteActivityAction: End");
		return AppConstants.MANAGE_TUTORIAL;
	}
	
	@RequestMapping(value="/gotoEditActivity.action", method=RequestMethod.POST)
	public String gotoEditActivityAction(@RequestParam("id") int activityOptionId, HttpSession session, Model model){
		
		LOGGER.debug("AdminController: editActivityAction: Start");
		
		try {
			ActivityOption activityOption = adminService.getActivityOptionById(activityOptionId);
			model.addAttribute("activityOption", activityOption);
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: editActivityAction: End");
		return AppConstants.EDIT_ACTIVITY;
	}
	
	@RequestMapping(value="/editActivity.action", method=RequestMethod.POST)
	public String editActivity(@ModelAttribute("activityOption") ActivityOption newActivityOption, 
			@RequestParam(value="uploadFile",required=false) MultipartFile uploadFile,
//			@RequestParam(value="card1File",required=false) MultipartFile card1File,
//			@RequestParam(value="card2File",required=false) MultipartFile card2File,
//			@RequestParam(value="card3File",required=false) MultipartFile card3File,
			HttpServletRequest request, HttpSession session, Model model){
		
		LOGGER.debug("AdminController: editActivity: Start");
		
		try {
			
			int activityOptionId = newActivityOption.getActivityOptionId();
			int activityTemplateId = newActivityOption.getActivity().getActivityTemplate().getActivityTemplateId();
			ActivityOption currActivityOption = adminService.getActivityOptionById(activityOptionId);
			if(activityTemplateId != AppConstants.TEMPLATE_INFO){
				adminService.deleteOptionsByActivityOptionId(activityOptionId);
				Set<Option> options = populateOptions(request, activityTemplateId, 
						uploadFile);
//					card1File, card2File, card3File);
				currActivityOption.getOptions().clear();
				currActivityOption.setOptions(options);
			}
			
			currActivityOption.getActivity().setUpdatedTs(new Date());
			currActivityOption.getActivity().setActivityText(newActivityOption.getActivity().getActivityText());
			currActivityOption.setUpdatedTs(new Date());
			
			currActivityOption = adminService.editActivity(currActivityOption);
			
			List<Topic> allTopics = adminService.loadAllTopics();
			session.setAttribute("allTopics", allTopics);
			model.addAttribute("moduleTopicId", currActivityOption.getModule().getTopic().getTopicId());
			model.addAttribute("activityModuleId", currActivityOption.getModule().getModuleId());
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: editActivity: End");
		return AppConstants.MANAGE_TUTORIAL;
	}
	
	@RequestMapping(value="/addActivity.action", method=RequestMethod.POST)
	public String addActivity(@ModelAttribute("activityOption") ActivityOption activityOption, 
			@RequestParam("activityTemplate") int activityTemplateId,
			@RequestParam(value="uploadFile",required=false) MultipartFile uploadFile,
//			@RequestParam(value="card1File",required=false) MultipartFile card1File,
//			@RequestParam(value="card2File",required=false) MultipartFile card2File,
//			@RequestParam(value="card3File",required=false) MultipartFile card3File,
			HttpServletRequest request, HttpSession session, Model model){
		
		LOGGER.debug("AdminController: addActivity: Start");
		
		try {
			activityOption.getActivity().setCreatedTs(new Date());
			activityOption.getActivity().getActivityTemplate().setActivityTemplateId(activityTemplateId);
			activityOption.setCreatedTs(new Date());
			if(activityTemplateId != AppConstants.TEMPLATE_INFO){
				Set<Option> options = populateOptions(request, activityTemplateId, 
						uploadFile);
//					card1File, card2File, card3File);
				activityOption.setOptions(options);
			}
			
			activityOption = adminService.addNewActivity(activityOption);
			
			applicationUtilService.updateNewActivityToInstanceModule(activityOption);
			
			List<Topic> allTopics = adminService.loadAllTopics();
			session.setAttribute("allTopics", allTopics);
			model.addAttribute("moduleTopicId", activityOption.getModule().getTopic().getTopicId());
			model.addAttribute("activityModuleId", activityOption.getModule().getModuleId());
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		} catch (ApplicationUtilException e1){
			e1.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: addActivity: End");
		return AppConstants.MANAGE_TUTORIAL;
	}
	
	private Set<Option> populateOptions(HttpServletRequest request, Integer activityTemplateId, 
			MultipartFile uploadFile) throws AdminException {
//			MultipartFile card1File, MultipartFile card2File, MultipartFile card3File) throws AdminException {
		
		Set<Option> options = new TreeSet<Option>();
		int orderNo = 0;
		
		if(activityTemplateId == AppConstants.TEMPLATE_MCQ){
			options = populateMCQOptoins(options, request, orderNo);
		}else if(activityTemplateId == AppConstants.TEMPLATE_YESNO){
			options = populateYESNOOptions(options, request, orderNo);
		}else{
			if(activityTemplateId <= AppConstants.TEMPLATE_IMAGE_YESNO){
				options = populateImageOption(options, request, uploadFile, orderNo, "image");
			}else if(activityTemplateId <= AppConstants.TEMPLATE_VIDEO_YESNO){
				options = populateImageOption(options, request, uploadFile, orderNo, "video");
			}
			orderNo++;
			if(activityTemplateId == AppConstants.TEMPLATE_IMAGE_DESC || activityTemplateId == AppConstants.TEMPLATE_VIDEO_DESC){
				options = populateDescOptions(options, request, orderNo);
			}else if(activityTemplateId == AppConstants.TEMPLATE_IMAGE_MCQ || activityTemplateId == AppConstants.TEMPLATE_VIDEO_MCQ){
				options = populateMCQOptoins(options, request, orderNo);
			}else if(activityTemplateId == AppConstants.TEMPLATE_IMAGE_YESNO || activityTemplateId == AppConstants.TEMPLATE_VIDEO_YESNO){
				options = populateYESNOOptions(options, request, orderNo);
			}
		}
		return options;
	}

	private Set<Option> populateImageOption(Set<Option> options, HttpServletRequest request, MultipartFile uploadFile, int orderNo, String fileType) throws AdminException{
		String imageUrl = adminService.generateFilePath(uploadFile, fileType);
		if(null != imageUrl){
			Option option = new Option();
			option.setOptionText(imageUrl);
			option.setOrderNo(++orderNo);
			option.setIsCorrect("true");
			option.setCreatedTs(new Date());
			options.add(option);
		}
		return options;
	}

	private Set<Option> populateDescOptions(Set<Option> options, HttpServletRequest request, int orderNo) {
		
		String idealAnswer = request.getParameter("idealAnswer").trim();
		
		Option option = new Option();
		option.setOptionText(idealAnswer);
		option.setOrderNo(++orderNo);
		option.setIsCorrect("true");
		option.setCreatedTs(new Date());
		options.add(option);
		return options;
	}

	private Set<Option> populateYESNOOptions(Set<Option> options, HttpServletRequest request, int orderNo) {
		
		Option option1 = new Option();
		option1.setOptionText(request.getParameter("yesno-option"));
		option1.setOrderNo(++orderNo);
		option1.setIsCorrect("true");
		option1.setCreatedTs(new Date());
		
		Option option2 = new Option();
		option2.setOptionText(request.getParameter("yesno-option").equalsIgnoreCase("Yes")?"No":"Yes");
		option2.setOrderNo(++orderNo);
		option2.setIsCorrect("false");
		option2.setCreatedTs(new Date());
		
		options.add(option1);
		options.add(option2);
		
		return options;
	}

	private Set<Option> populateMCQOptoins(Set<Option>options, HttpServletRequest request, int orderNo) {
		List<String> correctAnswers = new ArrayList<String>(Arrays.asList(request.getParameterValues("correctAnswer")));
		
		Enumeration<String> parameters = request.getParameterNames();
		
		while(parameters.hasMoreElements()){
			String param = (String) parameters.nextElement();
			if(param.contains("option")){
				Option option = new Option();
				option.setOptionText(request.getParameter(param).trim());
				option.setOrderNo(orderNo + Integer.valueOf(param.split("_")[1]));
				option.setIsCorrect(correctAnswers.contains(param)?"true":"false");
				option.setCreatedTs(new Date());
				options.add(option);
			}
		}
		return options;
	}

}
