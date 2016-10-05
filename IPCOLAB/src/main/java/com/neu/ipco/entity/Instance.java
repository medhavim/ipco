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
import java.util.TreeSet;

import com.neu.ipco.constants.AppConstants;

/**
 * @author Harsha
 *
 */
public class Instance implements Serializable, Comparable<Instance> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6134768059102718534L;
	
	private Integer instanceId;
	
	private String instanceName;
	
	private InstanceType instanceType;
	
	private Set<InstanceTopic> instanceTopics = new TreeSet<InstanceTopic>(AppConstants.INSTANCE_TOPIC_COMPARATOR);
	
	private List<InstanceTopic> instanceTopicList;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public Instance() {
		// TODO Auto-generated constructor stub
	}

	public void reorder(){
		this.instanceTopicList = new ArrayList<InstanceTopic>(this.instanceTopics);
		Collections.sort(this.instanceTopicList, AppConstants.INSTANCE_TOPIC_COMPARATOR);
		
		for(InstanceTopic instanceTopic : this.instanceTopicList){
			instanceTopic.reorder();
		}
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
	 * @return the instanceType
	 */
	public InstanceType getInstanceType() {
		return instanceType;
	}

	/**
	 * @param instanceType the instanceType to set
	 */
	public void setInstanceType(InstanceType instanceType) {
		this.instanceType = instanceType;
	}

	/**
	 * @return the instanceTopics
	 */
	public Set<InstanceTopic> getInstanceTopics() {
		return instanceTopics;
	}

	/**
	 * @param instanceTopics the instanceTopics to set
	 */
	public void setInstanceTopics(Set<InstanceTopic> instanceTopics) {
		this.instanceTopics = instanceTopics;
	}

	/**
	 * @return the instanceTopicList
	 */
	public List<InstanceTopic> getInstanceTopicList() {
		return instanceTopicList;
	}

	/**
	 * @param instanceTopicList the instanceTopicList to set
	 */
	public void setInstanceTopicList(List<InstanceTopic> instanceTopicList) {
		this.instanceTopicList = instanceTopicList;
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
		result = prime * result + ((instanceId == null) ? 0 : instanceId.hashCode());
		result = prime * result + ((instanceName == null) ? 0 : instanceName.hashCode());
		result = prime * result + ((instanceType == null) ? 0 : instanceType.hashCode());
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
		if (instanceId == null) {
			if (other.instanceId != null)
				return false;
		} else if (!instanceId.equals(other.instanceId))
			return false;
		if (instanceName == null) {
			if (other.instanceName != null)
				return false;
		} else if (!instanceName.equals(other.instanceName))
			return false;
		if (instanceType == null) {
			if (other.instanceType != null)
				return false;
		} else if (!instanceType.equals(other.instanceType))
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
		return "Instance [instanceId=" + instanceId + ", instanceName=" + instanceName + ", instanceType="
				+ instanceType + ", createdTs=" + createdTs + ", updatedTs=" + updatedTs + "]";
	}

	public int compareTo(Instance instance) {
		return instance.getCreatedTs().compareTo(this.createdTs);
	}

}
