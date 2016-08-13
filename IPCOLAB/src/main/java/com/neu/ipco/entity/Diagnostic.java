/**
 * 
 */
package com.neu.ipco.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.neu.ipco.constants.AppConstants;

/**
 * @author Harsha
 *
 */
public class Diagnostic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 659192540934768360L;
	
	private Integer diagnosticId;
	
	private ActivityOption activityOption;
	
	private Set<Topic> topics = new TreeSet<Topic>(AppConstants.TOPIC_COMPARATOR);
	
	private Set<RelatedDiagnostic> relatedDiagnostics = new TreeSet<RelatedDiagnostic>();
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public Diagnostic() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the diagnosticId
	 */
	public Integer getDiagnosticId() {
		return diagnosticId;
	}

	/**
	 * @param diagnosticId the diagnosticId to set
	 */
	public void setDiagnosticId(Integer diagnosticId) {
		this.diagnosticId = diagnosticId;
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
	 * @return the relatedDiagnostics
	 */
	public Set<RelatedDiagnostic> getRelatedDiagnostics() {
		return relatedDiagnostics;
	}

	/**
	 * @param relatedDiagnostics the relatedDiagnostics to set
	 */
	public void setRelatedDiagnostics(Set<RelatedDiagnostic> relatedDiagnostics) {
		this.relatedDiagnostics = relatedDiagnostics;
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
		result = prime * result + ((activityOption == null) ? 0 : activityOption.hashCode());
		result = prime * result + ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((diagnosticId == null) ? 0 : diagnosticId.hashCode());
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
		Diagnostic other = (Diagnostic) obj;
		if (activityOption == null) {
			if (other.activityOption != null)
				return false;
		} else if (!activityOption.equals(other.activityOption))
			return false;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (diagnosticId == null) {
			if (other.diagnosticId != null)
				return false;
		} else if (!diagnosticId.equals(other.diagnosticId))
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
		return "Diagnostic [diagnosticId=" + diagnosticId + ", activityOption=" + activityOption + ", createdTs="
				+ createdTs + ", updatedTs=" + updatedTs + "]";
	}

}
