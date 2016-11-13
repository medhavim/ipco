/**
 * 
 */
package com.neu.ipco.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.ipco.dao.AdminDao;
import com.neu.ipco.entity.ActivityOption;
import com.neu.ipco.entity.Diagnostic;
import com.neu.ipco.entity.DiagnosticCategory;
import com.neu.ipco.entity.RelatedDiagnostic;
import com.neu.ipco.entity.Topic;
import com.neu.ipco.exception.AdminException;
import com.neu.ipco.service.AdminDiagnosticService;

/**
 * @author harsh
 *
 */
@Service("adminDiagnosticService")
public class AdminDiagnosticServiceImpl implements AdminDiagnosticService {
	
private Logger LOGGER = Logger.getLogger(AdminDiagnosticServiceImpl.class);
	
	@Autowired
	private AdminDao adminDao;

	public List<DiagnosticCategory> loadAllCategories() throws AdminException {
		LOGGER.debug("AdminDiagnosticServiceImpl: loadAllCategories: Executing");
		try {
			return adminDao.loadAllCategories();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AdminException(e);
		}
	}

	public DiagnosticCategory addNewCategory(DiagnosticCategory newDiagnosticCategory) throws AdminException {
		LOGGER.debug("AdminDiagnosticServiceImpl: addNewCategory: Executing");
		try {
			newDiagnosticCategory.setOrderNo(adminDao.getCategoryNextOrderNo());
			return adminDao.addNewCategory(newDiagnosticCategory);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public DiagnosticCategory getDiagnosticCategoryById(int categoryId) throws AdminException {
		LOGGER.debug("AdminDiagnosticServiceImpl: getDiagnosticCategoryById: Executing");
		try {
			return adminDao.getDiagnosticCategoryById(categoryId);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public void deleteCategory(int diagnosticCategoryId) throws AdminException {
		LOGGER.debug("AdminDiagnosticServiceImpl: deleteCategory: Executing");
		try {
			DiagnosticCategory diagnosticCategory = adminDao.getDiagnosticCategoryById(diagnosticCategoryId);
			for(Diagnostic diagnostic : diagnosticCategory.getDiagnosticQuestions()){
				deleteDiagnosticById(diagnostic.getDiagnosticId());
			}
			diagnosticCategory = adminDao.getDiagnosticCategoryById(diagnosticCategoryId);
			adminDao.deleteCategory(diagnosticCategory);
//			reorderTopics(topic.getTopicType().getTypeId());
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public void updateCategory(DiagnosticCategory diagnosticCategory) throws AdminException {
		LOGGER.debug("AdminDiagnosticServiceImpl: updateCategory: Executing");
		try {
			adminDao.updateCategory(diagnosticCategory);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public List<Topic> loadCustomTopics() throws AdminException {
		LOGGER.debug("AdminDiagnosticServiceImpl: loadCustomTopics: Executing");
		try {
			return adminDao.loadCustomTopics();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AdminException(e);
		}
	}

	public Diagnostic addNewDiagnostic(Diagnostic diagnostic) throws AdminException {
		LOGGER.debug("AdminDiagnosticServiceImpl: addNewDiagnostic: Executing");
		try {
			return adminDao.addNewDiagnostic(diagnostic);
			
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public Diagnostic getDiagnosticById(int diagnosticId) throws AdminException {
		LOGGER.debug("AdminDiagnosticServiceImpl: getDiagnosticById: Executing");
		try {
			return adminDao.getDiagnosticById(diagnosticId);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public List<Diagnostic> loadAllDiagnostics() throws AdminException {
		LOGGER.debug("AdminDiagnosticServiceImpl: loadAllDiagnostics: Executing");
		try {
			return adminDao.loadAllDiagnostics();
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public RelatedDiagnostic addRelatedDiagnostic(RelatedDiagnostic relatedDiagnostic) throws AdminException {
		LOGGER.debug("AdminDiagnosticServiceImpl: addRelatedDiagnostic: Executing");
		try {
			return adminDao.addRelatedDiagnostic(relatedDiagnostic);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public List<RelatedDiagnostic> loadAllRelatedDiagnostics() throws AdminException {
		LOGGER.debug("AdminDiagnosticServiceImpl: loadAllRelatedDiagnostics: Executing");
		try {
			return adminDao.loadAllRelatedDiagnostics();
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public RelatedDiagnostic getRelatedDiagnosticById(int relatedDiagnosticId) throws AdminException {
		LOGGER.debug("AdminDiagnosticServiceImpl: getRelatedDiagnosticById: Executing");
		try {
			return adminDao.getRelatedDiagnosticById(relatedDiagnosticId);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public RelatedDiagnostic saveOrUpdateRelatedDiagnostic(RelatedDiagnostic relatedDiagnostic) throws AdminException {
		LOGGER.debug("AdminDiagnosticServiceImpl: saveOrUpdateRelatedDiagnostic: Executing");
		try {
			return adminDao.saveOrUpdateRelatedDiagnostic(relatedDiagnostic);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public void deleteDiagnosticById(int diagnosticId) throws AdminException {
		LOGGER.debug("AdminDiagnosticServiceImpl: deleteDiagnosticById: Executing");
		try {
			Diagnostic diagnostic = adminDao.getDiagnosticById(diagnosticId);
			for(RelatedDiagnostic relatedDiagnostic : diagnostic.getRelatedDiagnostics()){
				deleteDiagnosticFromRelatedDiagnostic(relatedDiagnostic, diagnostic.getDiagnosticId());
			}
			adminDao.deleteDiagnostic(diagnostic);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public void deleteDiagnosticFromRelatedDiagnostic(RelatedDiagnostic relatedDiagnostic, Integer diagnosticId) throws AdminException {

		LOGGER.debug("AdminDiagnosticServiceImpl: deleteDiagnosticById: Executing");
		try {
			if(relatedDiagnostic.getDiagnostics().size() <= 2){
				adminDao.deleteRelatedDiagnostic(relatedDiagnostic);
			}else{
				List<Diagnostic> tempList = new LinkedList<Diagnostic>(relatedDiagnostic.getDiagnostics());
				for(Iterator<Diagnostic> diagnosticIterator = tempList.iterator(); diagnosticIterator.hasNext();){
					Diagnostic diagnostic = diagnosticIterator.next();
					if(diagnostic.getDiagnosticId() == diagnosticId){
						relatedDiagnostic.getDiagnostics().remove(diagnostic);
					}
				}
				adminDao.saveOrUpdateRelatedDiagnostic(relatedDiagnostic);
			}
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public void saveOrUpdateDiagnostic(Diagnostic diagnostic) throws AdminException {
		LOGGER.debug("AdminDiagnosticServiceImpl: saveOrUpdateDiagnostic: Executing");
		try {
			adminDao.saveOrUpdateDiagnostic(diagnostic);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public void removeTopicFromDiagnostic(Topic topic) throws AdminException {
		
		LOGGER.debug("AdminDiagnosticServiceImpl: removeTopicFromDiagnostic: Executing");
		try {
			Set<Diagnostic> diagnostics = new HashSet<Diagnostic>(topic.getDiagnosticQuestions());
			for(Diagnostic diagnostic : diagnostics){
				if(diagnostic.getTopics().size() == 1){
					deleteDiagnosticById(diagnostic.getDiagnosticId());
				}else{
					diagnostic.getTopics().remove(topic);
					saveOrUpdateDiagnostic(diagnostic);
				}
			}
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public void removeTopicFromRelatedDiagnostic(Topic topic)
			throws AdminException {
		
		LOGGER.debug("AdminDiagnosticServiceImpl: removeTopicFromDiagnostic: Executing");
		try {
			Set<RelatedDiagnostic> relatedDiagnostics = new HashSet<RelatedDiagnostic>(topic.getRelatedDiagnostics());
			for(RelatedDiagnostic relatedDiagnostic : relatedDiagnostics){
				if(relatedDiagnostic.getTopics().size() == 1){
					deleteRelatedDiagnosticById(relatedDiagnostic.getRelatedDiagnosticId());
				}else{
					relatedDiagnostic.getTopics().remove(topic);
					saveOrUpdateRelatedDiagnostic(relatedDiagnostic);
				}
			}
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

	public void deleteRelatedDiagnosticById(Integer relatedDiagnosticId) throws AdminException{
		LOGGER.debug("AdminDiagnosticServiceImpl: deleteRelatedDiagnosticById: Executing");
		try {
			RelatedDiagnostic relatedDiagnostic = adminDao.getRelatedDiagnosticById(relatedDiagnosticId);
			adminDao.deleteRelatedDiagnostic(relatedDiagnostic);
		} catch (Exception e) {
			throw new AdminException(e);
		}
	}

}
