/**
 * 
 */
package com.neu.ipco.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.neu.ipco.constants.AppConstants;
import com.neu.ipco.dao.AdminDao;
import com.neu.ipco.entity.Activity;
import com.neu.ipco.entity.ActivityOption;
import com.neu.ipco.entity.ActivityTemplate;
import com.neu.ipco.entity.Diagnostic;
import com.neu.ipco.entity.DiagnosticCategory;
import com.neu.ipco.entity.Module;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.entity.TopicType;
import com.neu.ipco.exception.AdminException;

/**
 * @author Harsha
 *
 */
@Repository("adminDao")
@Transactional
public class AdminDaoImpl implements AdminDao {
	
	private Logger LOGGER = Logger.getLogger(AdminDaoImpl.class);
	
	@Autowired
	private HibernateTemplate template;

	/* (non-Javadoc)
	 * @see com.neu.ipco.dao.AdminDao#loadAllTopics()
	 */
	public List<Topic> loadAllTopics() throws AdminException {
		
		LOGGER.debug("AdminDaoImpl: loadAllTopics: Executing");
		return template.loadAll(Topic.class);
	}

	public Topic addNewTopic(Topic newTopic) throws AdminException {
		LOGGER.debug("AdminDaoImpl: addNewTopic: Start");
		int topicId = (Integer) template.save(newTopic);
		LOGGER.debug("AdminDaoImpl: addNewTopic: Executing");
		return template.get(Topic.class, topicId);
	}

	public Topic getTopicById(int topicId) throws AdminException {
		LOGGER.debug("AdminDaoImpl: getTopicById: Executing");
		return template.get(Topic.class, topicId);
	}

	public void updateTopic(Topic topic) throws AdminException {
		LOGGER.debug("AdminDaoImpl: updateTopic: Executing");
		template.update(topic);
	}

	public void deleteTopic(Topic topic) throws AdminException {
		LOGGER.debug("AdminDaoImpl: deleteTopic: Executing");
		template.delete(topic);
	}

	public Module addNewModule(Module module) throws AdminException {
		LOGGER.debug("AdminDaoImpl: addNewModule: Start");
		int moduleId = (Integer) template.save(module);
		LOGGER.debug("AdminDaoImpl: addNewModule: Executing");
		return template.get(Module.class, moduleId);
	}

	public Module getModuleById(int moduleId) throws AdminException {
		LOGGER.debug("AdminDaoImpl: getModuleById: Executing");
		return template.get(Module.class, moduleId);
	}

	public void updateModule(Module module) throws AdminException {
		LOGGER.debug("AdminDaoImpl: getModuleById: Executing");
		template.update(module);
	}

	public void deleteModule(Module module) throws AdminException {
		LOGGER.debug("AdminDaoImpl: deleteModule: Executing");
		template.delete(module);
	}

	public ActivityOption addNewActivity(ActivityOption activityOption) throws AdminException {
		LOGGER.debug("AdminDaoImpl: addNewActivity: Start");
		int activityOptionId = (Integer) template.save(activityOption);
		LOGGER.debug("AdminDaoImpl: addNewActivity: Executing");
		return template.get(ActivityOption.class, activityOptionId);
	}

	public int getActivityOptionNextOrderNo(Integer moduleId) throws AdminException {
		LOGGER.debug("AdminDaoImpl: getActivityOptionNextOrderNo: Executing");
		List<ActivityOption> activityOptions = (List<ActivityOption>) template.findByNamedParam("from ActivityOption ao where ao.module.moduleId = :moduleId", "moduleId", moduleId);
		if(activityOptions.isEmpty()){
			return 1;
		}else{
			Collections.sort(activityOptions);
			return activityOptions.get(activityOptions.size()-1).getOrderNo()+1;
		}
	}

	public int getModuleNextOrderNo(Integer topicId) throws AdminException {
		LOGGER.debug("AdminDaoImpl: getModuleNextOrderNo: Executing");
		List<Module> modules = (List<Module>) template.findByNamedParam("from Module m where m.topic.topicId = :topicId", "topicId", topicId);
		if(modules.isEmpty()){
			return 1;
		}else{
			Collections.sort(modules);
			return modules.get(modules.size()-1).getOrderNo()+1;
		}
	}

	public int getTopicNextOrderNo(int topicTypeId) throws AdminException {
		LOGGER.debug("AdminDaoImpl: getTopicNextOrderNo: Executing");
		List<Topic> topics = (List<Topic>) template.findByNamedParam("from Topic t where t.topicType.typeId = :topicTypeId", "topicTypeId", topicTypeId);
		if(topics.isEmpty()){
			return 1;
		}else{
			Collections.sort(topics);
			return topics.get(topics.size()-1).getOrderNo()+1;
		}
	}

	public ActivityOption getActivityOptionById(int activityOptionId) throws AdminException {
		LOGGER.debug("AdminDaoImpl: getActivityOptionById: Executing");
		return template.get(ActivityOption.class, activityOptionId);
	}

