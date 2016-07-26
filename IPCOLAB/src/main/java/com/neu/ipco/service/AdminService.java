/**
 * 
 */
package com.neu.ipco.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.neu.ipco.entity.ActivityOption;
import com.neu.ipco.entity.Module;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.Topic;
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

	public void deleteTopic(Topic topic) throws AdminException;

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

}
