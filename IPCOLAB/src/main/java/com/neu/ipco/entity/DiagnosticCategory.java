/**
 * 
 */
package com.neu.ipco.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author harsh
 *
 */
public class DiagnosticCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9136953687860594679L;
	
	private Integer categoryId;
	
	private String categoryTitle;
	
	private String categoryDesc;
	
	private List<Diagnostic> diagnosticQuestions;
	
	private int orderNo;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public DiagnosticCategory() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the categoryTitle
	 */
	public String getCategoryTitle() {
		return categoryTitle;
	}

	/**
	 * @param categoryTitle the categoryTitle to set
	 */
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	/**
	 * @return the categoryDesc
	 */
	public String getCategoryDesc() {
		return categoryDesc;
	}

	/**
	 * @param categoryDesc the categoryDesc to set
	 */
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	/**
	 * @return the diagnosticQuestions
	 */
	public List<Diagnostic> getDiagnosticQuestions() {
		return diagnosticQuestions;
	}

	/**
	 * @param diagnosticQuestions the diagnosticQuestions to set
	 */
	public void setDiagnosticQuestions(List<Diagnostic> diagnosticQuestions) {
		this.diagnosticQuestions = diagnosticQuestions;
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
		result = prime * result + ((categoryDesc == null) ? 0 : categoryDesc.hashCode());
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((categoryTitle == null) ? 0 : categoryTitle.hashCode());
		result = prime * result + ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((diagnosticQuestions == null) ? 0 : diagnosticQuestions.hashCode());
		result = prime * result + orderNo;
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
		DiagnosticCategory other = (DiagnosticCategory) obj;
		if (categoryDesc == null) {
			if (other.categoryDesc != null)
				return false;
		} else if (!categoryDesc.equals(other.categoryDesc))
			return false;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (categoryTitle == null) {
			if (other.categoryTitle != null)
				return false;
		} else if (!categoryTitle.equals(other.categoryTitle))
			return false;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (diagnosticQuestions == null) {
			if (other.diagnosticQuestions != null)
				return false;
		} else if (!diagnosticQuestions.equals(other.diagnosticQuestions))
			return false;
		if (orderNo != other.orderNo)
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
		return "DiagnosticCategory [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", categoryDesc="
				+ categoryDesc + ", diagnosticQuestions=" + diagnosticQuestions + ", orderNo=" + orderNo
				+ ", createdTs=" + createdTs + ", updatedTs=" + updatedTs + "]";
	}
	
}
