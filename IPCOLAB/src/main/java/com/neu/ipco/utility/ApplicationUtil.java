/**
 * 
 */
package com.neu.ipco.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.neu.ipco.entity.Option;
import com.neu.ipco.entity.User;

/**
 * @author harsh
 *
 */
public class ApplicationUtil {
	
	public static Set<Option> populateDescOptions(Set<Option> options, HttpServletRequest request, int orderNo) {
		
		String idealAnswer = request.getParameter("idealAnswer").trim();
		
		Option option = new Option();
		option.setOptionText(idealAnswer);
		option.setOrderNo(++orderNo);
		option.setIsCorrect("true");
		option.setCreatedTs(new Date());
		options.add(option);
		return options;
	}

	public static Set<Option> populateMCQOptoins(Set<Option>options, HttpServletRequest request, int orderNo) {
		String[] optionArray = request.getParameterValues("correctAnswer");
		String optionText = "option";
		if(null == optionArray){
			optionArray = request.getParameterValues("selectedAnswer");
			optionText = "selectedAnswer_";
		}
		if(null == optionArray){
			return options;
		}
		List<String> correctAnswers = new ArrayList<String>(Arrays.asList(optionArray));
		Enumeration<String> parameters = request.getParameterNames();
		
		while(parameters.hasMoreElements()){
			String param = (String) parameters.nextElement();
			if(param.contains(optionText)){
				Option option = new Option();
				option.setOptionText(request.getParameter(param).trim());
				option.setOrderNo(orderNo + Integer.valueOf(param.split("_")[1]));
				if(null != correctAnswers){
					option.setIsCorrect(correctAnswers.contains(param)?"true":"false");
				}
				option.setCreatedTs(new Date());
				options.add(option);
			}
		}
		return options;
	}
	
	public static Set<Option> populateMCAOptoins(Set<Option>options, HttpServletRequest request, int orderNo) {
		String[] optionArray = request.getParameterValues("correctAnswer");
		String optionText = "option";
		if(null == optionArray){
			optionArray = request.getParameterValues("selectedAnswer");
			optionText = "selectedAnswer_";
		}
		if(null == optionArray){
			return options;
		}
		List<String> correctAnswers = new ArrayList<String>(Arrays.asList(optionArray));
		Enumeration<String> parameters = request.getParameterNames();
		
		while(parameters.hasMoreElements()){
			String param = (String) parameters.nextElement();
			if(param.contains(optionText)){
				Option option = new Option();
				option.setOptionText(request.getParameter(param).trim());
				option.setOrderNo(orderNo + Integer.valueOf(param.split("_")[1]));
				if(null != correctAnswers){
					option.setIsCorrect(correctAnswers.contains(param)?"true":"false");
				}
				option.setCreatedTs(new Date());
				options.add(option);
			}
		}
		return options;
	}
	
	public static Set<Option> populateYESNOOptions(Set<Option> options, HttpServletRequest request, int orderNo) {
		
		String correctOption = request.getParameter("yesno-option");
		
		Option option1 = new Option();
		option1.setOptionText("Yes");
		option1.setOrderNo(++orderNo);
		option1.setIsCorrect(correctOption.equalsIgnoreCase("Yes")?"true":"false");
		option1.setCreatedTs(new Date());
		
		Option option2 = new Option();
		option2.setOptionText("No");
		option2.setOrderNo(++orderNo);
		option2.setIsCorrect(correctOption.equalsIgnoreCase("No")?"true":"false");
		option2.setCreatedTs(new Date());
		
		options.add(option1);
		options.add(option2);
		
		return options;
	}
	
	public static Collection<Option> updateCorrectOptions(Collection<Option> newOptions, Collection<Option> oldOptions) {
		Iterator<Option> oldOptionIterator = oldOptions.iterator();
		while(oldOptionIterator.hasNext()){
			Option oldOption = oldOptionIterator.next();
			Iterator<Option> newOptionIterator = newOptions.iterator();
			while(newOptionIterator.hasNext()){
				Option newOption = newOptionIterator.next();
				if(oldOption.getOptionText().equalsIgnoreCase(newOption.getOptionText())){
					oldOption.setIsCorrect(newOption.getIsCorrect());
					newOptionIterator.remove();
					break;
				}
			}
		}
		return oldOptions;
	}

	public static Comparator<User> getUserSortComparator(String sortBy) {

		switch (sortBy) {
		case AppConstants.SORT_BY_FIRST_NAME:
			return AppConstants.SORT_FIRST_NAME_COMPARATOR;
			
		case AppConstants.SORT_BY_LAST_NAME:
			return AppConstants.SORT_LAST_NAME_COMPARATOR;
			
		case AppConstants.SORT_BY_LAST_LOGIN:
			return AppConstants.SORT_LAST_LOGIN_COMPARATOR;
			
		case AppConstants.SORT_BY_REGISTERED:
			return AppConstants.SORT_REGISTERED_COMPARATOR;

		default:
			return AppConstants.SORT_LAST_LOGIN_COMPARATOR;
		}
	}

}