	public void deleteActivityOption(ActivityOption activityOption) throws AdminException {
		LOGGER.debug("AdminDaoImpl: deleteActivityOption: Executing");
		template.delete(activityOption);
	}

	public List<ActivityOption> getActivitiesByModuleId(Integer moduleId) throws AdminException {
		LOGGER.debug("AdminDaoImpl: getActivitiesByModuleId: Executing");
		List<ActivityOption> activityOptions = (List<ActivityOption>) template.findByNamedParam("from ActivityOption ao where ao.module.moduleId = :moduleId", "moduleId", moduleId);
		Collections.sort(activityOptions);
		return activityOptions;
	}

	public void updateActivityOption(ActivityOption activityOption) throws AdminException {
		LOGGER.debug("AdminDaoImpl: updateActivityOption: Executing");
		template.update(activityOption);
	}

	public List<Module> getModulesByTopicId(Integer topicId) throws AdminException {
		LOGGER.debug("AdminDaoImpl: getModulesByTopicId: Executing");
		List<Module> modules = (List<Module>) template.findByNamedParam("from Module m where m.topic.topicId = :topicId", "topicId", topicId);
		Collections.sort(modules);
		return modules;
	}

	public List<Topic> getTopicsByTypeId(Integer typeId) throws AdminException {
		LOGGER.debug("AdminDaoImpl: getTopicsByTypeId: Executing");
		List<Topic> topics = (List<Topic>) template.findByNamedParam("from Topic t where t.topicType.typeId = :typeId", "typeId", typeId);
		Collections.sort(topics);
		return topics;
	}

	public ActivityOption editActivity(ActivityOption activityOption) throws AdminException {
		LOGGER.debug("AdminDaoImpl: editActivity: Start");
		template.update(activityOption);
		LOGGER.debug("AdminDaoImpl: editActivity: Executing");
		return template.get(ActivityOption.class, activityOption.getActivityOptionId());
	}

	public void deleteOptions(Set<Option> options) throws AdminException {
		LOGGER.debug("AdminDaoImpl: deleteOptions: Executing");
		template.deleteAll(options);
	}

	public List<DiagnosticCategory> loadAllCategories() throws AdminException {
		
		LOGGER.debug("AdminDaoImpl: loadAllCategories: Executing");
		return template.loadAll(DiagnosticCategory.class);
	}

	public int getCategoryNextOrderNo() throws AdminException {
		LOGGER.debug("AdminDaoImpl: getCategoryNextOrderNo: Executing");
		List<DiagnosticCategory> categories = loadAllCategories();
		if(categories.isEmpty()){
			return 1;
		}else{
			Collections.sort(categories);
			return categories.get(categories.size()-1).getOrderNo()+1;
		}
	}

	public DiagnosticCategory addNewCategory(DiagnosticCategory newDiagnosticCategory) throws AdminException {
		LOGGER.debug("AdminDaoImpl: addNewCategory: Start");
		int categoryId = (Integer) template.save(newDiagnosticCategory);
		LOGGER.debug("AdminDaoImpl: addNewCategory: Executing");
		return getDiagnosticCategoryById(categoryId);
	}

	public DiagnosticCategory getDiagnosticCategoryById(int categoryId) throws AdminException {
		LOGGER.debug("AdminDaoImpl: getDiagnosticCategoryById: Executing");
		return template.get(DiagnosticCategory.class, categoryId);
	}

	public void deleteCategory(DiagnosticCategory diagnosticCategory) throws AdminException {
		LOGGER.debug("AdminDaoImpl: deleteCategory: Executing");
		template.delete(diagnosticCategory);
	}

	public void updateCategory(DiagnosticCategory diagnosticCategory) throws AdminException {
		LOGGER.debug("AdminDaoImpl: updateCategory: Executing");
		template.update(diagnosticCategory);
	}

	public List<Topic> loadCustomTopics() throws AdminException {
		LOGGER.debug("AdminDaoImpl: loadCustomTopics: Executing");
		return (List<Topic>) template.findByNamedParam("from Topic t where t.topicType.typeId = :topicTypeId", "topicTypeId", AppConstants.TOPIC_TYPE_ID_CUSTOM);
	}

	public Diagnostic addNewDiagnostic(Diagnostic diagnostic) throws AdminException {
		LOGGER.debug("AdminDaoImpl: addNewDiagnostic: Start");
		int diagnosticId = (Integer) template.save(diagnostic);
		LOGGER.debug("AdminDaoImpl: addNewDiagnostic: Executing");
		return template.get(Diagnostic.class, diagnosticId);
	}

	public Diagnostic getDiagnosticById(int diagnosticId) throws AdminException {
		LOGGER.debug("AdminDaoImpl: getDiagnosticById: Executing");
		return template.get(Diagnostic.class, diagnosticId);
	}

}
