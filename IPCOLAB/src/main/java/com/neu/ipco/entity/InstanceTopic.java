/**
 * 
 */
package com.neu.ipco.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Harsha
 *
 */
public class InstanceTopic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6906191818448486847L;
	
	private int instanceTopicId;
	
	private Topic topic;
	
	private Status status;
	
	private List<InstanceModule> instanceModules;
	
	private InstanceQuiz quiz;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public InstanceTopic() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the instanceTopicId
	 */
	public int getInstanceTopicId() {
		return instanceTopicId;
	}

	/**
	 * @param instanceTopicId the instanceTopicId to set
	 */
	public void setInstanceTopicId(int instanceTopicId) {
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
	 * @return the instanceModules
	 */
	public List<InstanceModule> getInstanceModules() {
		return instanceModules;
	}

	/**
	 * @param instanceModules the instanceModules to set
	 */
	public void setInstanceModules(List<InstanceModule> instanceModules) {
		this.instanceModules = instanceModules;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((instanceModules == null) ? 0 : instanceModules.hashCode());
		result = prime * result + instanceTopicId;
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
		if (instanceModules == null) {
			if (other.instanceModules != null)
				return false;
		} else if (!instanceModules.equals(other.instanceModules))
			return false;
		if (instanceTopicId != other.instanceTopicId)
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InstanceTopic [instanceTopicId=" + instanceTopicId + ", topic=" + topic + ", status=" + status
				+ ", instanceModules=" + instanceModules + ", quiz=" + quiz + ", createdTs=" + createdTs
				+ ", updatedTs=" + updatedTs + "]";
	}

}
