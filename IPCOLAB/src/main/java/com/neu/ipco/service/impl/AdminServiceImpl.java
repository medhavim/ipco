/**
 * 
 */
package com.neu.ipco.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.neu.ipco.dao.AdminDao;
import com.neu.ipco.dao.UserDao;
import com.neu.ipco.entity.ActivityAnswer;
import com.neu.ipco.entity.ActivityOption;
import com.neu.ipco.entity.BasicInstanceUser;
import com.neu.ipco.entity.CustomizeInstanceUser;
import com.neu.ipco.entity.InstanceModule;
import com.neu.ipco.entity.InstanceQuiz;
import com.neu.ipco.entity.InstanceTopic;
import com.neu.ipco.entity.Module;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.Quiz;
import com.neu.ipco.entity.QuizAnswer;
import com.neu.ipco.entity.QuizOption;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.entity.User;
import com.neu.ipco.entity.UserRole;
import com.neu.ipco.exception.AdminException;
import com.neu.ipco.service.AdminService;
import com.neu.ipco.utility.AppConstants;

/**
 * @author Harsha
 *
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

	private Logger LOGGER = Logger.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private UserDao userDao;
	
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
			deleteActivityAnswersForActivityOption(activityOption.getActivityOptionId());
			adminDao.deleteActivityOption(activityOption);
			reorderActivities(activityOption.getModule().getModuleId());
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	private void deleteActivityAnswersForActivityOption(int activityOptionId) throws AdminException {
		List<ActivityAnswer> activityAnswers = adminDao.getActivityAnswersByActivityOptionId(activityOptionId);
		for(ActivityAnswer activityAnswer : activityAnswers){
			adminDao.deleteActivityAnswer(activityAnswer);
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
			Collections.sort(allUsers, AppConstants.SORT_LAST_LOGIN_COMPARATOR);
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

	@Override
	public void deleteQuizById(int deletableId) throws AdminException {
		LOGGER.debug("AdminService: deleteQuizById: Start");
		try {
			Topic topic = adminDao.geTopicByQuizId(deletableId);
			if(null != topic){
				List<InstanceQuiz> instanceQuizes = new ArrayList<InstanceQuiz>(adminDao.geInstanceQuizByQuizId(deletableId));
				for(InstanceQuiz instanceQuiz : instanceQuizes){
					InstanceTopic instanceTopic = adminDao.getInstanceTopicByInstanceQuizId(instanceQuiz.getInstanceQuizId());
					instanceTopic.setQuiz(null);
					userDao.saveOrUpdateInstanceTopic(instanceTopic);
				}
				topic.setQuiz(null);
				adminDao.saveOrUpdateTopic(topic);
				adminDao.deleteInstanceQuizes(instanceQuizes);
				adminDao.deleteQuizById(deletableId);
			}
			LOGGER.debug("AdminService: deleteQuizById: End");
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	@Override
	public QuizOption getQuizOptionById(int quizOptionId) throws AdminException {
		LOGGER.debug("AdminService: deleteOptions: Start");
		try {
			LOGGER.debug("AdminService: getQuizOptionById: Executing");
			return adminDao.getQuizOptionById(quizOptionId);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	@Override
	public void saveOrUpdateQuizOption(QuizOption quizOption) throws AdminException {
		LOGGER.debug("AdminService: saveOrUpdateQuizOption: Start");
		try {
			LOGGER.debug("AdminService: saveOrUpdateQuizOption: Executing");
			adminDao.saveOrUpdateQuizOption(quizOption);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	@Override
	public void deleteQuizOptionById(int deletableId) throws AdminException {
		LOGGER.debug("AdminService: deleteQuizOptionById: Start");
		try {
			QuizOption quizOption = adminDao.getQuizOptionById(deletableId);
			deleteQuizAnswersForQuizOption(deletableId);
//			adminDao.deleteQuizOption(quizOption);
			deleteAndReorderQuiz(quizOption.getQuiz().getQuizId(), deletableId);
			LOGGER.debug("AdminService: deleteQuizOptionById: End");
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	private void deleteQuizAnswersForQuizOption(int quizOptoinsId) throws AdminException {
		List<QuizAnswer> quizAnswers = adminDao.getQuizAnswersByQuizOptionId(quizOptoinsId);
		for(QuizAnswer quizAnswer : quizAnswers){
			adminDao.deleteQuizAnswer(quizAnswer);
		}
	}

	private void deleteAndReorderQuiz(int quizId, int quizOptionId) throws AdminException {
		Quiz quiz =  adminDao.getQuizById(quizId);
		List<QuizOption> quizOptions = quiz.getQuizOptions();
		int orderNo = 1;
		for(Iterator<QuizOption> quizOptionIterator = quizOptions.iterator(); quizOptionIterator.hasNext();){
			QuizOption quizOption = quizOptionIterator.next();
			if(null == quizOption){
				quizOptionIterator.remove();
				continue;
			}else if(quizOption.getQuizOptionId() == quizOptionId){
				adminDao.deleteQuizOption(quizOption);
				quizOptionIterator.remove();
				continue;
			}
			quizOption.setOrderNo(orderNo++);
		}
		adminDao.saveOrUpdateQuiz(quiz);
	}

	@Override
	public void deleteBasicInstanceUser(BasicInstanceUser basicInstanceUser) throws AdminException {
		LOGGER.debug("AdminService: deleteBasicInstanceUser: Start");
		try {
			LOGGER.debug("AdminService: deleteBasicInstanceUser: Executing");
			adminDao.deleteBasicInstanceUser(basicInstanceUser);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	@Override
	public void deleteCustomizeInstanceUser(CustomizeInstanceUser customizeInstanceUser) throws AdminException {
		LOGGER.debug("AdminService: deleteCustomizeInstanceUser: Start");
		try {
			LOGGER.debug("AdminService: deleteCustomizeInstanceUser: Executing");
			adminDao.deleteCustomizeInstanceUser(customizeInstanceUser);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	@Override
	public void deleteUser(User deletableUser) throws AdminException {
		LOGGER.debug("AdminService: deleteUser: Start");
		try {
			LOGGER.debug("AdminService: deleteUser: Executing");
			adminDao.deleteUser(deletableUser);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	@Override
	public List<UserRole> loadAllUserRoles() throws AdminException {
		LOGGER.debug("AdminService: loadAllUserRoles: Start");
		try {
			LOGGER.debug("AdminService: loadAllUserRoles: Executing");
			return adminDao.loadAllUserRoles();
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	@Override
	public void saveOrUpdateUserRole(UserRole userRole) throws AdminException {
		LOGGER.debug("AdminService: saveOrUpdateUserRole: Start");
		try {
			LOGGER.debug("AdminService: saveOrUpdateUserRole: Executing");
			adminDao.saveOrUpdateUserRole(userRole);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	@Override
	public UserRole geUserRoleById(int userRoleId) throws AdminException {
		LOGGER.debug("AdminService: geUserRoleById: Start");
		try {
			LOGGER.debug("AdminService: geUserRoleById: Executing");
			return adminDao.geUserRoleById(userRoleId);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}
	

}