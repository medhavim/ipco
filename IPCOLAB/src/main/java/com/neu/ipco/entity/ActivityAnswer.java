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
public class ActivityAnswer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6995304591188353347L;
	
	private Integer activityAnswerId;
	
	private ActivityOption activityOption;
	
	private List<Option> answers;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public ActivityAnswer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the activityAnswerId
	 */
	public Integer getActivityAnswerId() {
		return activityAnswerId;
	}

	/**
	 * @param activityAnswerId the activityAnswerId to set
	 */
	public void setActivityAnswerId(Integer activityAnswerId) {
		this.activityAnswerId = activityAnswerId;
	}

	/**
	 * @return the activityOption
	 */
	public ActivityOption getActivityOption() {
		return activityOption;
	}

	/**
	 * @param activityOption the activityOption to set
	 */
	public void setActivityOption(ActivityOption activityOption) {
		this.activityOption = activityOption;
	}

	/**
	 * @return the answers
	 */
	public List<Option> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(List<Option> answers) {
		this.answers = answers;
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
		result = prime * result + activityAnswerId;
		result = prime * result + ((activityOption == null) ? 0 : activityOption.hashCode());
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
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
		ActivityAnswer other = (ActivityAnswer) obj;
		if (activityAnswerId != other.activityAnswerId)
			return false;
		if (activityOption == null) {
			if (other.activityOption != null)
				return false;
		} else if (!activityOption.equals(other.activityOption))
			return false;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
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
		return "ActivityAnswer [activityAnswerId=" + activityAnswerId + ", activityOption=" + activityOption
				+ ", answers=" + answers + ", createdTs=" + createdTs + ", updatedTs=" + updatedTs + "]";
	}

}
