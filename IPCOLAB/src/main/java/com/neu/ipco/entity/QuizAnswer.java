/**
 * 
 */
package com.neu.ipco.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author harsh
 *
 */
public class QuizAnswer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8296187346792096377L;
	
	private int quizAnswerId;
	
	private QuizOption quizOption;
	
	private List<Option> userAnswers = new ArrayList<Option>();
	
	private Status status;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public QuizAnswer() {
	}

	/**
	 * @return the quizAnswerId
	 */
	public int getQuizAnswerId() {
		return quizAnswerId;
	}

	/**
	 * @param quizAnswerId the quizAnswerId to set
	 */
	public void setQuizAnswerId(int quizAnswerId) {
		this.quizAnswerId = quizAnswerId;
	}

	/**
	 * @return the quizOption
	 */
	public QuizOption getQuizOption() {
		return quizOption;
	}

	/**
	 * @param quizOption the quizOption to set
	 */
	public void setQuizOption(QuizOption quizOption) {
		this.quizOption = quizOption;
	}

	/**
	 * @return the userAnswers
	 */
	public List<Option> getUserAnswers() {
		return userAnswers;
	}

	/**
	 * @param userAnswers the userAnswers to set
	 */
	public void setUserAnswers(List<Option> userAnswers) {
		this.userAnswers = userAnswers;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
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
		result = prime * result + quizAnswerId;
		result = prime * result + ((quizOption == null) ? 0 : quizOption.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userAnswers == null) ? 0 : userAnswers.hashCode());
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
		QuizAnswer other = (QuizAnswer) obj;
		if (quizAnswerId != other.quizAnswerId)
			return false;
		if (quizOption == null) {
			if (other.quizOption != null)
				return false;
		} else if (!quizOption.equals(other.quizOption))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userAnswers == null) {
			if (other.userAnswers != null)
				return false;
		} else if (!userAnswers.equals(other.userAnswers))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuizAnswer [quizAnswerId=" + quizAnswerId + ", quizOption=" + quizOption + ", userAnswers="
				+ userAnswers + ", status=" + status + "]";
	}
	
}
