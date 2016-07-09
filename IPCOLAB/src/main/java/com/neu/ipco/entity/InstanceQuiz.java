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
public class InstanceQuiz implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7543173781843855129L;
	
	private int instanceQuizId;
	
	private List<ActivityAnswer> activityAnswers;
	
	private int score;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public InstanceQuiz() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the instanceQuizId
	 */
	public int getInstanceQuizId() {
		return instanceQuizId;
	}

	/**
	 * @param instanceQuizId the instanceQuizId to set
	 */
	public void setInstanceQuizId(int instanceQuizId) {
		this.instanceQuizId = instanceQuizId;
	}

	/**
	 * @return the activityAnswers
	 */
	public List<ActivityAnswer> getActivityAnswers() {
		return activityAnswers;
	}

	/**
	 * @param activityAnswers the activityAnswers to set
	 */
	public void setActivityAnswers(List<ActivityAnswer> activityAnswers) {
		this.activityAnswers = activityAnswers;
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
		result = prime * result + ((activityAnswers == null) ? 0 : activityAnswers.hashCode());
		result = prime * result + ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + instanceQuizId;
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
		if (activityAnswers == null) {
			if (other.activityAnswers != null)
				return false;
		} else if (!activityAnswers.equals(other.activityAnswers))
			return false;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (instanceQuizId != other.instanceQuizId)
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
		return "InstanceQuiz [instanceQuizId=" + instanceQuizId + ", activityAnswers=" + activityAnswers + ", score="
				+ score + ", createdTs=" + createdTs + ", updatedTs=" + updatedTs + "]";
	}

}
