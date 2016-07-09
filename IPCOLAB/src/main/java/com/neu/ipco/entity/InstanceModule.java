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
public class InstanceModule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6393654052425460098L;
	
	private int instanceModuleId;
	
	private Module module;
	
	private Status status;
	
	private List<ActivityAnswer> activityAnswers;
	
	private Date createdTs;
	
	private Date updatedTs;
	
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

	public InstanceModule() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the instanceModuleId
	 */
	public int getInstanceModuleId() {
		return instanceModuleId;
	}

	/**
	 * @param instanceModuleId the instanceModuleId to set
	 */
	public void setInstanceModuleId(int instanceModuleId) {
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
	 * @return the activityAnswers
	 */
	public List<ActivityAnswer> getActivityAnswers() {
		return activityAnswers;
	}

	/**
	 * @param activityAnswers the activityAnswers to set
	 */
	public void setActivityAnswers(List<ActivityAnswer> activityAnswers) {
		this.activityAnswers = activityAnswers;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityAnswers == null) ? 0 : activityAnswers.hashCode());
		result = prime * result + ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + instanceModuleId;
		result = prime * result + ((module == null) ? 0 : module.hashCode());
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
		if (activityAnswers == null) {
			if (other.activityAnswers != null)
				return false;
		} else if (!activityAnswers.equals(other.activityAnswers))
			return false;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (instanceModuleId != other.instanceModuleId)
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InstanceModule [instanceModuleId=" + instanceModuleId + ", module=" + module + ", status=" + status
				+ ", activityAnswers=" + activityAnswers + ", createdTs=" + createdTs + ", updatedTs=" + updatedTs
				+ "]";
	}
	
}
