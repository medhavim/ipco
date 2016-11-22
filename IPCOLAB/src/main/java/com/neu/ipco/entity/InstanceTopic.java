/**
 * 
 */
package com.neu.ipco.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import com.neu.ipco.utility.AppConstants;

/**
 * @author Harsha
 *
 */
public class InstanceTopic implements Serializable, Comparable<InstanceTopic> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6906191818448486847L;
	
	private Integer instanceTopicId;
	
	private Topic topic;
	
	private Status status;
	
	private int progress = 0;
	
	private Set<InstanceModule> instanceModules = new TreeSet<InstanceModule>(AppConstants.INSTANCE_MODULE_COMPARATOR);
	
	private List<InstanceModule> instanceModuleList;

	private Instance instance;
	
	private Stack<InstanceModule> prevModules = new Stack<InstanceModule>();
	
	private InstanceModule currModule = new InstanceModule();
	
	private Stack<InstanceModule> nextModules = new Stack<InstanceModule>();
	
	private InstanceQuiz quiz;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public InstanceTopic() {
	}

	public void reorder() {
//		populate instanceModuleList from the instanceModules(Set) and sort them.
		this.instanceModuleList = new ArrayList<InstanceModule>(this.instanceModules);
		Collections.sort(this.instanceModuleList, AppConstants.INSTANCE_MODULE_COMPARATOR);
		updateTopicProgress();
	}
	
	private void updateTopicProgress() {
		double progress = 0;
		for(InstanceModule instanceModule : instanceModuleList){
			if(instanceModule.getStatus().getStatusId() == AppConstants.STATUS_COMPLETE_ID){
				progress++;
			}
		}
		this.progress = (int) (progress/instanceModuleList.size() * 100);
	}

	public void prepareStack() {
		nextModules.clear();
		prevModules.clear();
		List<InstanceModule> tempInstanceModuleList = new ArrayList<InstanceModule>(instanceModuleList);
		Collections.sort(tempInstanceModuleList, AppConstants.INSTANCE_MODULE_COMPARATOR_REVERSE);
		nextModules.addAll(tempInstanceModuleList);
		if(!nextModules.isEmpty()){
			InstanceModule lastModule = nextModules.firstElement();
			currModule = nextModules.pop();
			if(lastModule.getStatus().getStatusId() != AppConstants.STATUS_COMPLETE_ID){
				while(!nextModules.empty() && currModule.getStatus().getStatusId() != AppConstants.STATUS_INCOMPLETE_ID){
					if(currModule.getStatus().getStatusId() == AppConstants.STATUS_COMPLETE_ID){
						prevModules.push(currModule);
					}
					currModule = nextModules.pop();
				}
			}
		}
	}
	
	public void prepareStack(InstanceModule instanceModule) {
		
		nextModules.clear();
		prevModules.clear();
		List<InstanceModule> tempInstanceModuleList = new ArrayList<InstanceModule>(instanceModuleList);
		Collections.sort(tempInstanceModuleList, AppConstants.INSTANCE_MODULE_COMPARATOR_REVERSE);
		nextModules.addAll(tempInstanceModuleList);
		while(!nextModules.empty()){
			currModule = nextModules.pop();
			if(currModule.getInstanceModuleId().equals(instanceModule.getInstanceModuleId())){
				break;
			}
			prevModules.push(currModule);
		}
	}

	/**
	 * @return the instanceModuleList
	 */
	public List<InstanceModule> getInstanceModuleList() {
		return instanceModuleList;
	}
	
	/**
	 * @param instanceModuleList the instanceModuleList to set
	 */
	public void setInstanceModuleList(List<InstanceModule> instanceModuleList) {
		this.instanceModuleList = instanceModuleList;
	}
	
	/**
	 * @return the instanceTopicId
	 */
	public Integer getInstanceTopicId() {
		return instanceTopicId;
	}

	/**
	 * @param instanceTopicId the instanceTopicId to set
	 */
	public void setInstanceTopicId(Integer instanceTopicId) {
		this.instanceTopicId = instanceTopicId;
	}

	/**
	 * @return the topic
	 */
	public Topic getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
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
	 * @return the instanceModules
	 */
	public Set<InstanceModule> getInstanceModules() {
		return instanceModules;
	}

	/**
	 * @param instanceModules the instanceModules to set
	 */
	public void setInstanceModules(Set<InstanceModule> instanceModules) {
		this.instanceModules = instanceModules;
	}

	/**
	 * @return the instance
	 */
	public Instance getInstance() {
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	public void setInstance(Instance instance) {
		this.instance = instance;
	}

	/**
	 * @return the quiz
	 */
	public InstanceQuiz getQuiz() {
		return quiz;
	}

	/**
	 * @param quiz the quiz to set
	 */
	public void setQuiz(InstanceQuiz quiz) {
		this.quiz = quiz;
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
	 * @return the prevModules
	 */
	public Stack<InstanceModule> getPrevModules() {
		return prevModules;
	}

	/**
	 * @param prevModules the prevModules to set
	 */
	public void setPrevModules(Stack<InstanceModule> prevModules) {
		this.prevModules = prevModules;
	}

	/**
	 * @return the currModule
	 */
	public InstanceModule getCurrModule() {
		return currModule;
	}

	/**
	 * @param currModule the currModule to set
	 */
	public void setCurrModule(InstanceModule currModule) {
		this.currModule = currModule;
	}

	/**
	 * @return the nextModules
	 */
	public Stack<InstanceModule> getNextModules() {
		return nextModules;
	}

	/**
	 * @param nextModules the nextModules to set
	 */
	public void setNextModules(Stack<InstanceModule> nextModules) {
		this.nextModules = nextModules;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InstanceTopic [instanceTopicId=" + instanceTopicId + ", topic=" + topic + ", status=" + status
				+ ", progress=" + progress + ", quiz=" + quiz + ", createdTs=" + createdTs + ", updatedTs=" + updatedTs
				+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((instanceTopicId == null) ? 0 : instanceTopicId.hashCode());
		result = prime * result + progress;
		result = prime * result + ((quiz == null) ? 0 : quiz.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
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
		InstanceTopic other = (InstanceTopic) obj;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (instanceTopicId == null) {
			if (other.instanceTopicId != null)
				return false;
		} else if (!instanceTopicId.equals(other.instanceTopicId))
			return false;
		if (progress != other.progress)
			return false;
		if (quiz == null) {
			if (other.quiz != null)
				return false;
		} else if (!quiz.equals(other.quiz))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		if (updatedTs == null) {
			if (other.updatedTs != null)
				return false;
		} else if (!updatedTs.equals(other.updatedTs))
			return false;
		return true;
	}

	public int compareTo(InstanceTopic instanceTopic) {
		return this.topic.getOrderNo() - instanceTopic.getTopic().getOrderNo();
	}

}
