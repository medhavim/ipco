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
public class InstanceType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7153461243055632603L;
	
	private int instanceTypeId;
	
	private String instanceTypeDesc;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public InstanceType() {
	}

	/**
	 * @return the instanceTypeId
	 */
	public int getInstanceTypeId() {
		return instanceTypeId;
	}

	/**
	 * @param instanceTypeId the instanceTypeId to set
	 */
	public void setInstanceTypeId(int instanceTypeId) {
		this.instanceTypeId = instanceTypeId;
	}

	/**
	 * @return the instanceTypeDesc
	 */
	public String getInstanceTypeDesc() {
		return instanceTypeDesc;
	}

	/**
	 * @param instanceTypeDesc the instanceTypeDesc to set
	 */
	public void setInstanceTypeDesc(String instanceTypeDesc) {
		this.instanceTypeDesc = instanceTypeDesc;
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
		result = prime * result + ((instanceTypeDesc == null) ? 0 : instanceTypeDesc.hashCode());
		result = prime * result + instanceTypeId;
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
		InstanceType other = (InstanceType) obj;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (instanceTypeDesc == null) {
			if (other.instanceTypeDesc != null)
				return false;
		} else if (!instanceTypeDesc.equals(other.instanceTypeDesc))
			return false;
		if (instanceTypeId != other.instanceTypeId)
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
		return "InstanceType [instanceTypeId=" + instanceTypeId + ", instanceTypeDesc=" + instanceTypeDesc
				+ ", createdTs=" + createdTs + ", updatedTs=" + updatedTs + "]";
	}
	
}
