/**
 * 
 */
package com.neu.ipco.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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

import com.neu.ipco.entity.Diagnostic;
import com.neu.ipco.entity.DiagnosticCategory;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.RelatedDiagnostic;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.entity.User;
import com.neu.ipco.exception.AdminException;
import com.neu.ipco.service.AdminDiagnosticService;
import com.neu.ipco.service.AdminService;
import com.neu.ipco.utility.AppConstants;
import com.neu.ipco.utility.ApplicationUtil;

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
			return "redirect: adminHome.action";
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
//			DiagnosticCategory diagnosticCategory = adminDiagnosticService.getDiagnosticCategoryById(deletableId);
			adminDiagnosticService.deleteCategory(deletableId);
			
			List<DiagnosticCategory> allCategories = adminDiagnosticService.loadAllCategories();
			session.setAttribute("allCategories", allCategories);
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: deleteCategoryAction: End");
		return AppConstants.MANAGE_DIAGNOSTIC;
	}
	
	@RequestMapping(value="/deleteDiagnostic.action", method=RequestMethod.POST)
	public String deleteDiagnosticAction(@RequestParam("deletableId") int deletableId, HttpSession session, Model model){
		
		LOGGER.debug("AdminController: deleteDiagnosticAction: Start");
		
		try {
//			DiagnosticCategory diagnosticCategory = adminDiagnosticService.getDiagnosticCategoryById(deletableId);
			adminDiagnosticService.deleteDiagnosticById(deletableId);
			
			List<DiagnosticCategory> allCategories = adminDiagnosticService.loadAllCategories();
			session.setAttribute("allCategories", allCategories);
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: deleteDiagnosticAction: End");
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
			options = ApplicationUtil.populateYESNOOptions(options, request, orderNo);
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

	@RequestMapping(value="/gotoEditDiagnostic.action")
	public String gotoEditDiagnosticAction(@RequestParam("id") int diagnosticId, HttpSession session, Model model){
		
		LOGGER.debug("AdminDiagnosticController: gotoEditDiagnosticAction: Start");
		
		try {
			Diagnostic diagnostic = adminDiagnosticService.getDiagnosticById(diagnosticId);
			List<Topic> allTopics = adminDiagnosticService.loadCustomTopics();
			
			allTopics.removeAll(diagnostic.getTopics());
			
			model.addAttribute("allTopics", allTopics);
			model.addAttribute("diagnostic", diagnostic);
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		} 
		
		LOGGER.debug("AdminDiagnosticController: gotoEditDiagnosticAction: End");
		return AppConstants.EDIT_DIAGNOSTIC;
	}
	
	@RequestMapping(value="/editDiagnostic.action", method=RequestMethod.POST)
	public String editDiagnosticAction(@ModelAttribute("diagnostic") Diagnostic diagnostic, 
			@RequestParam("activityTemplate") int activityTemplateId,
			HttpServletRequest request, HttpSession session, Model model){
		
		LOGGER.debug("AdminDiagnosticController: editDiagnosticAction: Start");
		
		try {
			Diagnostic oldDiagnostic = adminDiagnosticService.getDiagnosticById(diagnostic.getDiagnosticId());
			oldDiagnostic.getActivity().setActivityText(diagnostic.getActivity().getActivityText());
			oldDiagnostic.getActivity().setActivityTitle(diagnostic.getActivity().getActivityTitle());
			oldDiagnostic.getActivity().setUpdatedTs(new Date());
			oldDiagnostic.setUpdatedTs(new Date());
			
			Set<Option> options = new TreeSet<Option>();
			int orderNo = 0;
			options = ApplicationUtil.populateYESNOOptions(options, request, orderNo);
			options = new TreeSet<Option>(ApplicationUtil.updateCorrectOptions(options, oldDiagnostic.getOptions()));
			oldDiagnostic.setOptions(options);
			
			Set<Topic> topics = populateTopics(request);
			oldDiagnostic.setTopics(topics);
			
			adminDiagnosticService.saveOrUpdateDiagnostic(oldDiagnostic);
			
			List<DiagnosticCategory> allCategories = adminDiagnosticService.loadAllCategories();
			session.setAttribute("allCategories", allCategories);
			model.addAttribute("diagnosticCategoryId", diagnostic.getCategory().getCategoryId());
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		} 
		
		LOGGER.debug("AdminDiagnosticController: editDiagnosticAction: End");
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
	
	@RequestMapping(value="/gotoAddRelatedDiagnostic.action")
	public String gotoAddRelatedDiagnosticAction(@RequestParam("id") int diagnosticId, Model model, HttpSession session){
		
		LOGGER.debug("AdminController: gotoAddRelatedDiagnosticAction: Start");
		
		try {
			
			List<Diagnostic> allDiagnostics = adminDiagnosticService.loadAllDiagnostics();
			List<Topic> allTopics = adminDiagnosticService.loadCustomTopics();

			Diagnostic currentDiagnostic = adminDiagnosticService.getDiagnosticById(diagnosticId);
			
			model.addAttribute("allDiagnostics", allDiagnostics);
			model.addAttribute("allTopics", allTopics);
			model.addAttribute("currentDiagnostic", currentDiagnostic);
			
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
		
		LOGGER.debug("AdminController: gotoAddRelatedDiagnosticAction: End");
		return AppConstants.ADMIN_RELATED_DIAGNOSTIC;
	}
	
	@RequestMapping(value="/addRelatedDiagnostic.action", method=RequestMethod.POST)
	public String addRelatedDiagnosticAction(HttpServletRequest request, HttpSession session, Model model){
		
		LOGGER.debug("AdminDiagnosticController: addRelatedDiagnosticAction: Start");
		
		try {
			String relatedDiagnosticTitle = request.getParameter("relatedDiagnosticTitle");
		
			RelatedDiagnostic relatedDiagnostic = new RelatedDiagnostic();
			relatedDiagnostic.setRelatedDiagnosticTitle(relatedDiagnosticTitle);
			relatedDiagnostic.setCreatedTs(new Date());
			Set<Topic> topics = populateTopics(request);
			relatedDiagnostic.setTopics(topics);
			Set<Diagnostic> diagnostics = populateDiagnostics(request);
			relatedDiagnostic.setDiagnostics(diagnostics);
			
			relatedDiagnostic = adminDiagnosticService.addRelatedDiagnostic(relatedDiagnostic);
			
			List<RelatedDiagnostic> relatedDiagnostics = adminDiagnosticService.loadAllRelatedDiagnostics();
			session.setAttribute("relatedDiagnostics", relatedDiagnostics);
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		} 
		
		LOGGER.debug("AdminDiagnosticController: addRelatedDiagnosticAction: End");
		return AppConstants.MANAGE_RELATED_DIAGNOSTIC;
	}
	
	private Set<Diagnostic> populateDiagnostics(HttpServletRequest request) throws AdminException {
		
		Set<Diagnostic> diagnostics = new HashSet<Diagnostic>();
		Enumeration<String> parameters = request.getParameterNames();
		while(parameters.hasMoreElements()){
			String param = (String) parameters.nextElement();
			if(param.contains("diagnostic_")){
				int diagnosticId = Integer.valueOf(param.split("_")[1]);
				try {
					Diagnostic diagnostic = adminDiagnosticService.getDiagnosticById(diagnosticId);
					diagnostics.add(diagnostic);
				} catch (Exception e) {
					e.printStackTrace();
					throw new AdminException(e);
				}
			}
		}
		return diagnostics;
	}
	
	@RequestMapping(value="/manageRelatedDiagnostic.action")
	public String manageRelatedDiagnosticAction(HttpSession session, Model model){
		
		LOGGER.debug("AdminDiagnosticController: manageRelatedDiagnosticAction: Start");
		
		try {
			User user = (User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_ADMIN);
			
			if(null == user){
				LOGGER.debug("AdminDiagnosticController: manageRelatedDiagnosticAction: Exception: Admin object not found in the session, hence exiting.");
				return AppConstants.ERROR_PAGE;
			}
			
			List<RelatedDiagnostic> relatedDiagnostics = adminDiagnosticService.loadAllRelatedDiagnostics();
			session.setAttribute("relatedDiagnostics", relatedDiagnostics);
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		} 
		
		LOGGER.debug("AdminDiagnosticController: manageRelatedDiagnosticAction: End");
		return AppConstants.MANAGE_RELATED_DIAGNOSTIC;
	}

	@RequestMapping(value="/editRelatedDiagnostic.action")
	public String editRelatedDiagnosticAction(@RequestParam("id") int relatedDiagnosticId, HttpSession session, Model model){
		
		LOGGER.debug("AdminDiagnosticController: editRelatedDiagnosticAction: Start");
		
		try {
			
			RelatedDiagnostic relatedDiagnostic = adminDiagnosticService.getRelatedDiagnosticById(relatedDiagnosticId);
			List<Diagnostic> allDiagnostics = adminDiagnosticService.loadAllDiagnostics();
			List<Topic> allTopics = adminDiagnosticService.loadCustomTopics();
			
			Map<Integer, Diagnostic> allDiagnosticMap = new TreeMap<Integer, Diagnostic>();
			for(Diagnostic d : allDiagnostics){
				allDiagnosticMap.put(d.getDiagnosticId(), d);
			}
			
			for(Diagnostic d : relatedDiagnostic.getDiagnostics()){
				Diagnostic currDiagnostic = allDiagnosticMap.get(d.getDiagnosticId());
				if(null != currDiagnostic){
					allDiagnosticMap.remove(d.getDiagnosticId());
				}
			}
			allDiagnostics = new ArrayList<Diagnostic>(allDiagnosticMap.values());
			
			allTopics.removeAll(relatedDiagnostic.getTopics());
			
			model.addAttribute("allDiagnostics", allDiagnostics);
			model.addAttribute("allTopics", allTopics);
			model.addAttribute("relatedDiagnostic", relatedDiagnostic);
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		} 
		
		LOGGER.debug("AdminDiagnosticController: editRelatedDiagnosticAction: End");
		return AppConstants.EDIT_RELATED_DIAGNOSTIC;
	}
	
	@RequestMapping(value="/updatedRelatedDiagnostic.action", method=RequestMethod.POST)
	public String updateRelatedDiagnosticAction(HttpServletRequest request, HttpSession session, Model model){
		
		LOGGER.debug("AdminDiagnosticController: updateRelatedDiagnosticAction: Start");
		
		try {
			int relatedDiagnosticId = Integer.valueOf(request.getParameter("relatedDiagnosticId"));
			RelatedDiagnostic relatedDiagnostic = adminDiagnosticService.getRelatedDiagnosticById(relatedDiagnosticId);
			String relatedDiagnosticTitle = request.getParameter("relatedDiagnosticTitle");
			relatedDiagnostic.setRelatedDiagnosticTitle(relatedDiagnosticTitle);
			relatedDiagnostic.setUpdatedTs(new Date());
			Set<Topic> topics = populateTopics(request);
			relatedDiagnostic.getTopics().clear();
			relatedDiagnostic.getTopics().addAll(topics);
			Set<Diagnostic> diagnostics = populateDiagnostics(request);
			relatedDiagnostic.getDiagnostics().clear();
			relatedDiagnostic.getDiagnostics().addAll(diagnostics);
			
			relatedDiagnostic = adminDiagnosticService.saveOrUpdateRelatedDiagnostic(relatedDiagnostic);
			
			List<RelatedDiagnostic> relatedDiagnostics = adminDiagnosticService.loadAllRelatedDiagnostics();
			session.setAttribute("relatedDiagnostics", relatedDiagnostics);
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		} 
		
		LOGGER.debug("AdminDiagnosticController: updateRelatedDiagnosticAction: End");
		return AppConstants.MANAGE_RELATED_DIAGNOSTIC;
	}

	@RequestMapping(value="/deleteRelatedDiagnostic.action", method=RequestMethod.POST)
	public String deleteRelatedDiagnosticAction(@RequestParam("deletableId") int deletableId, HttpSession session, Model model){
		
		LOGGER.debug("AdminController: deleteRelatedDiagnostic: Start");
		
		try {
			adminDiagnosticService.deleteRelatedDiagnosticById(deletableId);
			LOGGER.debug("AdminController: deleteRelatedDiagnostic: End");
			
			return manageRelatedDiagnosticAction(session, model);
		} catch (AdminException e) {
			return AppConstants.ERROR_PAGE;
		}
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
