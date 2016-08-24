/**
 * 
 */
package com.neu.ipco.service.impl;

import java.util.Iterator;
import java.util.List;

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

	public void deleteCategory(DiagnosticCategory diagnosticCategory) throws AdminException {
		LOGGER.debug("AdminDiagnosticServiceImpl: deleteCategory: Executing");
		try {
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

}
