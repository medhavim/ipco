/**
 * 
 */
package com.neu.ipco.dao;

import java.util.List;

import com.neu.ipco.entity.Topic;
import com.neu.ipco.exception.AdminException;

/**
 * @author Harsha
 *
 */
public interface AdminDao {
	
	public List<Topic> loadAllTopics() throws AdminException;

	public Topic addNewTopic(Topic newTopic) throws AdminException;

}
