/**
 * 
 */
package com.neu.ipco.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author harsh
 *
 */
public class RelatedDiagnostic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4403795726899421956L;
	
	private Integer relatedDiagnosticId;
	
	private String relatedDiagnosticTitle;
	
	private Set<Topic> topics = new TreeSet<Topic>();
	
	private Set<Diagnostic> diagnostics = new TreeSet<Diagnostic>();
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public RelatedDiagnostic() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the relatedDiagnosticId
	 */
	public Integer getRelatedDiagnosticId() {
		return relatedDiagnosticId;
	}

	/**
	 * @param relatedDiagnosticId the relatedDiagnosticId to set
	 */
	public void setRelatedDiagnosticId(Integer relatedDiagnosticId) {
		this.relatedDiagnosticId = relatedDiagnosticId;
	}

	/**
	 * @return the relatedDiagnosticTitle
	 */
	public String getRelatedDiagnosticTitle() {
		return relatedDiagnosticTitle;
	}

	/**
	 * @param relatedDiagnosticTitle the relatedDiagnosticTitle to set
	 */
	public void setRelatedDiagnosticTitle(String relatedDiagnosticTitle) {
		this.relatedDiagnosticTitle = relatedDiagnosticTitle;
	}

	/**
	 * @return the topics
	 */
	public Set<Topic> getTopics() {
		return topics;
	}

	/**
	 * @param topics the topics to set
	 */
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	/**
	 * @return the diagnostics
	 */
	public Set<Diagnostic> getDiagnostics() {
		return diagnostics;
	}

	/**
	 * @param diagnostics the diagnostics to set
	 */
	public void setDiagnostics(Set<Diagnostic> diagnostics) {
		this.diagnostics = diagnostics;
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
		result = prime * result + ((relatedDiagnosticId == null) ? 0 : relatedDiagnosticId.hashCode());
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
		RelatedDiagnostic other = (RelatedDiagnostic) obj;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (relatedDiagnosticId == null) {
			if (other.relatedDiagnosticId != null)
				return false;
		} else if (!relatedDiagnosticId.equals(other.relatedDiagnosticId))
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
		return "RelatedDiagnostic [relatedDiagnosticId=" + relatedDiagnosticId + ", createdTs=" + createdTs
				+ ", updatedTs=" + updatedTs + "]";
	}

	
}
