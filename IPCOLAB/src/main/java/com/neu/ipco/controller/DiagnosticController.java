/**
 * 
 */
package com.neu.ipco.controller;

import java.io.Serializable;
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
import com.neu.ipco.entity.Diagnostic;
import com.neu.ipco.entity.DiagnosticCategory;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.exception.AdminException;
import com.neu.ipco.exception.ApplicationUtilException;
import com.neu.ipco.service.AdminDiagnosticService;
import com.neu.ipco.service.AdminService;

/**
 * @author harsh
 *
 */
@Controller
public class DiagnosticController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5900156469791655634L;
	
	private Logger LOGGER = Logger.getLogger(DiagnosticController.class);
	
	@Autowired
	private AdminDiagnosticService adminDiagnosticService;
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/manageDiagnostic.action")
	public String manageDiagnosticAction(Model model, HttpSession session){
		
		LOGGER.debug("AdminController: manageDiagnosticAction: Start");
		
		try {
			List<DiagnosticCategory> allCategories = adminDiagnosticService.loadAllCategories();
			
			session.setAttribute("allCategories", allCategories);
			
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: manageDiagnosticAction: End");
		return AppConstants.MANAGE_DIAGNOSTIC;
	}
	
	@RequestMapping(value="/addNewCategory.action", method=RequestMethod.POST)
	public String addNewCategoryAction(@RequestParam("categoryTitle") String categoryTitle, @RequestParam("categoryDesc") String categoryDesc,
			Model model, HttpSession session){
		
		LOGGER.debug("AdminController: addNewCategoryAction: Start");
		
		List<DiagnosticCategory> allCategories = (List<DiagnosticCategory>) session.getAttribute("allCategories");
		DiagnosticCategory newDiagnosticCategory = new DiagnosticCategory(categoryTitle, categoryDesc);
		try {
			newDiagnosticCategory = adminDiagnosticService.addNewCategory(newDiagnosticCategory);
			allCategories.add(newDiagnosticCategory);
			
			session.setAttribute("allCategories", allCategories);
		} catch (AdminException e) {
			e.printStackTrace();
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: addNewCategoryAction: End");
		return AppConstants.MANAGE_DIAGNOSTIC;
	}
	
	@RequestMapping(value="/deleteCategory.action", method=RequestMethod.POST)
	public String deleteCategoryAction(@RequestParam("deletableId") int deletableId, HttpSession session, Model model){
		
		LOGGER.debug("AdminController: deleteCategoryAction: Start");
		
		try {
			DiagnosticCategory diagnosticCategory = adminDiagnosticService.getDiagnosticCategoryById(deletableId);
			adminDiagnosticService.deleteCategory(diagnosticCategory);
			
			List<DiagnosticCategory> allCategories = adminDiagnosticService.loadAllCategories();
			session.setAttribute("allCategories", allCategories);
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: deleteCategoryAction: End");
		return AppConstants.MANAGE_DIAGNOSTIC;
	}
	
	@RequestMapping(value="/gotoAddDiagnostic.action", method=RequestMethod.POST)
	public String gotoAddDiagnosticAction(@RequestParam("categoryId") int categoryId, HttpSession session, Model model){
		
		LOGGER.debug("AdminController: gotoAddDiagnosticAction: Start");
		
		try {
			DiagnosticCategory diagnosticCategory = adminDiagnosticService.getDiagnosticCategoryById(categoryId);
			
			Diagnostic diagnostic =  new Diagnostic();
			diagnostic.setCategory(diagnosticCategory);
			List<Topic> customTopics = adminDiagnosticService.loadCustomTopics();
			
			model.addAttribute("customTopics", customTopics);
			model.addAttribute("diagnostic", diagnostic);
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: gotoAddDiagnosticAction: End");
		return AppConstants.ADMIN_DIAGNOSTIC;
	}
	
	@RequestMapping(value="/addDiagnostic.action", method=RequestMethod.POST)
	public String addDiagnosticAction(@ModelAttribute("diagnostic") Diagnostic diagnostic, 
			@RequestParam("activityTemplate") int activityTemplateId,
			HttpServletRequest request, HttpSession session, Model model){
		
		LOGGER.debug("AdminDiagnosticController: addDiagnosticAction: Start");
		
		try {
			diagnostic.getActivity().setCreatedTs(new Date());
			diagnostic.getActivity().getActivityTemplate().setActivityTemplateId(activityTemplateId);
			diagnostic.setCreatedTs(new Date());
			
			Set<Option> options = new TreeSet<Option>();
			int orderNo = 0;
			options = populateYESNOOptions(options, request, orderNo);
			diagnostic.setOptions(options);
			
			diagnostic.setCategory(adminDiagnosticService.getDiagnosticCategoryById(diagnostic.getCategory().getCategoryId()));
			
			Set<Topic> topics = populateTopics(request);
			diagnostic.setTopics(topics);
			
			diagnostic = adminDiagnosticService.addNewDiagnostic(diagnostic);
			
			List<DiagnosticCategory> allCategories = adminDiagnosticService.loadAllCategories();
			session.setAttribute("allCategories", allCategories);
			model.addAttribute("diagnosticCategoryId", diagnostic.getCategory().getCategoryId());
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		} 
		
		LOGGER.debug("AdminDiagnosticController: addDiagnosticAction: End");
		return AppConstants.MANAGE_DIAGNOSTIC;
	}
	
	private Set<Topic> populateTopics(HttpServletRequest request) throws AdminException {
		
		Set<Topic> topics = new TreeSet<Topic>();
		Enumeration<String> parameters = request.getParameterNames();
		while(parameters.hasMoreElements()){
			String param = (String) parameters.nextElement();
			if(param.contains("topic_")){
				int topicId = Integer.valueOf(param.split("_")[1]);
				try {
					Topic topic = adminService.getTopicById(topicId);
					topics.add(topic);
				} catch (Exception e) {
					e.printStackTrace();
					throw new AdminException(e);
				}
			}
		}
		return topics;
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
	
	@RequestMapping(value="/loadDiagnostic.action")
	public String loadDiagnosticAction(Model model, HttpSession session){
		
		LOGGER.debug("AdminController: loadDiagnosticAction: Start");
		
		try {
			List<DiagnosticCategory> allCategories = adminDiagnosticService.loadAllCategories();
			
			model.addAttribute("allCategories", allCategories);
			
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: loadDiagnosticAction: End");
		return AppConstants.USER_DIAGNOSTIC;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/renameCategory.action", method=RequestMethod.POST)
	public String renameCategory(@RequestParam("categoryTitle") String categoryTitle, @RequestParam("categoryId") int categoryId, HttpSession session){
		
		LOGGER.debug("AdminController: renameCategory: Start");
		DiagnosticCategory diagnosticCategory;
		try {
			diagnosticCategory = adminDiagnosticService.getDiagnosticCategoryById(categoryId);
			diagnosticCategory.setCategoryTitle(categoryTitle);
			diagnosticCategory.setUpdatedTs(new Date());
			adminDiagnosticService.updateCategory(diagnosticCategory);
			List<DiagnosticCategory> allCategories = adminDiagnosticService.loadAllCategories();
			
			session.setAttribute("allCategories", allCategories);
			LOGGER.debug("AdminController: renameCategory: End");
			
			return "true";
		} catch (AdminException e) {
			return "error"; 
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/updateCategoryDesc.action", method=RequestMethod.POST)
	public String updateCategoryDescAction(@RequestParam("categoryDesc") String categoryDesc, @RequestParam("categoryId") int categoryId, HttpSession session){
		
		LOGGER.debug("AdminController: updateCategoryDescAction: Start");
		DiagnosticCategory diagnosticCategory;
		try {
			diagnosticCategory = adminDiagnosticService.getDiagnosticCategoryById(categoryId);
			diagnosticCategory.setCategoryDesc(categoryDesc);
			diagnosticCategory.setUpdatedTs(new Date());
			adminDiagnosticService.updateCategory(diagnosticCategory);
			List<DiagnosticCategory> allCategories = adminDiagnosticService.loadAllCategories();
			
			session.setAttribute("allCategories", allCategories);
			LOGGER.debug("AdminController: updateCategoryDescAction: End");
			
			return "true";
		} catch (AdminException e) {
			return "error";
		}
	}
	
}
