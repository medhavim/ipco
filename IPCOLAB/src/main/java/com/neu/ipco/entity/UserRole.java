/**
 * 
 */
package com.neu.ipco.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author harsh
 *
 */
public class UserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1071783027683374901L;
	
	private Integer userRoleId;
	
	private String userRoleDesc;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public UserRole() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the userRoleId
	 */
	public Integer getUserRoleId() {
		return userRoleId;
	}

	/**
	 * @param userRoleId the userRoleId to set
	 */
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	/**
	 * @return the userRoleDesc
	 */
	public String getUserRoleDesc() {
		return userRoleDesc;
	}

	/**
	 * @param userRoleDesc the userRoleDesc to set
	 */
	public void setUserRoleDesc(String userRoleDesc) {
		this.userRoleDesc = userRoleDesc;
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
		result = prime * result + ((userRoleDesc == null) ? 0 : userRoleDesc.hashCode());
		result = prime * result + ((userRoleId == null) ? 0 : userRoleId.hashCode());
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
		UserRole other = (UserRole) obj;
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
		if (userRoleDesc == null) {
			if (other.userRoleDesc != null)
				return false;
		} else if (!userRoleDesc.equals(other.userRoleDesc))
			return false;
		if (userRoleId == null) {
			if (other.userRoleId != null)
				return false;
		} else if (!userRoleId.equals(other.userRoleId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRole [userRoleId=" + userRoleId + ", userRoleDesc=" + userRoleDesc + ", createdTs=" + createdTs
				+ ", updatedTs=" + updatedTs + "]";
	}
	
}
