/**
 * 
 */
package com.neu.ipco.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.neu.ipco.dao.UserDao;
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
@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {
	
	private Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
	
	@Autowired
	private HibernateTemplate template;

	public BasicInstanceUser getInstanceByUserId(Integer userId, int instanceTypeId) throws UserException {
		LOGGER.debug("UserDaoImpl: getInstanceByUserId: Start");
//		template.delete(template.get(Instance.class, 1));
		List<BasicInstanceUser> instances = (List<BasicInstanceUser>) template.findByNamedParam("from BasicInstanceUser biu where biu.user.userId = :userId and biu.instance.instanceType.instanceTypeId = :instanceTypeId", new String[]{"userId", "instanceTypeId"}, new Object[]{userId, instanceTypeId});
		LOGGER.debug("UserDaoImpl: getInstanceByUserId: End");
		if(instances.isEmpty()){
			return null;
		}else{
			return instances.get(0);
		}
	}

	public List<Topic> getTopicsByTypeId(int instanceTypeId) throws UserException {
		LOGGER.debug("UserDaoImpl: getTopicsByTypeId: Executing");
		return (List<Topic>) template.findByNamedParam("from Topic t where t.topicType.typeId = :instanceTypeId", "instanceTypeId", instanceTypeId);
	}

	public Status getStatusByDesc(String statusDesc) throws UserException {
		LOGGER.debug("UserDaoImpl: getStatusByDesc: Executing");
		return ((List<Status>) template.findByNamedParam("from Status s where s.statusDesc = :statusDesc", "statusDesc", statusDesc)).get(0);
	}

	public Instance saveInstance(Instance instance) throws UserException {
		LOGGER.debug("UserDaoImpl: saveBasicInstance: Executing");
		int instanceId = (Integer) template.save(instance);
		return template.get(Instance.class, instanceId);
	}

	public ActivityAnswer saveActivityAnswer(ActivityAnswer activityAnswer) throws UserException {
		LOGGER.debug("UserDaoImpl: saveActivityAnswer: Executing");
		int activityAnswerId = (Integer) template.save(activityAnswer);
		return template.get(ActivityAnswer.class, activityAnswerId);
	}

	public BasicInstanceUser saveBasicInstance(BasicInstanceUser basicInstanceUser) throws UserException {
		LOGGER.debug("UserDaoImpl: saveBasicInstance: Executing");
		return (BasicInstanceUser) template.save(basicInstanceUser);
	}
	
	public BasicInstanceUser saveOrUpdateBasicInstance(BasicInstanceUser basicInstanceUser) throws UserException {
		LOGGER.debug("UserDaoImpl: saveBasicInstance: Executing");
		template.saveOrUpdate(basicInstanceUser);
		return ((List<BasicInstanceUser>) template.findByNamedParam("from BasicInstanceUser biu where biu.user.userId = :userId and biu.instance.instanceId = :instanceId", 
				new String[]{"userId", "instanceId"}, new Object[]{basicInstanceUser.getUser().getUserId(), basicInstanceUser.getInstance().getInstanceId()})).get(0);
	}

	public InstanceModule geInstanceModuleById(int instanceModuleId) throws UserException {
		LOGGER.debug("UserDaoImpl: geInstanceModuleById: Executing");
		return (InstanceModule) template.get(InstanceModule.class, instanceModuleId);
	}

	public InstanceTopic getInstanceTopicById(Integer instanceTopicId) throws UserException {
		LOGGER.debug("UserDaoImpl: getInstanceTopicById: Executing");
		return (InstanceTopic) template.get(InstanceTopic.class, instanceTopicId);
	}

	public Instance getInstanceById(Integer instanceId) throws UserException {
		LOGGER.debug("UserDaoImpl: getInstanceById: Executing");
		return (Instance) template.get(Instance.class, instanceId);
	}

	public Instance getInstanceIdByUser(Integer userId) throws UserException {
		LOGGER.debug("UserDaoImpl: getInstanceIdByUser: Start");
		List<Instance> tempList = (List<Instance>) template.find("select biu.instance from BasicInstanceUser biu where biu.user.userId = ?", userId);
//		template.delete(template.get(Instance.class, 1));
//		List<BasicInstanceUser> instances = (List<BasicInstanceUser>) template.findByNamedParam("from BasicInstanceUser biu where biu.user.userId = :userId and biu.instance.instanceType.instanceTypeId = :instanceTypeId", new String[]{"userId", "instanceTypeId"}, new Object[]{userId, instanceTypeId});
		LOGGER.debug("UserDaoImpl: getInstanceIdByUser: End");
		if(tempList.isEmpty()){
			return null;
		}else{
			return tempList.get(0);
		}
	}

	public void saveOrUpdateInstance(Instance instance) throws UserException {
		LOGGER.debug("UserDaoImpl: saveOrUpdateInstance: Executing");
		template.saveOrUpdate(instance);
	}

	public void saveOrUpdateInstanceTopic(InstanceTopic instanceTopic) throws UserException {
		LOGGER.debug("UserDaoImpl: saveOrUpdateInstanceTopic: Executing");
		template.saveOrUpdate(instanceTopic);
	}

	public void saveOrUpdateInstanceModule(InstanceModule instanceModule) throws UserException {
		LOGGER.debug("UserDaoImpl: saveOrUpdateInstanceModule: Executing");
		template.saveOrUpdate(instanceModule);
	}

}