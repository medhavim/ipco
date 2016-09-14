/**
 * 
 */
package com.neu.ipco.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.neu.ipco.constants.AppConstants;

/**
 * @author Harsha
 *
 */
public class Topic implements Serializable, Comparable<Topic> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6103128993812139770L;
	
	private Integer topicId;
	
	private String topicName;
	
	private String topicDesc;
	
	private Set<Module> modules = new TreeSet<Module>(AppConstants.MODULE_COMPARATOR);
	
	private InstanceTopic instanceTopic;
	
	private TopicType topicType;
	
	private int orderNo;
	
	private Set<Diagnostic> diagnosticQuestions = new TreeSet<Diagnostic>();
	
	private Set<RelatedDiagnostic> relatedDiagnostics = new TreeSet<RelatedDiagnostic>();
	
	private Set<Quiz> quizQuestions = new TreeSet<Quiz>();
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public Topic() {
	}
	
	public Topic(String topicName, String topicDesc, Integer topicTypeId) {
		this.topicName = topicName;
		this.topicDesc = topicDesc;
		this.topicType = new TopicType(topicTypeId);
		this.createdTs = new Date();
	}

	public void reorder(){
		List<Module> tempModuleList = new ArrayList<Module>(this.modules);
		Collections.sort(tempModuleList, AppConstants.MODULE_COMPARATOR);
		this.modules = new TreeSet<Module>(tempModuleList);
	}
	/**
	 * @return the topicId
	 */
	public Integer getTopicId() {
		return topicId;
	}

	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	/**
	 * @return the topicName
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * @param topicName the topicName to set
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	/**
	 * @return the topicDesc
	 */
	public String getTopicDesc() {
		return topicDesc;
	}

	/**
	 * @param topicDesc the topicDesc to set
	 */
	public void setTopicDesc(String topicDesc) {
		this.topicDesc = topicDesc;
	}

	/**
	 * @return the modules
	 */
	public Set<Module> getModules() {
		return modules;
	}

	/**
	 * @param modules the modules to set
	 */
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	/**
	 * @return the instanceTopic
	 */
	public InstanceTopic getInstanceTopic() {
		return instanceTopic;
	}

	/**
	 * @param instanceTopic the instanceTopic to set
	 */
	public void setInstanceTopic(InstanceTopic instanceTopic) {
		this.instanceTopic = instanceTopic;
	}

	/**
	 * @return the topicType
	 */
	public TopicType getTopicType() {
		return topicType;
	}

	/**
	 * @param topicType the topicType to set
	 */
	public void setTopicType(TopicType topicType) {
		this.topicType = topicType;
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
	 * @return the diagnosticQuestions
	 */
	public Set<Diagnostic> getDiagnosticQuestions() {
		return diagnosticQuestions;
	}

	/**
	 * @param diagnosticQuestions the diagnosticQuestions to set
	 */
	public void setDiagnosticQuestions(Set<Diagnostic> diagnosticQuestions) {
		this.diagnosticQuestions = diagnosticQuestions;
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
	 * @return the quizQuestions
	 */
	public Set<Quiz> getQuizQuestions() {
		return quizQuestions;
	}

	/**
	 * @param quizQuestions the quizQuestions to set
	 */
	public void setQuizQuestions(Set<Quiz> quizQuestions) {
		this.quizQuestions = quizQuestions;
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
		result = prime * result + orderNo;
		result = prime * result + ((topicDesc == null) ? 0 : topicDesc.hashCode());
		result = prime * result + ((topicId == null) ? 0 : topicId.hashCode());
		result = prime * result + ((topicName == null) ? 0 : topicName.hashCode());
		result = prime * result + ((topicType == null) ? 0 : topicType.hashCode());
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
		Topic other = (Topic) obj;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (orderNo != other.orderNo)
			return false;
		if (topicDesc == null) {
			if (other.topicDesc != null)
				return false;
		} else if (!topicDesc.equals(other.topicDesc))
			return false;
		if (topicId == null) {
			if (other.topicId != null)
				return false;
		} else if (!topicId.equals(other.topicId))
			return false;
		if (topicName == null) {
			if (other.topicName != null)
				return false;
		} else if (!topicName.equals(other.topicName))
			return false;
		if (topicType == null) {
			if (other.topicType != null)
				return false;
		} else if (!topicType.equals(other.topicType))
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
		return "Topic [topicId=" + topicId + ", topicName=" + topicName + ", topicDesc=" + topicDesc + ", topicType="
				+ topicType + ", orderNo=" + orderNo + ", createdTs=" + createdTs + ", updatedTs=" + updatedTs + "]";
	}

	public int compareTo(Topic topic) {
		return this.orderNo - topic.orderNo;
	}

}
