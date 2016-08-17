/**
 * 
 */
package com.neu.ipco.service;

import java.util.List;

import com.neu.ipco.entity.Diagnostic;
import com.neu.ipco.entity.DiagnosticCategory;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.exception.AdminException;

/**
 * @author harsh
 *
 */
public interface AdminDiagnosticService {

	List<DiagnosticCategory> loadAllCategories() throws AdminException;

	DiagnosticCategory addNewCategory(DiagnosticCategory newDiagnosticCategory) throws AdminException;

	DiagnosticCategory getDiagnosticCategoryById(int categoryId) throws AdminException;

	void deleteCategory(DiagnosticCategory diagnosticCategory) throws AdminException;

	void updateCategory(DiagnosticCategory diagnosticCategory) throws AdminException;

	List<Topic> loadCustomTopics() throws AdminException;

	Diagnostic addNewDiagnostic(Diagnostic diagnostic) throws AdminException;

	Diagnostic getDiagnosticById(int diagnosticId) throws AdminException;

}
