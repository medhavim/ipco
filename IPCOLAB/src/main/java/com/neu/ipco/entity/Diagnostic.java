/**
 * 
 */
package com.neu.ipco.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import com.neu.ipco.constants.AppConstants;

/**
 * @author Harsha
 *
 */
public class Diagnostic implements Serializable, Comparable<Diagnostic> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 659192540934768360L;
	
	private Integer diagnosticId;

	private Activity activity = new Activity();
	
	private Set<Option> options = new TreeSet<Option>(AppConstants.OPTION_COMPARATOR);
	
	private Set<Topic> topics = new TreeSet<Topic>(AppConstants.TOPIC_COMPARATOR);
	
	private Set<RelatedDiagnostic> relatedDiagnostics = new TreeSet<RelatedDiagnostic>();
	
	private DiagnosticCategory category;
	
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
	 * @return the activity
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * @return the options
	 */
	public Set<Option> getOptions() {
		return options;
	}

	/**
	 * @param options the options to set
	 */
	public void setOptions(Set<Option> options) {
		this.options = options;
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
	 * @return the category
	 */
	public DiagnosticCategory getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(DiagnosticCategory category) {
		this.category = category;
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
		result = prime * result + ((activity == null) ? 0 : activity.hashCode());
		result = prime * result + ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((diagnosticId == null) ? 0 : diagnosticId.hashCode());
		result = prime * result + ((options == null) ? 0 : options.hashCode());
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
		if (activity == null) {
			if (other.activity != null)
				return false;
		} else if (!activity.equals(other.activity))
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
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
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
		return "Diagnostic [diagnosticId=" + diagnosticId + ", activity=" + activity + ", options=" + options
				+ ", createdTs=" + createdTs + ", updatedTs=" + updatedTs + "]";
	}

	public int compareTo(Diagnostic d) {
		return this.diagnosticId.compareTo(d.getDiagnosticId());
	}

}
