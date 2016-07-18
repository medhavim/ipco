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
public class Activity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4427875007073263205L;

	private Integer activityId;
	
	private String activityText;
	
	private ActivityTemplate activityTemplate;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public Activity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the activityId
	 */
	public Integer getActivityId() {
		return activityId;
	}

	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	/**
	 * @return the activityText
	 */
	public String getActivityText() {
		return activityText;
	}

	/**
	 * @param activityText the activityText to set
	 */
	public void setActivityText(String activityText) {
		this.activityText = activityText;
	}

	/**
	 * @return the activityTemplate
	 */
	public ActivityTemplate getActivityTemplate() {
		return activityTemplate;
	}

	/**
	 * @param activityTemplate the activityTemplate to set
	 */
	public void setActivityTemplate(ActivityTemplate activityTemplate) {
		this.activityTemplate = activityTemplate;
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
		result = prime * result + activityId;
		result = prime * result + ((activityTemplate == null) ? 0 : activityTemplate.hashCode());
		result = prime * result + ((activityText == null) ? 0 : activityText.hashCode());
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
		Activity other = (Activity) obj;
		if (activityId != other.activityId)
			return false;
		if (activityTemplate == null) {
			if (other.activityTemplate != null)
				return false;
		} else if (!activityTemplate.equals(other.activityTemplate))
			return false;
		if (activityText == null) {
			if (other.activityText != null)
				return false;
		} else if (!activityText.equals(other.activityText))
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
		return "Activity [activityId=" + activityId + ", activityText=" + activityText + ", activityTemplate="
				+ activityTemplate + ", createdTs=" + createdTs + ", updatedTs=" + updatedTs + "]";
	}

}
