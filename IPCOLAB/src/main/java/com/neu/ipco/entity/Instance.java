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
public class Instance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6134768059102718534L;
	
	private Integer instanceId;
	
	private String instanceName;
	
	private List<InstanceTopic> instanceTopics;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public Instance() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the instanceId
	 */
	public Integer getInstanceId() {
		return instanceId;
	}

	/**
	 * @param instanceId the instanceId to set
	 */
	public void setInstanceId(Integer instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * @return the instanceName
	 */
	public String getInstanceName() {
		return instanceName;
	}

	/**
	 * @param instanceName the instanceName to set
	 */
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	/**
	 * @return the instanceTopics
	 */
	public List<InstanceTopic> getInstanceTopics() {
		return instanceTopics;
	}

	/**
	 * @param instanceTopics the instanceTopics to set
	 */
	public void setInstanceTopics(List<InstanceTopic> instanceTopics) {
		this.instanceTopics = instanceTopics;
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
		result = prime * result + instanceId;
		result = prime * result + ((instanceName == null) ? 0 : instanceName.hashCode());
		result = prime * result + ((instanceTopics == null) ? 0 : instanceTopics.hashCode());
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
		Instance other = (Instance) obj;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (instanceId != other.instanceId)
			return false;
		if (instanceName == null) {
			if (other.instanceName != null)
				return false;
		} else if (!instanceName.equals(other.instanceName))
			return false;
		if (instanceTopics == null) {
			if (other.instanceTopics != null)
				return false;
		} else if (!instanceTopics.equals(other.instanceTopics))
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
		return "Instance [instanceId=" + instanceId + ", instanceName=" + instanceName + ", instanceTopics="
				+ instanceTopics + ", createdTs=" + createdTs + ", updatedTs=" + updatedTs + "]";
	}
	
}
