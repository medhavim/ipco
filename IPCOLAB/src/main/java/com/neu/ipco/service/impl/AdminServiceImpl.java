/**
 * 
 */
package com.neu.ipco.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.ipco.dao.AdminDao;
import com.neu.ipco.entity.Module;
import com.neu.ipco.entity.Topic;
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
		return adminDao.loadAllTopics();
	}

	public Topic addNewTopic(Topic newTopic) throws AdminException {
		LOGGER.debug("AdminService: addNewTopic: Executing");
		return adminDao.addNewTopic(newTopic);
	}

	public Topic getTopicById(int topicId) throws AdminException {
		LOGGER.debug("AdminService: getTopicById: Executing");
		return adminDao.getTopicById(topicId);
	}

	public void updateTopic(Topic topic) throws AdminException {
		LOGGER.debug("AdminService: updateTopic: Executing");
		adminDao.updateTopic(topic);
	}

	public void deleteTopic(Topic topic) throws AdminException {
		LOGGER.debug("AdminService: deleteTopic: Executing");
		adminDao.deleteTopic(topic);
	}

	public Module addNewModule(Module module) throws AdminException {
		LOGGER.debug("AdminService: addNewModule: Executing");
		return adminDao.addNewModule(module);
	}

	public Module getModuleById(int moduleId) throws AdminException {
		LOGGER.debug("AdminService: getModuleById: Executing");
		return adminDao.getModuleById(moduleId);
	}

	public void updateModule(Module module) throws AdminException {
		LOGGER.debug("AdminService: updateModule: Executing");
		adminDao.updateModule(module);
	}

	public void deleteModule(Module module) throws AdminException {
		LOGGER.debug("AdminService: deleteModule: Executing");
		adminDao.deleteModule(module);
	}

}
