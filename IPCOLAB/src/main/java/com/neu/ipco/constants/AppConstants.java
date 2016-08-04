/**
 * 
 */
package com.neu.ipco.constants;

import java.util.Comparator;

import com.neu.ipco.entity.ActivityAnswer;
import com.neu.ipco.entity.ActivityOption;
import com.neu.ipco.entity.InstanceModule;
import com.neu.ipco.entity.InstanceTopic;
import com.neu.ipco.entity.Module;
import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.Topic;

/**
 * @author Harsha
 *
 */
public class AppConstants {

	
//	Views
	public static final String USER_AUTH_PAGE = "userAuth";

	public static final String ERROR_PAGE = "errorPage";

	public static final String USER_HOME = "userHome";

	public static final String ADMIN_AUTH_PAGE = "adminAuth";

	public static final String USER_TYPE_ADMIN = "Admin";

	public static final String USER_TYPE_USER = "User";

	public static final String ADMIN_HOME = "adminHome";

	public static final String MANAGE_TUTORIAL = "manageTutorial";

	public static final String ADMIN_ACTIVITY = "adminActivity";
	
	public static final String EDIT_ACTIVITY = "editActivity";
	
	public static final String USER_TOPIC = "userTopic";
	
	public static final String USER_ACTIVITY = "userActivity";

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
	
	public static final String INSTANCE_TYPE_BASIC = "Basic";
	
	public static final Integer INSTANCE_TYPE_ID_BASIC = 1;

	public static final Integer TOPIC_TYPE_ID_BASIC = 1;

	public static final String STATUS_NOT_STARTED = "Not Started";
	
	public static final String STATUS_INCOMPLETE = "Incomplete";
	
	public static final Integer STATUS_COMPLETE_ID = 3;
	
//	Comparators

	public static final Comparator<InstanceModule> INSTANCE_MODULE_COMPARATOR = new Comparator<InstanceModule>() {

		public int compare(InstanceModule instanceModule1, InstanceModule instanceModule2) {
			return Integer.valueOf(instanceModule1.getModule().getOrderNo()).compareTo(instanceModule2.getModule().getOrderNo());
		}
	};

	public static final Comparator<InstanceTopic> INSTANCE_TOPIC_COMPARATOR = new Comparator<InstanceTopic>(){

		public int compare(InstanceTopic instanceTopic1, InstanceTopic instanceTopic2) {
			return Integer.valueOf(instanceTopic1.getTopic().getOrderNo()).compareTo(instanceTopic2.getTopic().getOrderNo());
		}
	};

	public static final Comparator<Option> OPTION_COMPARATOR = new Comparator<Option>() {

		public int compare(Option o1, Option o2) {
			return o1.getOrderNo() - o2.getOrderNo();
		}
	};

	public static final Comparator<ActivityAnswer> ACTIVITY_ANSWER_COMPARATOR = new Comparator<ActivityAnswer>() {

		public int compare(ActivityAnswer activityAnswer1, ActivityAnswer activityAnswer2) {
			return activityAnswer1.getActivityOption().getOrderNo() - activityAnswer2.getActivityOption().getOrderNo();
		}
	};

	public static final Comparator<ActivityOption> ACTIVITY_OPTION_COMPARATOR = new Comparator<ActivityOption>() {

		public int compare(ActivityOption activityOption1, ActivityOption activityOption2) {
			return activityOption1.getOrderNo() - activityOption2.getOrderNo();
		}
	};

	public static final Comparator<Module> MODULE_COMPARATOR = new Comparator<Module>() {

		public int compare(Module module1, Module module2) {
			return module1.getOrderNo() - module2.getOrderNo();
		}
	};

	public static final Comparator<Topic> TOPIC_COMPARATOR = new Comparator<Topic>() {

		public int compare(Topic topic1, Topic topic2) {
			return topic1.getOrderNo() - topic2.getOrderNo();
		}
	};

}
