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
import com.neu.ipco.entity.CustomizeInstanceUser;
import com.neu.ipco.entity.Instance;
import com.neu.ipco.entity.InstanceModule;
import com.neu.ipco.entity.InstanceQuiz;
import com.neu.ipco.entity.InstanceTopic;
import com.neu.ipco.entity.QuizAnswer;
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

	public InstanceTopic saveOrUpdateInstanceTopic(InstanceTopic instanceTopic) throws UserException {
		LOGGER.debug("UserDaoImpl: saveOrUpdateInstanceTopic: Executing");
		template.saveOrUpdate(instanceTopic);
		return template.get(InstanceTopic.class, instanceTopic.getInstanceTopicId());
	}

	public InstanceModule saveOrUpdateInstanceModule(InstanceModule instanceModule) throws UserException {
		LOGGER.debug("UserDaoImpl: saveOrUpdateInstanceModule: Executing");
		template.saveOrUpdate(instanceModule);
		return template.get(InstanceModule.class, instanceModule.getInstanceModuleId());
	}

	public CustomizeInstanceUser saveOrUpdateCustomInstance(CustomizeInstanceUser customizeInstanceUser)
			throws UserException {
		LOGGER.debug("UserDaoImpl: saveOrUpdateCustomInstance: Executing");
		template.saveOrUpdate(customizeInstanceUser);
		return ((List<CustomizeInstanceUser>) template.findByNamedParam("from CustomizeInstanceUser ciu where ciu.user.userId = :userId", "userId", customizeInstanceUser.getUser().getUserId())).get(0);
	}

	public ActivityAnswer saveOrUpdateActivityAnswer(ActivityAnswer currActivity) throws UserException {
		LOGGER.debug("UserDaoImpl: saveOrUpdateActivityAnswer: Executing");
		template.saveOrUpdate(currActivity);
		return template.get(ActivityAnswer.class, currActivity.getActivityAnswerId());
	}

	public void deleteInstance(Instance instance) throws UserException {
		LOGGER.debug("UserDaoImpl: deleteInstance: Executing");
		template.delete(instance);
	}

	public QuizAnswer saveQuizAnswer(QuizAnswer quizAnswer) throws UserException {
		LOGGER.debug("UserDaoImpl: saveQuizAnswer: Executing");
		int quizAnswerId = (Integer) template.save(quizAnswer);
		return template.get(QuizAnswer.class, quizAnswerId);
	}

	public InstanceQuiz saveOrUpdateInstanceQuiz(InstanceQuiz instanceQuiz) throws UserException {
		LOGGER.debug("UserDaoImpl: saveOrUpdateInstanceQuiz: Executing");
		template.saveOrUpdate(instanceQuiz);
		return template.get(InstanceQuiz.class, instanceQuiz.getInstanceQuizId());
	}

	public void saveOrUpdateQuizAnswer(QuizAnswer currentQuizAnswer) throws UserException {
		LOGGER.debug("UserDaoImpl: saveOrUpdateQuizAnswer: Executing");
		template.saveOrUpdate(currentQuizAnswer);
	}

	public QuizAnswer getNextCurrentQuizAnswer(int instanceQuizId, int orderNo) throws UserException {
		LOGGER.debug("UserDaoImpl: getNextCurrentQuizAnswer: Executing");
		InstanceQuiz instanceQuiz = getInstanceQuizById(instanceQuizId);
		for(QuizAnswer qa : instanceQuiz.getQuizAnswers()){
			if(qa.getQuizOption().getOrderNo() == orderNo){
				return qa;
			}
		}
		throw new UserException("Next Quiz Answer not found.");
	}

	public InstanceQuiz getInstanceQuizById(int instanceQuizId) throws UserException {
		LOGGER.debug("UserDaoImpl: getInstanceQuizById: Executing");
		return template.get(InstanceQuiz.class, instanceQuizId);
	}

	public InstanceModule getNextCurrentInstanceModule(int instanceTopicId, int orderNo) throws UserException {
		LOGGER.debug("UserDaoImpl: getNextCurrentInstanceModule: Start");
		List<InstanceModule> tempList = (List<InstanceModule>) template.findByNamedParam("from InstanceModule im where im.instanceTopic.instanceTopicId = :instanceTopicId "
				+ "and im.module.orderNo = :orderNo", new String[]{"instanceTopicId", "orderNo"}, new Object[]{instanceTopicId, orderNo});
		LOGGER.debug("UserDaoImpl: getNextCurrentInstanceModule: End");
		if(tempList.isEmpty()){
			return null;
		}else{
			return tempList.get(0);
		}
	}

	@Override
	public InstanceTopic getInstanceTopicByInstanceQuizId(int instanceQuizId) throws UserException {
		LOGGER.debug("UserDaoImpl: getInstanceTopicByInstanceQuizId: Start");
		List<InstanceTopic> tempList = (List<InstanceTopic>) template.findByNamedParam("from InstanceTopic it where it.quiz.instanceQuizId = :instanceQuizId", 
				new String[]{"instanceQuizId"}, new Object[]{instanceQuizId});
		LOGGER.debug("UserDaoImpl: getInstanceTopicByInstanceQuizId: End");
		if(tempList.isEmpty()){
			return null;
		}else{
			return tempList.get(0);
		}
	}

	@Override
	public CustomizeInstanceUser getCustomizeInstanceByUserId(int deleteUserId) throws UserException {
		LOGGER.debug("UserDaoImpl: getCustomizeInstanceByUserId: Start");
		List<CustomizeInstanceUser> tempList = (List<CustomizeInstanceUser>) template.findByNamedParam("from CustomizeInstanceUser ciu where ciu.user.userId = :userId", 
				"userId", deleteUserId);
		LOGGER.debug("UserDaoImpl: getCustomizeInstanceByUserId: End");
		if(tempList.isEmpty()){
			return null;
		}else{
			return tempList.get(0);
		}
	}

}