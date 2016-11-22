/**
 * 
 */
package com.neu.ipco.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.neu.ipco.entity.ActivityOption;
import com.neu.ipco.entity.BasicInstanceUser;
import com.neu.ipco.entity.CustomizeInstanceUser;
import com.neu.ipco.entity.Module;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.Quiz;
import com.neu.ipco.entity.QuizOption;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.entity.User;
import com.neu.ipco.entity.UserRole;
import com.neu.ipco.exception.AdminException;

/**
 * @author Harsha
 *
 */
public interface AdminService {

	public List<Topic> loadAllTopics() throws AdminException;

	public Topic addNewTopic(Topic newTopic) throws AdminException;

	public Topic getTopicById(int topicId) throws AdminException;

	public void updateTopic(Topic topic) throws AdminException;

	public void deleteTopicById(int topicId) throws AdminException;

	public Module addNewModule(Module module) throws AdminException;

	public Module getModuleById(int moduleId) throws AdminException;

	public void updateModule(Module module) throws AdminException;

	public void deleteModule(Module module) throws AdminException;

	public String generateFilePath(MultipartFile uploadFile, String fileType) throws AdminException;

	public ActivityOption addNewActivity(ActivityOption activityOption) throws AdminException;

	public ActivityOption getActivityOptionById(int activityOptionId) throws AdminException;

	public void deleteActivityOption(ActivityOption activityOption) throws AdminException;

	public ActivityOption editActivity(ActivityOption currActivityOption) throws AdminException;

	public void deleteOptionsByActivityOptionId(int activityOptionId) throws AdminException;

	public void saveOrUpdateTopic(Topic topic) throws AdminException;

	public void saveQuiz(Quiz quiz) throws AdminException;

	public Quiz getQuizById(int quizId) throws AdminException;

	public void saveOrUpdateQuiz(Quiz quiz) throws AdminException;

	public int getNextQuizOpOrderNo(List<QuizOption> quizOptions) throws AdminException;

	public List<User> loadAllUsers() throws AdminException;

	public User getUserById(int userId) throws AdminException;

	public void deleteOptions(Set<Option> options) throws AdminException;

	public void deleteQuizById(int deletableId) throws AdminException;

	public QuizOption getQuizOptionById(int quizOptionId) throws AdminException;

	public void saveOrUpdateQuizOption(QuizOption oldQuizOption) throws AdminException;

	public void deleteQuizOptionById(int deletableId) throws AdminException;

	public void deleteBasicInstanceUser(BasicInstanceUser basicInstanceUser) throws AdminException;

	public void deleteCustomizeInstanceUser(CustomizeInstanceUser customizeInstanceUser) throws AdminException;

	public void deleteUser(User deletableUser) throws AdminException;

	public List<UserRole> loadAllUserRoles() throws AdminException;

	public void saveOrUpdateUserRole(UserRole userRole) throws AdminException;

	public UserRole geUserRoleById(int userRoleId) throws AdminException;
	
}
