/**
 * 
 */
package com.neu.ipco.dao;

import java.util.List;

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

	InstanceTopic saveOrUpdateInstanceTopic(InstanceTopic instanceTopic) throws UserException;

	InstanceModule saveOrUpdateInstanceModule(InstanceModule instanceModule) throws UserException;

	CustomizeInstanceUser saveOrUpdateCustomInstance(CustomizeInstanceUser customizeInstanceUser) throws UserException;

	ActivityAnswer saveOrUpdateActivityAnswer(ActivityAnswer currActivity) throws UserException;

	void deleteInstance(Instance instance) throws UserException;

	QuizAnswer saveQuizAnswer(QuizAnswer quizAnswer) throws UserException;

	InstanceQuiz saveOrUpdateInstanceQuiz(InstanceQuiz instanceQuiz) throws UserException;

	void saveOrUpdateQuizAnswer(QuizAnswer currentQuizAnswer) throws UserException;

	QuizAnswer getNextCurrentQuizAnswer(int instanceQuizId, int orderNo) throws UserException;

	InstanceQuiz getInstanceQuizById(int instanceQuizId) throws UserException;

	InstanceModule getNextCurrentInstanceModule(int instanceTopicId, int orderNo) throws UserException;

	InstanceTopic getInstanceTopicByInstanceQuizId(int instanceQuizId) throws UserException;

}
