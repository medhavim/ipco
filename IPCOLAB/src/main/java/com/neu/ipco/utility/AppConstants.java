/**
 * 
 */
package com.neu.ipco.utility;

import java.util.Comparator;

import com.neu.ipco.entity.ActivityAnswer;
import com.neu.ipco.entity.ActivityOption;
import com.neu.ipco.entity.InstanceModule;
import com.neu.ipco.entity.InstanceTopic;
import com.neu.ipco.entity.Module;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.entity.User;

/**
 * @author Harsha
 *
 */
public class AppConstants {
	
//	Model Attributes
	
	public static final String MODEL_ATTRIBUTE_CURRENT_INSTANCE_MODULE_ID = "currInstanceModuleId";

	public static final String MODEL_ATTRIBUTE_CURRENT_INSTANCE_QUIZ_ID = "currInstanceQuizId";
	
//	Session Attributes

	public static final String SESSION_ATTRIBUTE_INSTANCE = "instance";
	
	public static final String SESSION_ATTRIBUTE_INSTANCE_MODULE = "instanceModule";
	
	public static final String SESSION_ATTRIBUTE_INSTANCE_TOPIC = "instanceTopic";

	public static final String SESSION_ATTRIBUTE_INSTANCE_QUIZ = "quiz";

	public static final String SESSION_ATTRIBUTE_CURRENT_QUIZ_ANSWER = "currentQuizAnswer";
	
	public static final String SESSION_ATTRIBUTE_USER = "user";

	public static final String SESSION_ATTRIBUTE_ADMIN = "admin";
	
	public static final String SESSION_ATTRIBUTE_CUSTOM_INSTANCE = "customInstance";

	public static final String SESSION_ATTRIBUTE_REVIEW_USER = "reviewUser";	

	public static final String SESSION_ATTRIBUTE_REGISTERED_USERS = "registeredUsers";

	public static final String SESSION_ATTRIBUTE_SORT_BY = "sortBy";

	public static final String SESSION_ATTRIBUTE_USER_ROLES = "userRoles";
	
	
//	Declaring constants
	public static final String TRUE = "true";
	
	public static final String FALSE = "false";
	
//	Sort by constants
	
	public static final String SORT_BY_FIRST_NAME = "firstName";

	public static final String SORT_BY_LAST_NAME = "lastName";

	public static final String SORT_BY_LAST_LOGIN = "lastLogin";

	public static final String SORT_BY_REGISTERED = "registered";

	public static final String DEFAULT_SORT_BY = "lastLogin";

	
//	Nav Type Constants
	
	public static final String NAV_TYPE_PREVIOUS_QUIZ = "prev-quiz";
	
	public static final String NAV_TYPE_NEXT_QUIZ = "next-quiz";
	
	public static final String NAV_TYPE_QUIZ_FINISH = "quiz-finish";
	
	public static final String NAV_TYPE_GOTO_DASHBOARD = "go-to-dashboard";
	
	public static final String NAV_TYPE_NEXT_MODULE = "next-module";
	
	public static final String NAV_TYPE_PREV_MODULE = "prev-module";

	public static final String NAV_TYPE_TAKE_QUIZ = "take-quiz";
	
	public static final String NAV_TYPE_NEXT_ACTIVITY = "next-activity";

	public static final String NAV_TYPE_PREV_ACTIVITY = "prev-activity";


//	Views
	public static final String USER_AUTH_PAGE = "userAuth";

	public static final String ERROR_PAGE = "errorPage";

	public static final String USER_HOME = "userHome";

	public static final String ADMIN_AUTH_PAGE = "adminAuth";

	public static final String ADMIN_HOME = "adminHome";

	public static final String MANAGE_TUTORIAL = "manageTutorial";

	public static final String ADMIN_ACTIVITY = "adminActivity";
	
	public static final String EDIT_ACTIVITY = "editActivity";
	
	public static final String USER_TOPIC = "userTopic";
	
	public static final String USER_ACTIVITY = "userActivity";

	public static final String MANAGE_DIAGNOSTIC = "manageDiagnostic";
	
	public static final String ADMIN_DIAGNOSTIC = "adminDiagnostic";
	
	public static final String USER_PROFILE = "userProfile";
	
	public static final String USER_DIAGNOSTIC = "userDiagnostic";
	
	public static final String ADMIN_RELATED_DIAGNOSTIC = "adminRelatedDiagnostic";
	
	public static final String MANAGE_RELATED_DIAGNOSTIC = "manageRelatedDiagnostic";
	
	public static final String EDIT_RELATED_DIAGNOSTIC = "editRelatedDiagnostic";
	
	public static final String MANAGE_QUIZ = "manageQuiz";
	
