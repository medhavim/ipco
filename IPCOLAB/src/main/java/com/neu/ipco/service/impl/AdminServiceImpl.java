/**
 * 
 */
package com.neu.ipco.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.ipco.dao.AdminDao;
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

}
