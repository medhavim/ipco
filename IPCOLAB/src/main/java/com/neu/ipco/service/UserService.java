/**
 * 
 */
package com.neu.ipco.service;

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
import com.neu.ipco.exception.ApplicationUtilException;
import com.neu.ipco.exception.UserException;

/**
 * @author harsh
 *
 */
public interface UserService {

	BasicInstanceUser getInstanceByUserId(Integer userId, int instanceTypeId) throws UserException;

	void populateTopicsForInstance(Instance instance) throws UserException;

	Instance saveInstance(Instance instance) throws UserException;

	BasicInstanceUser saveBasicInstance(BasicInstanceUser basicInstanceUser) throws UserException;

	InstanceModule geInstanceModuleById(int instanceModuleId) throws UserException;

	InstanceTopic getInstanceTopicById(Integer instanceTopicId) throws UserException;

	Instance getInstanceById(Integer instanceId) throws UserException;

	BasicInstanceUser saveOrUpdateBasicInstance(BasicInstanceUser basicInstanceUser) throws UserException;

	void populateTopicsForInstance(List<Topic> arrayList, Instance instance) throws UserException;

	CustomizeInstanceUser saveOrUpdateCustomInstance(CustomizeInstanceUser customizeInstanceUser) throws UserException;

	ActivityAnswer saveOrUpdateActivityAnswer(ActivityAnswer currActivity) throws UserException;

	InstanceModule saveOrUpdateInstanceModule(InstanceModule instanceModule) throws UserException;

	InstanceTopic saveOrUpdateInstanceTopic(InstanceTopic instanceTopic) throws UserException;

	void deleteInstance(Instance instance) throws UserException;

	QuizAnswer getFirstQuizAnswer(InstanceQuiz quiz) throws UserException;

	void updateQuizScore(InstanceQuiz instanceQuiz, QuizAnswer currentQuizAnswer) throws UserException;

	void saveOrUpdateQuizAnswer(QuizAnswer currentQuizAnswer) throws UserException;

	QuizAnswer getNextCurrentQuizAnswer(int instanceQuizId, int orderNo, String navType) throws UserException;

	void saveOrUpdateInstanceQuiz(InstanceQuiz instanceQuiz) throws UserException;

	InstanceQuiz getInstanceQuizById(int instanceQuizId) throws UserException;

	void updateQuizStatus(InstanceQuiz quiz, Status statusIncomplete) throws UserException;

	void updateActivityAnswerStatus(ActivityAnswer currActivity, Status incompleteStatus) throws UserException;

	void updateInstanceModuleStatus(InstanceModule currModule, Status incompleteStatus) throws UserException;

	void updateInstanceTopicStatus(InstanceTopic instanceTopic, Status status) throws UserException;

	InstanceModule getNextCurrentInstanceModule(int instanceTopicId, int orderNo) throws UserException;

	InstanceModule getPrevInstanceModuleForNavigation(InstanceModule instanceModule) throws UserException;

	InstanceModule getNextInstanceModuleForNavigation(InstanceModule instanceModule) throws UserException, ApplicationUtilException;

	void updateQuizAnswerStatus(QuizAnswer currentQuizAnswer, Status statusIncomplete) throws UserException;

	InstanceTopic getInstanceTopicByInstanceQuizId(int instanceQuizId) throws UserException;
}
