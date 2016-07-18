/**
 * 
 */
package com.neu.ipco.service;

import java.util.List;

import com.neu.ipco.entity.Topic;
import com.neu.ipco.exception.AdminException;

/**
 * @author Harsha
 *
 */
public interface AdminService {

	public List<Topic> loadAllTopics() throws AdminException;
}
