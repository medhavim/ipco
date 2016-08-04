/**
 * 
 */
package com.neu.ipco.entity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import com.neu.ipco.constants.AppConstants;

/**
 * @author Harsha
 *
 */
public class ActivityOption implements Serializable, Comparable<ActivityOption> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6995304591188353347L;
	
	private Integer activityOptionId;
	
	private Activity activity = new Activity();
	
	private Module module = new Module();
	
	private Set<Option> options = new TreeSet<Option>(AppConstants.OPTION_COMPARATOR);
	
	private int orderNo;
	
	private Date createdTs;
	
	private Date updatedTs;
	
	public ActivityOption() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the activityOptionId
	 */
	public Integer getActivityOptionId() {
		return activityOptionId;
	}

	/**
	 * @param activityOptionId the activityOptionId to set
	 */
	public void setActivityOptionId(Integer activityOptionId) {
		this.activityOptionId = activityOptionId;
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
	 * @return the module
	 */
	public Module getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(Module module) {
		this.module = module;
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
		result = prime * result + ((activityOptionId == null) ? 0 : activityOptionId.hashCode());
		result = prime * result + ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + orderNo;
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
		ActivityOption other = (ActivityOption) obj;
		if (activity == null) {
			if (other.activity != null)
				return false;
		} else if (!activity.equals(other.activity))
			return false;
		if (activityOptionId == null) {
			if (other.activityOptionId != null)
				return false;
		} else if (!activityOptionId.equals(other.activityOptionId))
			return false;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (orderNo != other.orderNo)
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
		return "ActivityOption [activityOptionId=" + activityOptionId + ", activity=" + activity + ", module=" + module
				+ ", options=" + options + ", orderNo=" + orderNo + ", createdTs=" + createdTs + ", updatedTs="
				+ updatedTs + "]";
	}

	public int compareTo(ActivityOption activityOption) {
		return this.orderNo - activityOption.orderNo;
	}

}