	public static final String ADMIN_QUIZ = "adminQuiz";
	
	public static final String USER_QUIZ = "userQuiz";
	
	public static final String USER_QUIZ_REPORT = "userQuizReport";

	public static final String MANAGE_USER = "manageUser";

	public static final String REVIEW_USER_PROFILE = "manageUserPages/reviewUserProfile";

	public static final String REVIEW_USER_INSTANCE = "manageUserPages/reviewUserInstance";

	public static final String REVIEW_USER_TOPIC = "manageUserPages/reviewUserTopic";

	public static final String EDIT_DIAGNOSTIC = "editDiagnostic";

	public static final String EDIT_QUIZ_OPTION = "editQuizOption";
	
//	Template ids
	public static final Integer TEMPLATE_MCQ = 1;

	public static final Integer TEMPLATE_YESNO = 2;

	public static final Integer TEMPLATE_IMAGE_DESC = 3;

	public static final Integer TEMPLATE_IMAGE_MCQ = 4;

	public static final Integer TEMPLATE_IMAGE_YESNO = 5;

	public static final Integer TEMPLATE_VIDEO_DESC = 6;

	public static final Integer TEMPLATE_VIDEO_MCQ = 7;
	
	public static final Integer TEMPLATE_VIDEO_YESNO = 8;

	public static final Integer TEMPLATE_INFO = 9;

//	Path to save image/video
	
	public static final String IMAGE_ABSOLUTE_PATH ="/usr/ipco/resources/images/";

	public static final String IMAGE_RELATIVE_PATH ="resources/images/";

	public static final String VIDEO_ABSOLUTE_PATH ="/usr/ipco/resources/videos/";

	public static final String VIDEO_RELATIVE_PATH ="resources/videos/";

//	Type ids

	public static final String USER_TYPE_ADMIN = "Admin";

	public static final String USER_TYPE_USER = "User";
	
	public static final String INSTANCE_TYPE_BASIC = "Basic";
	
	public static final String INSTANCE_TYPE_CUSTOM = "Custom";
	
	public static final Integer INSTANCE_TYPE_ID_BASIC = 1;

	public static final Integer TOPIC_TYPE_ID_BASIC = 1;
	
	public static final Integer TOPIC_TYPE_ID_CUSTOM = 2;

	public static final String STATUS_NOT_STARTED = "Not Started";
	
	public static final String STATUS_INCOMPLETE = "Incomplete";
	
	public static final Integer STATUS_NOT_STARTED_ID = 1;
	
	public static final Integer STATUS_INCOMPLETE_ID = 2;

	public static final Integer STATUS_COMPLETE_ID = 3;

	public static final int USER_TYPE_ID_USER = 2;
	
//	Comparators

	public static final Comparator<InstanceModule> INSTANCE_MODULE_COMPARATOR = new Comparator<InstanceModule>() {

		public int compare(InstanceModule instanceModule1, InstanceModule instanceModule2) {
			if(instanceModule1 == null
					|| instanceModule2 == null){
				return 0;
			}else{
				instanceModule1.reorder();
				instanceModule2.reorder();
				if(instanceModule1.getModule() == null){
					return 1;
				}else if(instanceModule2.getModule() == null){
					return -1;
				}else{
					return Integer.valueOf(instanceModule1.getModule().getOrderNo()).compareTo(instanceModule2.getModule().getOrderNo());
				}
			}
		}
	};
	
	public static final Comparator<InstanceModule> INSTANCE_MODULE_COMPARATOR_REVERSE = new Comparator<InstanceModule>() {

		public int compare(InstanceModule instanceModule1, InstanceModule instanceModule2) {
			if(instanceModule1 == null
					|| instanceModule2 == null){
				return 0;
			}else if(instanceModule1.getModule() == null){
				return -1;
			}else if(instanceModule2.getModule() == null){
				return 1;
			}else{
				return Integer.valueOf(instanceModule2.getModule().getOrderNo()).compareTo(instanceModule1.getModule().getOrderNo());
			}
		}
	};

	public static final Comparator<InstanceTopic> INSTANCE_TOPIC_COMPARATOR = new Comparator<InstanceTopic>(){

		public int compare(InstanceTopic instanceTopic1, InstanceTopic instanceTopic2) {
			if(instanceTopic1 == null
					|| instanceTopic2 == null){
				return 0;
			}else if(instanceTopic1.getTopic() == null){
				return 1;
			}else if(instanceTopic2.getTopic() == null){
				return -1;
			}else{
				return Integer.valueOf(instanceTopic1.getTopic().getOrderNo()).compareTo(instanceTopic2.getTopic().getOrderNo());
			}
		}
	};

