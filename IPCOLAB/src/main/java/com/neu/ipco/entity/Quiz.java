/**
 * 
 */
package com.neu.ipco.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Harsha
 *
 */
public class Quiz implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2102974540493012996L;
	
	private Integer quizId;
	
	private String quizName;
	
	private String quizDesc;
	
	private List<QuizOption> quizOptions = new ArrayList<QuizOption>();
	
	private Set<InstanceQuiz> instanceQuizes;
	
	private Date createdTs;
	
	private Date updatedTs;

	public Quiz() {
	}

	/**
	 * @return the quizId
	 */
	public Integer getQuizId() {
		return quizId;
	}

	/**
	 * @param quizId the quizId to set
	 */
	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}

	/**
	 * @return the quizName
	 */
	public String getQuizName() {
		return quizName;
	}

	/**
	 * @param quizName the quizName to set
	 */
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	/**
	 * @return the quizDesc
	 */
	public String getQuizDesc() {
		return quizDesc;
	}

	/**
	 * @param quizDesc the quizDesc to set
	 */
	public void setQuizDesc(String quizDesc) {
		this.quizDesc = quizDesc;
	}


	/**
	 * @return the quizOptions
	 */
	public List<QuizOption> getQuizOptions() {
		return quizOptions;
	}

	/**
	 * @param quizOptions the quizOptions to set
	 */
	public void setQuizOptions(List<QuizOption> quizOptions) {
		this.quizOptions = quizOptions;
	}

	/**
	 * @return the instanceQuizes
	 */
	public Set<InstanceQuiz> getInstanceQuizes() {
		return instanceQuizes;
	}

	/**
	 * @param instanceQuizes the instanceQuizes to set
	 */
	public void setInstanceQuizes(Set<InstanceQuiz> instanceQuizes) {
		this.instanceQuizes = instanceQuizes;
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
		result = prime * result + ((quizDesc == null) ? 0 : quizDesc.hashCode());
		result = prime * result + ((quizId == null) ? 0 : quizId.hashCode());
		result = prime * result + ((quizName == null) ? 0 : quizName.hashCode());
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
		Quiz other = (Quiz) obj;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (quizDesc == null) {
			if (other.quizDesc != null)
				return false;
		} else if (!quizDesc.equals(other.quizDesc))
			return false;
		if (quizId == null) {
			if (other.quizId != null)
				return false;
		} else if (!quizId.equals(other.quizId))
			return false;
		if (quizName == null) {
			if (other.quizName != null)
				return false;
		} else if (!quizName.equals(other.quizName))
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
		return "Quiz [quizId=" + quizId + ", quizName=" + quizName + ", quizDesc=" + quizDesc + ", createdTs="
				+ createdTs + ", updatedTs=" + updatedTs + "]";
	}

}
