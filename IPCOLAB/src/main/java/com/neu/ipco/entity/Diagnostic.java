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
public class Diagnostic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 659192540934768360L;
	
	private int diagnosticId;
	
	private int relatedDiagnosticId;
	
	private ActivityOption question;
	
	private List<Topic> topics;
	
	private List<Diagnostic> relatedQuestions;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public Diagnostic() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the diagnosticId
	 */
	public int getDiagnosticId() {
		return diagnosticId;
	}

	/**
	 * @param diagnosticId the diagnosticId to set
	 */
	public void setDiagnosticId(int diagnosticId) {
		this.diagnosticId = diagnosticId;
	}

	/**
	 * @return the relatedDiagnosticId
	 */
	public int getRelatedDiagnosticId() {
		return relatedDiagnosticId;
	}

	/**
	 * @param relatedDiagnosticId the relatedDiagnosticId to set
	 */
	public void setRelatedDiagnosticId(int relatedDiagnosticId) {
		this.relatedDiagnosticId = relatedDiagnosticId;
	}

	/**
	 * @return the question
	 */
	public ActivityOption getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(ActivityOption question) {
		this.question = question;
	}

	/**
	 * @return the topics
	 */
	public List<Topic> getTopics() {
		return topics;
	}

	/**
	 * @param topics the topics to set
	 */
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	/**
	 * @return the relatedQuestions
	 */
	public List<Diagnostic> getRelatedQuestions() {
		return relatedQuestions;
	}

	/**
	 * @param relatedQuestions the relatedQuestions to set
	 */
	public void setRelatedQuestions(List<Diagnostic> relatedQuestions) {
		this.relatedQuestions = relatedQuestions;
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
		result = prime * result + diagnosticId;
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + relatedDiagnosticId;
		result = prime * result + ((relatedQuestions == null) ? 0 : relatedQuestions.hashCode());
		result = prime * result + ((topics == null) ? 0 : topics.hashCode());
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
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (diagnosticId != other.diagnosticId)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (relatedDiagnosticId != other.relatedDiagnosticId)
			return false;
		if (relatedQuestions == null) {
			if (other.relatedQuestions != null)
				return false;
		} else if (!relatedQuestions.equals(other.relatedQuestions))
			return false;
		if (topics == null) {
			if (other.topics != null)
				return false;
		} else if (!topics.equals(other.topics))
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
		return "Diagnostic [diagnosticId=" + diagnosticId + ", relatedDiagnosticId=" + relatedDiagnosticId
				+ ", question=" + question + ", topics=" + topics + ", relatedQuestions=" + relatedQuestions
				+ ", createdTs=" + createdTs + ", updatedTs=" + updatedTs + "]";
	}

}
