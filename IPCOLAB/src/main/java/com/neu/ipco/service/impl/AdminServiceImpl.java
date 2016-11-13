/**
 * 
 */
package com.neu.ipco.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.neu.ipco.constants.AppConstants;
import com.neu.ipco.dao.AdminDao;
import com.neu.ipco.entity.ActivityOption;
import com.neu.ipco.entity.InstanceModule;
import com.neu.ipco.entity.InstanceTopic;
import com.neu.ipco.entity.Module;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.Quiz;
import com.neu.ipco.entity.QuizOption;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.entity.User;
import com.neu.ipco.exception.AdminException;
import com.neu.ipco.service.AdminService;

/**
 * @author Harsha
 *
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

	private Logger LOGGER = Logger.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private AdminDao adminDao;
	
	/* (non-Javadoc)
	 * @see com.neu.ipco.service.AdminService#loadAllTopics()
	 */
	public List<Topic> loadAllTopics() throws AdminException {
		LOGGER.debug("AdminService: loadAllTopics: Executing");
		try {
			List<Topic> allTopics = adminDao.loadAllTopics();
			Collections.sort(allTopics, AppConstants.TOPIC_COMPARATOR);
			return allTopics;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AdminException(e);
		}
	}

	public Topic addNewTopic(Topic newTopic) throws AdminException {
		LOGGER.debug("AdminService: addNewTopic: Executing");
		try {
			newTopic.setOrderNo(adminDao.getTopicNextOrderNo(newTopic.getTopicType().getTypeId()));
			return adminDao.addNewTopic(newTopic);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public Topic getTopicById(int topicId) throws AdminException {
		LOGGER.debug("AdminService: getTopicById: Executing");
		try {
			return adminDao.getTopicById(topicId);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public void updateTopic(Topic topic) throws AdminException {
		LOGGER.debug("AdminService: updateTopic: Executing");
		try {
			adminDao.updateTopic(topic);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public void deleteTopicById(int topicId) throws AdminException {
		LOGGER.debug("AdminService: deleteTopic: Executing");
		try {
			Topic topic = adminDao.getTopicById(topicId);
			int typeId = topic.getTopicType().getTypeId();
			deleteInstanceTopicsFromTopic(topic);
			adminDao.deleteTopic(topic);
			reorderTopics(typeId);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	private void deleteInstanceTopicsFromTopic(Topic topic) throws AdminException {
		
		Set<InstanceTopic> instanceTopics = new HashSet<InstanceTopic>(adminDao.getInstanceTopicsByTopcId(topic.getTopicId()));
		for(InstanceTopic instanceTopic : instanceTopics){
			adminDao.deleteInstanceTopic(instanceTopic);
		}
		topic.setInstanceTopics(null);
	}

	private void reorderTopics(Integer typeId) throws AdminException{
		List<Topic> topics = adminDao.getTopicsByTypeId(typeId);
		int orderNo = 1;
		for(Iterator<Topic> iterator = topics.iterator(); iterator.hasNext();){
			Topic topic = iterator.next();
			topic.setOrderNo(orderNo++);
			adminDao.updateTopic(topic);
		}
	}

	public Module addNewModule(Module module) throws AdminException {
		LOGGER.debug("AdminService: addNewModule: Executing");
		try {
			module.setOrderNo(adminDao.getModuleNextOrderNo(module.getTopic().getTopicId()));
			return adminDao.addNewModule(module);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public Module getModuleById(int moduleId) throws AdminException {
		LOGGER.debug("AdminService: getModuleById: Executing");
		try {
			return adminDao.getModuleById(moduleId);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public void updateModule(Module module) throws AdminException {
		LOGGER.debug("AdminService: updateModule: Executing");
		try {
			adminDao.updateModule(module);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public void deleteModule(Module module) throws AdminException {
		LOGGER.debug("AdminService: deleteModule: Executing");
		try {
			deleteInstanceModulesFromModule(module);
			adminDao.deleteModule(module);
			reorderModules(module.getTopic().getTopicId());
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}
	
	private void deleteInstanceModulesFromModule(Module module) throws AdminException {
		
		Set<InstanceModule> instanceModules = new HashSet<InstanceModule>(adminDao.getInstanceModulesByModuleId(module.getModuleId()));
		for(InstanceModule instanceModule : instanceModules){
			adminDao.deleteInstanceModule(instanceModule);
		}
		module.setInstanceModules(null);
	}

	private void reorderModules(Integer topicId) throws AdminException{
		List<Module> modules = adminDao.getModulesByTopicId(topicId);
		int orderNo = 1;
		for(Iterator<Module> iterator = modules.iterator(); iterator.hasNext();){
			Module module = iterator.next();
			module.setOrderNo(orderNo++);
			adminDao.updateModule(module);
		}
	}

	public String generateFilePath(MultipartFile uploadFile, String fileType) throws AdminException {
		
		LOGGER.debug("AdminService: generateFilePath: Start");

		if(null != uploadFile && !uploadFile.isEmpty()){
			StringBuilder fileName = new StringBuilder();
			fileName.append(new Date().getTime());
			fileName.append("_");
			fileName.append(uploadFile.getOriginalFilename());
			
			StringBuilder absolutePath = new StringBuilder();
			absolutePath.append(fileType.equalsIgnoreCase("image")?AppConstants.IMAGE_ABSOLUTE_PATH:AppConstants.VIDEO_ABSOLUTE_PATH);
			absolutePath.append(fileName.toString());
			
			StringBuilder relativePath = new StringBuilder();
			relativePath.append(fileType.equalsIgnoreCase("image")?AppConstants.IMAGE_RELATIVE_PATH:AppConstants.VIDEO_RELATIVE_PATH);
			relativePath.append(fileName.toString());
			
			File fileOnServer = new File(absolutePath.toString());
			try {
				FileUtils.writeByteArrayToFile(fileOnServer, uploadFile.getBytes());
			} catch (IOException e) {
				throw new AdminException(e);
			}
			
			LOGGER.debug("AdminService: generateFilePath: End");
			return relativePath.toString();
		}
		LOGGER.debug("AdminService: generateFilePath: End");
		return null;
	}

	public ActivityOption addNewActivity(ActivityOption activityOption) throws AdminException {
		LOGGER.debug("AdminService: addNewActivity: Start");
		try {
			activityOption.setOrderNo(adminDao.getActivityOptionNextOrderNo(activityOption.getModule().getModuleId()));
			activityOption =adminDao.addNewActivity(activityOption);
			activityOption.setModule(adminDao.getModuleById(activityOption.getModule().getModuleId()));
			
			LOGGER.debug("AdminService: addNewActivity: End");
			return activityOption;
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public ActivityOption getActivityOptionById(int activityOptionId) throws AdminException {
		LOGGER.debug("AdminService: getActivityOptionById: Executing");
		try {
			return adminDao.getActivityOptionById(activityOptionId);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public void deleteActivityOption(ActivityOption activityOption) throws AdminException {
		LOGGER.debug("AdminService: deleteActivityOption: Executing");
		try {
			adminDao.deleteActivityOption(activityOption);
			reorderActivities(activityOption.getModule().getModuleId());
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	private void reorderActivities(Integer moduleId) throws AdminException{
		try {
			List<ActivityOption> activityOptions = adminDao.getActivitiesByModuleId(moduleId);
			int orderNo = 1;
			for(Iterator<ActivityOption> iterator = activityOptions.iterator(); iterator.hasNext();){
				ActivityOption activityOption = iterator.next();
				activityOption.setOrderNo(orderNo++);
				adminDao.updateActivityOption(activityOption);
			}
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public ActivityOption editActivity(ActivityOption activityOption) throws AdminException {
		LOGGER.debug("AdminService: editActivity: Start");
		try {
			activityOption =adminDao.editActivity(activityOption);
			LOGGER.debug("AdminService: editActivity: End");
			return activityOption;
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public void deleteOptionsByActivityOptionId(int activityOptionId) throws AdminException {
		LOGGER.debug("AdminService: deleteOptionsByActivityOptionId: Start");
		try {
			ActivityOption activityOption = adminDao.getActivityOptionById(activityOptionId);
			adminDao.deleteOptions(activityOption.getOptions());
			LOGGER.debug("AdminService: deleteOptionsByActivityOptionId: End");
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public void saveOrUpdateTopic(Topic topic) throws AdminException {
		LOGGER.debug("AdminService: saveOrUpdateTopic: Start");
		try {
			LOGGER.debug("AdminService: saveOrUpdateTopic: Executing");
			adminDao.saveOrUpdateTopic(topic);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public void saveQuiz(Quiz quiz) throws AdminException {
		LOGGER.debug("AdminService: saveQuiz: Start");
		try {
			LOGGER.debug("AdminService: saveQuiz: Executing");
			adminDao.saveQuiz(quiz);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public Quiz getQuizById(int quizId) throws AdminException {
		LOGGER.debug("AdminService: getQuizById: Start");
		try {
			LOGGER.debug("AdminService: getQuizById: Executing");
			return adminDao.getQuizById(quizId);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public void saveOrUpdateQuiz(Quiz quiz) throws AdminException {
		LOGGER.debug("AdminService: saveOrUpdateQuiz: Start");
		try {
			LOGGER.debug("AdminService: saveOrUpdateQuiz: Executing");
			adminDao.saveOrUpdateQuiz(quiz);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public int getNextQuizOpOrderNo(List<QuizOption> quizOptions) throws AdminException {
		return quizOptions.size()+1;
	}

	@Override
	public List<User> loadAllUsers() throws AdminException {
		LOGGER.debug("AdminService: loadAllUsers: Executing");
		try {
			List<User> allUsers = adminDao.loadAllUsers();
			Collections.sort(allUsers, AppConstants.REGISTERED_USER_COMPARATOR);
			return allUsers;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AdminException(e);
		}
	}

	@Override
	public User getUserById(int userId) throws AdminException {
		LOGGER.debug("AdminService: getUserById: Executing");
		try {
			return adminDao.getUserById(userId);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	@Override
	public void deleteOptions(Set<Option> options) throws AdminException {
		LOGGER.debug("AdminService: deleteOptions: Start");
		try {
			adminDao.deleteOptions(options);
			LOGGER.debug("AdminService: deleteOptions: End");
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}
	

}