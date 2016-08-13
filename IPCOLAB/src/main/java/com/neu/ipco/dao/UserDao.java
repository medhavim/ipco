/**
 * 
 */
package com.neu.ipco.dao;

import java.util.List;

import com.neu.ipco.entity.ActivityAnswer;
import com.neu.ipco.entity.BasicInstanceUser;
import com.neu.ipco.entity.Instance;
import com.neu.ipco.entity.InstanceModule;
import com.neu.ipco.entity.InstanceTopic;
import com.neu.ipco.entity.Status;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.exception.UserException;

/**
 * @author harsh
 *
 */
public interface UserDao {

	BasicInstanceUser getInstanceByUserId(Integer userId, int instanceTypeId) throws UserException;

	List<Topic> getTopicsByTypeId(int instanceTypeId) throws UserException;

	Status getStatusByDesc(String statusDesc) throws UserException;

	Instance saveInstance(Instance instance) throws UserException;

	ActivityAnswer saveActivityAnswer(ActivityAnswer activityAnswer) throws UserException;

	BasicInstanceUser saveBasicInstance(BasicInstanceUser basicInstanceUser) throws UserException;

	InstanceModule geInstanceModuleById(int instanceModuleId) throws UserException;

	InstanceTopic getInstanceTopicById(Integer instanceTopicId) throws UserException;

	Instance getInstanceById(Integer instanceId) throws UserException;

	BasicInstanceUser saveOrUpdateBasicInstance(BasicInstanceUser basicInstanceUser) throws UserException;

	Instance getInstanceIdByUser(Integer userId) throws UserException;

	void saveOrUpdateInstance(Instance instance) throws UserException;

	void saveOrUpdateInstanceTopic(InstanceTopic instanceTopic) throws UserException;

	void saveOrUpdateInstanceModule(InstanceModule instanceModule) throws UserException;

}