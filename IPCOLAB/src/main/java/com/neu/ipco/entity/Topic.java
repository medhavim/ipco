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
public class Topic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6103128993812139770L;
	
	private int topicId;
	
	private String topicName;
	
	private List<Module> modules;
	
	private TopicType topicType;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public Topic() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the topicId
	 */
	public int getTopicId() {
		return topicId;
	}

	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	/**
	 * @return the topicName
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * @param topicName the topicName to set
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	/**
	 * @return the modules
	 */
	public List<Module> getModules() {
		return modules;
	}

	/**
	 * @param modules the modules to set
	 */
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	/**
	 * @return the topicType
	 */
	public TopicType getTopicType() {
		return topicType;
	}

	/**
	 * @param topicType the topicType to set
	 */
	public void setTopicType(TopicType topicType) {
		this.topicType = topicType;
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
		result = prime * result + ((modules == null) ? 0 : modules.hashCode());
		result = prime * result + topicId;
		result = prime * result + ((topicName == null) ? 0 : topicName.hashCode());
		result = prime * result + ((topicType == null) ? 0 : topicType.hashCode());
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
		Topic other = (Topic) obj;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (modules == null) {
			if (other.modules != null)
				return false;
		} else if (!modules.equals(other.modules))
			return false;
		if (topicId != other.topicId)
			return false;
		if (topicName == null) {
			if (other.topicName != null)
				return false;
		} else if (!topicName.equals(other.topicName))
			return false;
		if (topicType == null) {
			if (other.topicType != null)
				return false;
		} else if (!topicType.equals(other.topicType))
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
		return "Topic [topicId=" + topicId + ", topicName=" + topicName + ", modules=" + modules + ", topicType="
				+ topicType + ", createdTs=" + createdTs + ", updatedTs=" + updatedTs + "]";
	}

}