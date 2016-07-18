/**
 * 
 */
package com.neu.ipco.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.neu.ipco.dao.AdminDao;
import com.neu.ipco.entity.Topic;
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

}