	public static final Comparator<Option> OPTION_COMPARATOR = new Comparator<Option>() {

		public int compare(Option o1, Option o2) {
			return o1.getOrderNo() - o2.getOrderNo();
		}
	};
	
	public static final Comparator<ActivityAnswer> ACTIVITY_ANSWER_COMPARATOR = new Comparator<ActivityAnswer>() {
		
		public int compare(ActivityAnswer activityAnswer1, ActivityAnswer activityAnswer2) {
			if(activityAnswer1 == null
					|| activityAnswer2 == null){
				return 0;
			}else if(activityAnswer1.getActivityOption() == null){
				return 1;
			}else if(activityAnswer2.getActivityOption() == null){
				return -1;
			}else{
				return Integer.valueOf(activityAnswer1.getActivityOption().getOrderNo()).compareTo(Integer.valueOf(activityAnswer2.getActivityOption().getOrderNo()));
			}
		}
	};

	public static final Comparator<ActivityAnswer> ACTIVITY_ANSWER_COMPARATOR_REVERSE = new Comparator<ActivityAnswer>() {

		public int compare(ActivityAnswer activityAnswer1, ActivityAnswer activityAnswer2) {
			if(activityAnswer1 == null
					|| activityAnswer2 == null){
				return 0;
			}else if(activityAnswer2.getActivityOption() == null){
				return 1;
			}else if(activityAnswer1.getActivityOption() == null){
				return -1;
			}else{
				return Integer.valueOf(activityAnswer2.getActivityOption().getOrderNo()).compareTo(Integer.valueOf(activityAnswer1.getActivityOption().getOrderNo()));
			}
		}
	};

	public static final Comparator<ActivityOption> ACTIVITY_OPTION_COMPARATOR = new Comparator<ActivityOption>() {

		public int compare(ActivityOption activityOption1, ActivityOption activityOption2) {
			if(activityOption1 == null
					|| activityOption2 == null){
				return 0;
			}else{
				return activityOption1.getOrderNo() - activityOption2.getOrderNo();
			}
		}
	};

	public static final Comparator<Module> MODULE_COMPARATOR = new Comparator<Module>() {

		public int compare(Module module1, Module module2) {
			if(module1 == null
					|| module2 == null){
				return 0;
			}else{
				return module1.getOrderNo() - module2.getOrderNo();
			}
		}
	};

	public static final Comparator<Topic> TOPIC_COMPARATOR = new Comparator<Topic>() {

		public int compare(Topic topic1, Topic topic2) {
			if(topic1 == null
					|| topic2 == null){
				return 0;
			}else{
				topic1.reorder();
				topic2.reorder();
				return topic1.getOrderNo() - topic2.getOrderNo();
			}
		}
	};

	public static final Comparator<User> SORT_FIRST_NAME_COMPARATOR = new Comparator<User>(){

		@Override
		public int compare(User user1, User user2) {
			if(user1 == null
					|| user2 == null){
				return 0;
			}else if(user1.getFirstName() == null){
				return 1;
			}else if(user2.getFirstName() == null){
				return -1;
			}else{
				return user1.getFirstName().toLowerCase().compareTo(user2.getFirstName().toLowerCase());
			}
		}
	};

	public static final Comparator<User> SORT_LAST_NAME_COMPARATOR = new Comparator<User>(){

		@Override
		public int compare(User user1, User user2) {
			if(user1 == null
					|| user2 == null){
				return 0;
			}else if(user1.getLastName() == null){
				return 1;
			}else if(user2.getLastName() == null){
				return -1;
			}else{
				return user1.getLastName().toLowerCase().compareTo(user2.getLastName().toLowerCase());
			}
		}
	};
	
	public static final Comparator<User> SORT_LAST_LOGIN_COMPARATOR = new Comparator<User>() {
		
		public int compare(User user1, User user2) {
			if(user1 == null
					|| user1.getCredential() == null
					|| user1.getCredential().getUpdatedTs() == null){
				return 1;
			}else if(user2 == null
					|| user2.getCredential() == null
					|| user2.getCredential().getUpdatedTs() == null){
				return -1;
			}else{
				return user2.getCredential().getUpdatedTs().compareTo(user1.getCredential().getUpdatedTs());
			}
		}
	};
	
	public static final Comparator<User> SORT_REGISTERED_COMPARATOR = new Comparator<User>() {
		
		public int compare(User user1, User user2) {
			if(user1 == null
					|| user1.getCredential() == null
					|| user1.getCredential().getCreatedTs() == null){
				return 1;
			}else if(user2 == null
					|| user2.getCredential() == null
					|| user2.getCredential().getCreatedTs() == null){
				return -1;
			}else{
				return user2.getCredential().getCreatedTs().compareTo(user1.getCredential().getCreatedTs());
			}
		}
	};

}
