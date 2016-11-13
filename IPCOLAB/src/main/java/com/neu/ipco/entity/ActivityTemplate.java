/**
 * 
 */
package com.neu.ipco.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Harsha
 *
 */
public class ActivityTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4730286678868951839L;
	
	private Integer activityTemplateId;
	
	private String activityTemplateName;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public ActivityTemplate() {
	}

	/**
	 * @return the activityTemplateId
	 */
	public Integer getActivityTemplateId() {
		return activityTemplateId;
	}

	/**
	 * @param activityTemplateId the activityTemplateId to set
	 */
	public void setActivityTemplateId(Integer activityTemplateId) {
		this.activityTemplateId = activityTemplateId;
	}

	/**
	 * @return the activityTemplateName
	 */
	public String getActivityTemplateName() {
		return activityTemplateName;
	}

	/**
	 * @param activityTemplateName the activityTemplateName to set
	 */
	public void setActivityTemplateName(String activityTemplateName) {
		this.activityTemplateName = activityTemplateName;
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
		result = prime * result + activityTemplateId;
		result = prime * result + ((activityTemplateName == null) ? 0 : activityTemplateName.hashCode());
		result = prime * result + ((createdTs == null) ? 0 : createdTs.hashCode());
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
		ActivityTemplate other = (ActivityTemplate) obj;
		if (activityTemplateId != other.activityTemplateId)
			return false;
		if (activityTemplateName == null) {
			if (other.activityTemplateName != null)
				return false;
		} else if (!activityTemplateName.equals(other.activityTemplateName))
			return false;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
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
		return "ActivityTemplate [activityTemplateId=" + activityTemplateId + ", activityTemplateName="
				+ activityTemplateName + ", createdTs=" + createdTs + ", updatedTs=" + updatedTs + "]";
	}
	
}
