/**
 * 
 */
package com.neu.ipco.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import com.neu.ipco.constants.AppConstants;

/**
 * @author Harsha
 *
 */
public class InstanceModule implements Serializable, Comparable<InstanceModule> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6393654052425460098L;
	
	private Integer instanceModuleId;
	
	private Module module;
	
	private Status status;
	
	private int progress = 0;
	
	private Set<ActivityAnswer> activityAnswers = new TreeSet<ActivityAnswer>(AppConstants.ACTIVITY_ANSWER_COMPARATOR);
	
	private List<ActivityAnswer> activityAnswerList;
	
	private InstanceTopic instanceTopic;
	
	private Stack<ActivityAnswer> prevActivity = new Stack<ActivityAnswer>();
	
	private ActivityAnswer currActivity = new ActivityAnswer();
	
	private Stack<ActivityAnswer> nextActivity = new Stack<ActivityAnswer>();
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public InstanceModule() {
		// TODO Auto-generated constructor stub
	}
	
	public void reorder() {
		this.activityAnswerList = new ArrayList<ActivityAnswer>(this.activityAnswers);
		Collections.sort(this.activityAnswerList, AppConstants.ACTIVITY_ANSWER_COMPARATOR);
	}
	
	public void prepareStack() {
		
		nextActivity.clear();
		prevActivity.clear();	
		List<ActivityAnswer> tempActivityAnswerList = new ArrayList<ActivityAnswer>(activityAnswerList);
		Collections.sort(tempActivityAnswerList, AppConstants.ACTIVITY_ANSWER_COMPARATOR_REVERSE);
		nextActivity.addAll(tempActivityAnswerList);
		
		if(!nextActivity.isEmpty()){
			ActivityAnswer lastActivity = nextActivity.firstElement();
			currActivity = nextActivity.pop();
			if(lastActivity.getStatus().getStatusId() != AppConstants.STATUS_COMPLETE_ID){
				while(!nextActivity.empty() && currActivity.getStatus().getStatusId() != AppConstants.STATUS_INCOMPLETE_ID){
					if(currActivity.getStatus().getStatusId() == AppConstants.STATUS_COMPLETE_ID){
						prevActivity.push(currActivity);
					}
					currActivity = nextActivity.pop();
				}
			}
		}
	}
	
	public void prepareStack(ActivityAnswer activityAnswer) {
		
		nextActivity.clear();
		prevActivity.clear();	
		List<ActivityAnswer> tempActivityAnswerList = new ArrayList<ActivityAnswer>(activityAnswerList);
		Collections.sort(tempActivityAnswerList, AppConstants.ACTIVITY_ANSWER_COMPARATOR_REVERSE);
		nextActivity.addAll(tempActivityAnswerList);
		while(!nextActivity.empty()){
			currActivity = nextActivity.pop();
			if(currActivity.getActivityAnswerId() == activityAnswer.getActivityAnswerId()){
				break;
			}
			prevActivity.push(currActivity);
		}
	}
	
	public void prepareNextModuleStack() {
		
		nextActivity.clear();
		prevActivity.clear();	
		List<ActivityAnswer> tempActivityAnswerList = new ArrayList<ActivityAnswer>(activityAnswerList);
		Collections.sort(tempActivityAnswerList, AppConstants.ACTIVITY_ANSWER_COMPARATOR_REVERSE);
		nextActivity.addAll(tempActivityAnswerList);
		
		if(!nextActivity.isEmpty()){
			currActivity = nextActivity.pop();
		}
	}
	
	public void preparePreviousModuleStack() {
		
		nextActivity.clear();
		prevActivity.clear();	
		List<ActivityAnswer> tempActivityAnswerList = new ArrayList<ActivityAnswer>(activityAnswerList);
		Collections.sort(tempActivityAnswerList, AppConstants.ACTIVITY_ANSWER_COMPARATOR_REVERSE);
		nextActivity.addAll(tempActivityAnswerList);
		
		if(!nextActivity.isEmpty()){
			ActivityAnswer lastActivity = nextActivity.firstElement();
			currActivity = nextActivity.pop();
			while(!nextActivity.empty() && currActivity.getActivityAnswerId() != lastActivity.getActivityAnswerId()){
				prevActivity.push(currActivity);
				currActivity = nextActivity.pop();
			}
		}
	}
	
	/**
	 * @return the createdTs
	 */
	public Date getCreatedTs() {
		return createdTs;
	}

	/**
	 * @param createdTs the createdTs to set
	 */
	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	/**
	 * @return the updatedTs
	 */
	public Date getUpdatedTs() {
		return updatedTs;
	}

	/**
	 * @param updatedTs the updatedTs to set
	 */
	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}

	/**
	 * @return the instanceModuleId
	 */
	public Integer getInstanceModuleId() {
		return instanceModuleId;
	}

	/**
	 * @param instanceModuleId the instanceModuleId to set
	 */
	public void setInstanceModuleId(Integer instanceModuleId) {
		this.instanceModuleId = instanceModuleId;
	}

	/**
	 * @return the module
	 */
	public Module getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(Module module) {
		this.module = module;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the progress
	 */
	public int getProgress() {
		return progress;
	}

	/**
	 * @param progress the progress to set
	 */
	public void setProgress(int progress) {
		this.progress = progress;
	}

	/**
	 * @return the activityAnswers
	 */
	public Set<ActivityAnswer> getActivityAnswers() {
		return activityAnswers;
	}

	/**
	 * @param activityAnswers the activityAnswers to set
	 */
	public void setActivityAnswers(Set<ActivityAnswer> activityAnswers) {
		this.activityAnswers = activityAnswers;
	}


	/**
	 * @return the instanceTopic
	 */
	public InstanceTopic getInstanceTopic() {
		return instanceTopic;
	}

	/**
	 * @param instanceTopic the instanceTopic to set
	 */
	public void setInstanceTopic(InstanceTopic instanceTopic) {
		this.instanceTopic = instanceTopic;
	}


	/**
	 * @return the activityAnswerList
	 */
	public List<ActivityAnswer> getActivityAnswerList() {
		return activityAnswerList;
	}

	/**
	 * @param activityAnswerList the activityAnswerList to set
	 */
	public void setActivityAnswerList(List<ActivityAnswer> activityAnswerList) {
		this.activityAnswerList = activityAnswerList;
	}

	/**
	 * @return the prevActivity
	 */
	public Stack<ActivityAnswer> getPrevActivity() {
		return prevActivity;
	}

	/**
	 * @param prevActivity the prevActivity to set
	 */
	public void setPrevActivity(Stack<ActivityAnswer> prevActivity) {
		this.prevActivity = prevActivity;
	}

	/**
	 * @return the currActivity
	 */
	public ActivityAnswer getCurrActivity() {
		return currActivity;
	}

	/**
	 * @param currActivity the currActivity to set
	 */
	public void setCurrActivity(ActivityAnswer currActivity) {
		this.currActivity = currActivity;
	}

	/**
	 * @return the nextActivity
	 */
	public Stack<ActivityAnswer> getNextActivity() {
		return nextActivity;
	}

	/**
	 * @param nextActivity the nextActivity to set
	 */
	public void setNextActivity(Stack<ActivityAnswer> nextActivity) {
		this.nextActivity = nextActivity;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InstanceModule [instanceModuleId=" + instanceModuleId + ", module=" + module + ", status=" + status
				+ ", progress=" + progress + ", instanceTopic=" + instanceTopic + ", createdTs=" + createdTs
				+ ", updatedTs=" + updatedTs + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((instanceModuleId == null) ? 0 : instanceModuleId.hashCode());
		result = prime * result + ((instanceTopic == null) ? 0 : instanceTopic.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + progress;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((updatedTs == null) ? 0 : updatedTs.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstanceModule other = (InstanceModule) obj;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (instanceModuleId == null) {
			if (other.instanceModuleId != null)
				return false;
		} else if (!instanceModuleId.equals(other.instanceModuleId))
			return false;
		if (instanceTopic == null) {
			if (other.instanceTopic != null)
				return false;
		} else if (!instanceTopic.equals(other.instanceTopic))
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (progress != other.progress)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (updatedTs == null) {
			if (other.updatedTs != null)
				return false;
		} else if (!updatedTs.equals(other.updatedTs))
			return false;
		return true;
	}

	public int compareTo(InstanceModule instanceModule) {
		return this.module.getOrderNo() - instanceModule.getModule().getOrderNo();
	}

}
