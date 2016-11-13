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
public class UserType implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4185306040769753636L;

	private Integer userTypeId;
	
	private String userTypeDesc;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public UserType() {
	}

	/**
	 * @return the userTypeId
	 */
	public Integer getUserTypeId() {
		return userTypeId;
	}

	/**
	 * @param userTypeId the userTypeId to set
	 */
	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	/**
	 * @return the userTypeDesc
	 */
	public String getUserTypeDesc() {
		return userTypeDesc;
	}

	/**
	 * @param userTypeDesc the userTypeDesc to set
	 */
	public void setUserTypeDesc(String userTypeDesc) {
		this.userTypeDesc = userTypeDesc;
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
		result = prime * result + ((updatedTs == null) ? 0 : updatedTs.hashCode());
		result = prime * result + ((userTypeDesc == null) ? 0 : userTypeDesc.hashCode());
		result = prime * result + userTypeId;
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
		UserType other = (UserType) obj;
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
		if (userTypeDesc == null) {
			if (other.userTypeDesc != null)
				return false;
		} else if (!userTypeDesc.equals(other.userTypeDesc))
			return false;
		if (userTypeId != other.userTypeId)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserType [userTypeId=" + userTypeId + ", userTypeDesc=" + userTypeDesc + ", createdTs=" + createdTs
				+ ", updatedTs=" + updatedTs + "]";
	}
	
}
