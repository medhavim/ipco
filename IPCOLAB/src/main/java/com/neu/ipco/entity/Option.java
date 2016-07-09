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
public class Option implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8916176611295390170L;
	
	private int optionId;
	
	private String optionText;
	
	private String isCorrect;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public Option() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the optionId
	 */
	public int getOptionId() {
		return optionId;
	}

	/**
	 * @param optionId the optionId to set
	 */
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	/**
	 * @return the optionText
	 */
	public String getOptionText() {
		return optionText;
	}

	/**
	 * @param optionText the optionText to set
	 */
	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

	/**
	 * @return the isCorrect
	 */
	public String getIsCorrect() {
		return isCorrect;
	}

	/**
	 * @param isCorrect the isCorrect to set
	 */
	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
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
		result = prime * result + ((isCorrect == null) ? 0 : isCorrect.hashCode());
		result = prime * result + optionId;
		result = prime * result + ((optionText == null) ? 0 : optionText.hashCode());
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
		Option other = (Option) obj;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (isCorrect == null) {
			if (other.isCorrect != null)
				return false;
		} else if (!isCorrect.equals(other.isCorrect))
			return false;
		if (optionId != other.optionId)
			return false;
		if (optionText == null) {
			if (other.optionText != null)
				return false;
		} else if (!optionText.equals(other.optionText))
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
		return "Option [optionId=" + optionId + ", optionText=" + optionText + ", isCorrect=" + isCorrect
				+ ", createdTs=" + createdTs + ", updatedTs=" + updatedTs + "]";
	}

}