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

import com.neu.ipco.utility.AppConstants;

/**
 * @author Harsha
 *
 */
public class Module implements Serializable, Comparable<Module> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 507016717308048152L;
	
	private Integer moduleId;
	
	private String moduleName;
	
	private String moduleDesc;
	
	private Topic topic;
	
	private Set<ActivityOption> activityOptions = new TreeSet<ActivityOption>(AppConstants.ACTIVITY_OPTION_COMPARATOR);
	
	private Set<InstanceModule> instanceModules;
	
	private int orderNo;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public Module() {
	}

	public Module(String moduleName, Topic topic) {
		this.moduleName = moduleName;
		this.topic = topic;
		this.createdTs = new Date();
	}
	
	public void reorder(){
		List<ActivityOption> tempActivityOptionList = new ArrayList<ActivityOption>(this.activityOptions);
		Collections.sort(tempActivityOptionList, AppConstants.ACTIVITY_OPTION_COMPARATOR);
		this.activityOptions = new TreeSet<ActivityOption>(tempActivityOptionList);
		System.out.println("--> activity option" + this.activityOptions);
	}

	/**
	 * @return the moduleId
	 */
	public Integer getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId the moduleId to set
	 */
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName the moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}


	/**
	 * @return the moduleDesc
	 */
	public String getModuleDesc() {
		return moduleDesc;
	}

	/**
	 * @param moduleDesc the moduleDesc to set
	 */
	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
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
	 * @return the activityOptions
	 */
	public Set<ActivityOption> getActivityOptions() {
		return activityOptions;
	}

	/**
	 * @param activityOptions the activityOptions to set
	 */
	public void setActivityOptions(Set<ActivityOption> activityOptions) {
		this.activityOptions = activityOptions;
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
	 * @return the orderNo
	 */
	public int getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
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
		result = prime * result + ((moduleDesc == null) ? 0 : moduleDesc.hashCode());
		result = prime * result + ((moduleId == null) ? 0 : moduleId.hashCode());
		result = prime * result + ((moduleName == null) ? 0 : moduleName.hashCode());
		result = prime * result + orderNo;
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
		Module other = (Module) obj;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (moduleDesc == null) {
			if (other.moduleDesc != null)
				return false;
		} else if (!moduleDesc.equals(other.moduleDesc))
			return false;
		if (moduleId == null) {
			if (other.moduleId != null)
				return false;
		} else if (!moduleId.equals(other.moduleId))
			return false;
		if (moduleName == null) {
			if (other.moduleName != null)
				return false;
		} else if (!moduleName.equals(other.moduleName))
			return false;
		if (orderNo != other.orderNo)
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
		return "Module [moduleId=" + moduleId + ", moduleName=" + moduleName + ", moduleDesc=" + moduleDesc + ", topic="
				+ topic + ", orderNo=" + orderNo + ", createdTs=" + createdTs + ", updatedTs=" + updatedTs + "]";
	}

	public int compareTo(Module module) {
		return this.orderNo - module.orderNo;
	}

}
