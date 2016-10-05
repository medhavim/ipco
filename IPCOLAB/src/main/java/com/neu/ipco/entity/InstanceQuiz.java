/**
 * 
 */
package com.neu.ipco.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Harsha
 *
 */
public class InstanceQuiz implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7543173781843855129L;
	
	private Integer instanceQuizId;
	
	private Quiz quiz;
	
	private List<QuizAnswer> quizAnswers = new ArrayList<QuizAnswer>();
	
	private Status status;
	
	private int score = 0;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public InstanceQuiz() {
	}

	/**
	 * @return the instanceQuizId
	 */
	public Integer getInstanceQuizId() {
		return instanceQuizId;
	}

	/**
	 * @param instanceQuizId the instanceQuizId to set
	 */
	public void setInstanceQuizId(Integer instanceQuizId) {
		this.instanceQuizId = instanceQuizId;
	}

	/**
	 * @return the quiz
	 */
	public Quiz getQuiz() {
		return quiz;
	}

	/**
	 * @param quiz the quiz to set
	 */
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	/**
	 * @return the quizAnswers
	 */
	public List<QuizAnswer> getQuizAnswers() {
		return quizAnswers;
	}

	/**
	 * @param quizAnswers the quizAnswers to set
	 */
	public void setQuizAnswers(List<QuizAnswer> quizAnswers) {
		this.quizAnswers = quizAnswers;
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
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
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
		result = prime * result + ((instanceQuizId == null) ? 0 : instanceQuizId.hashCode());
		result = prime * result + ((quizAnswers == null) ? 0 : quizAnswers.hashCode());
		result = prime * result + score;
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
		InstanceQuiz other = (InstanceQuiz) obj;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (instanceQuizId == null) {
			if (other.instanceQuizId != null)
				return false;
		} else if (!instanceQuizId.equals(other.instanceQuizId))
			return false;
		if (quizAnswers == null) {
			if (other.quizAnswers != null)
				return false;
		} else if (!quizAnswers.equals(other.quizAnswers))
			return false;
		if (score != other.score)
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
		return "InstanceQuiz [instanceQuizId=" + instanceQuizId + ", quizAnswers=" + quizAnswers + ", score=" + score
				+ ", createdTs=" + createdTs + ", updatedTs=" + updatedTs + "]";
	}


}
