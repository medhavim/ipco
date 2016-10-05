/**
 * 
 */
package com.neu.ipco.service;

import java.util.List;

import com.neu.ipco.entity.Diagnostic;
import com.neu.ipco.entity.DiagnosticCategory;
import com.neu.ipco.entity.RelatedDiagnostic;
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

	void deleteCategory(int diagnosticCategoryId) throws AdminException;

	void updateCategory(DiagnosticCategory diagnosticCategory) throws AdminException;

	List<Topic> loadCustomTopics() throws AdminException;

	Diagnostic addNewDiagnostic(Diagnostic diagnostic) throws AdminException;

	Diagnostic getDiagnosticById(int diagnosticId) throws AdminException;

	List<Diagnostic> loadAllDiagnostics() throws AdminException;

	RelatedDiagnostic addRelatedDiagnostic(RelatedDiagnostic relatedDiagnostic) throws AdminException;

	List<RelatedDiagnostic> loadAllRelatedDiagnostics() throws AdminException;

	RelatedDiagnostic getRelatedDiagnosticById(int relatedDiagnosticId) throws AdminException;

	RelatedDiagnostic saveOrUpdateRelatedDiagnostic(RelatedDiagnostic relatedDiagnostic) throws AdminException;
	
	void deleteDiagnosticById(int diagnosticId) throws AdminException;
	
	void deleteDiagnosticFromRelatedDiagnostic(RelatedDiagnostic relatedDiagnostic, Integer diagnosticId) throws AdminException;

}
