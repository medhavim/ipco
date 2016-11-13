/**
 * 
 */
package com.neu.ipco.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.neu.ipco.entity.ActivityOption;
import com.neu.ipco.entity.Diagnostic;
import com.neu.ipco.entity.DiagnosticCategory;
import com.neu.ipco.entity.InstanceModule;
import com.neu.ipco.entity.InstanceTopic;
import com.neu.ipco.entity.Module;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.Quiz;
import com.neu.ipco.entity.RelatedDiagnostic;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.entity.User;
import com.neu.ipco.exception.AdminException;

/**
 * @author Harsha
 *
 */
public interface AdminDao {
	
	public List<Topic> loadAllTopics() throws AdminException;

	public Topic addNewTopic(Topic newTopic) throws AdminException;

	public Topic getTopicById(int topicId) throws AdminException;

	public void updateTopic(Topic topic) throws AdminException;

	public void deleteTopic(Topic topic) throws AdminException;

	public Module addNewModule(Module module) throws AdminException;

	public Module getModuleById(int moduleId) throws AdminException;

	public void updateModule(Module module) throws AdminException;

	public void deleteModule(Module module) throws AdminException;

	public ActivityOption addNewActivity(ActivityOption activityOption) throws AdminException;

	public int getActivityOptionNextOrderNo(Integer moduleId) throws AdminException;

	public int getModuleNextOrderNo(Integer topicId) throws AdminException;

	public int getTopicNextOrderNo(int topicTypeId) throws AdminException;

	public ActivityOption getActivityOptionById(int activityOptionId) throws AdminException;

	public void deleteActivityOption(ActivityOption activityOption) throws AdminException;

	public List<ActivityOption> getActivitiesByModuleId(Integer moduleId) throws AdminException;

	public void updateActivityOption(ActivityOption activityOption) throws AdminException;

	public List<Module> getModulesByTopicId(Integer topicId) throws AdminException;

	public List<Topic> getTopicsByTypeId(Integer typeId) throws AdminException;

	public ActivityOption editActivity(ActivityOption activityOption) throws AdminException;

	public void deleteOptions(Set<Option> options) throws AdminException;

	public List<DiagnosticCategory> loadAllCategories() throws AdminException;		

	public int getCategoryNextOrderNo() throws AdminException;

	public DiagnosticCategory addNewCategory(DiagnosticCategory newDiagnosticCategory) throws AdminException;

	public DiagnosticCategory getDiagnosticCategoryById(int categoryId) throws AdminException;

	public void deleteCategory(DiagnosticCategory diagnosticCategory) throws AdminException;

	public void updateCategory(DiagnosticCategory diagnosticCategory) throws AdminException;

	public List<Topic> loadCustomTopics() throws AdminException;

	public Diagnostic addNewDiagnostic(Diagnostic diagnostic) throws AdminException;

	public Diagnostic getDiagnosticById(int diagnosticId) throws AdminException;

	public List<Diagnostic> loadAllDiagnostics() throws AdminException;

	public RelatedDiagnostic addRelatedDiagnostic(RelatedDiagnostic relatedDiagnostic) throws AdminException;

	public List<RelatedDiagnostic> loadAllRelatedDiagnostics() throws AdminException;

	public RelatedDiagnostic getRelatedDiagnosticById(int relatedDiagnosticId) throws AdminException;

	public RelatedDiagnostic saveOrUpdateRelatedDiagnostic(RelatedDiagnostic relatedDiagnostic) throws AdminException;

	public void saveOrUpdateTopic(Topic topic) throws AdminException;

	public void saveQuiz(Quiz quiz) throws AdminException;

	public Quiz getQuizById(int quizId) throws AdminException;

	public void saveOrUpdateQuiz(Quiz quiz) throws AdminException;

	public void deleteDiagnostic(Diagnostic diagnostic) throws AdminException;

	public void deleteRelatedDiagnostic(RelatedDiagnostic relatedDiagnostic) throws AdminException;

	public void deleteRelatedDiagnostics(Set<RelatedDiagnostic> relatedDiagnostics) throws AdminException;
	
	public void deleteInstanceTopic(InstanceTopic instanceTopic) throws AdminException;

	public List<InstanceTopic> getInstanceTopicsByTopcId(int topicId) throws AdminException;

	public void saveOrUpdateDiagnostic(Diagnostic diagnostic) throws AdminException;

	public List<InstanceModule> getInstanceModulesByModuleId(int moduleId) throws AdminException;

	public void deleteInstanceModule(InstanceModule instanceModule) throws AdminException;

	public List<User> loadAllUsers() throws AdminException;

	public User getUserById(int userId) throws AdminException;

}
