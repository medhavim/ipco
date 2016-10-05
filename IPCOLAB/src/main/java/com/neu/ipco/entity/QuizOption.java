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
public class QuizOption implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337538948697071715L;
	
	private int quizOptionId;
	
	private Activity activity;
	
	private List<Option> correctAnswers = new ArrayList<Option>();
	
	private int orderNo;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public QuizOption() {
	}

	/**
	 * @return the quizOptionId
	 */
	public int getQuizOptionId() {
		return quizOptionId;
	}

	/**
	 * @param quizOptionId the quizOptionId to set
	 */
	public void setQuizOptionId(int quizOptionId) {
		this.quizOptionId = quizOptionId;
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
	 * @return the correctAnswers
	 */
	public List<Option> getCorrectAnswers() {
		return correctAnswers;
	}

	/**
	 * @param correctAnswers the correctAnswers to set
	 */
	public void setCorrectAnswers(List<Option> correctAnswers) {
		this.correctAnswers = correctAnswers;
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
		result = prime * result + ((activity == null) ? 0 : activity.hashCode());
		result = prime * result + ((correctAnswers == null) ? 0 : correctAnswers.hashCode());
		result = prime * result + ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + orderNo;
		result = prime * result + quizOptionId;
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
		QuizOption other = (QuizOption) obj;
		if (activity == null) {
			if (other.activity != null)
				return false;
		} else if (!activity.equals(other.activity))
			return false;
		if (correctAnswers == null) {
			if (other.correctAnswers != null)
				return false;
		} else if (!correctAnswers.equals(other.correctAnswers))
			return false;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (orderNo != other.orderNo)
			return false;
		if (quizOptionId != other.quizOptionId)
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
		return "QuizOption [quizOptionId=" + quizOptionId + ", activity=" + activity + ", correctAnswers="
				+ correctAnswers + ", orderNo=" + orderNo + ", createdTs=" + createdTs + ", updatedTs=" + updatedTs
				+ "]";
	}

}
