/**
 * 
 */
package com.neu.ipco.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
	
	private Activity activity;
	
	private Set<Option> correctAnswers = new TreeSet<Option>();
	
	private Set<Option> userAnswers = new TreeSet<Option>();
	
	private Status status;
	
	private int orderNo;
	
	private Date createdTs;
	
	private Date updatedTs;

	public Quiz() {
		// TODO Auto-generated constructor stub
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
	public Set<Option> getCorrectAnswers() {
		return correctAnswers;
	}

	/**
	 * @param correctAnswers the correctAnswers to set
	 */
	public void setCorrectAnswers(Set<Option> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	/**
	 * @return the userAnswers
	 */
	public Set<Option> getUserAnswers() {
		return userAnswers;
	}

	/**
	 * @param userAnswers the userAnswers to set
	 */
	public void setUserAnswers(Set<Option> userAnswers) {
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
		result = prime * result + ((quizId == null) ? 0 : quizId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((updatedTs == null) ? 0 : updatedTs.hashCode());
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
		Quiz other = (Quiz) obj;
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
		if (quizId == null) {
			if (other.quizId != null)
				return false;
		} else if (!quizId.equals(other.quizId))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (updatedTs == null) {
			if (other.updatedTs != null)
				return false;
		} else if (!updatedTs.equals(other.updatedTs))
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
		return "Quiz [quizId=" + quizId + ", activity=" + activity + ", correctAnswers=" + correctAnswers
				+ ", userAnswers=" + userAnswers + ", status=" + status + ", orderNo=" + orderNo + ", createdTs="
				+ createdTs + ", updatedTs=" + updatedTs + "]";
	}

}
