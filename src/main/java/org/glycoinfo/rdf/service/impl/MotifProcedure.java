package org.glycoinfo.rdf.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glycoinfo.rdf.SparqlException;
import org.glycoinfo.rdf.dao.SparqlDAO;
import org.glycoinfo.rdf.dao.SparqlEntity;
import org.glycoinfo.rdf.motif.DeleteMotif;
import org.glycoinfo.rdf.motif.InsertMotif;
import org.glycoinfo.rdf.motif.Motif;
import org.glycoinfo.rdf.motif.ReplaceMotif;
import org.glycoinfo.rdf.motif.ReplaceMotifName;
import org.glycoinfo.rdf.motif.ReplaceReducingEnd;
import org.glycoinfo.rdf.motif.SelectMotif;
import org.glycoinfo.rdf.service.exception.MotifException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class MotifProcedure {
	private static final Log logger = LogFactory.getLog(MotifProcedure.class);
	
	@Autowired
	SparqlDAO sparqlDAO;
	
	@Autowired
	ReplaceMotif replaceMotif;

	@Autowired
	ReplaceMotifName replaceMotifName;
	
	@Autowired
	ReplaceReducingEnd replaceReducingEnd;

	@Autowired
	InsertMotif insertMotif;

	@Autowired
	DeleteMotif deleteMotif;

	@Autowired
	SelectMotif selectMotif;

	
	
	// Replace Motif
	@Transactional
	public String replaceMotif(String deleteAccessionNumber, String insertAccessionNumber) throws MotifException {
		if (StringUtils.isNotBlank(deleteAccessionNumber) && StringUtils.isNotBlank(insertAccessionNumber)) {
			SparqlEntity sparqlEntity = new SparqlEntity();
			sparqlEntity.setValue(Motif.DeleteAccessionNumber, deleteAccessionNumber);
			sparqlEntity.setValue(Motif.InsertAccessionNumber, insertAccessionNumber);
			replaceMotif.setSparqlEntity(sparqlEntity);
			try {
				sparqlDAO.execute(replaceMotif);
			} catch (SparqlException e) {
				throw new MotifException(e);
			}
		} else {
			logger.info("some parameters are null");
			throw new MotifException("delete accession number or insert accession number cannot be null.");
		}
		String result = deleteAccessionNumber + " was replaced to " + insertAccessionNumber + ".";
		return result;
	}

	// Replace Motif Name
	@Transactional
	public String replaceMotifName(String accessionNumber, String motifName) throws MotifException {
		if (StringUtils.isNotBlank(accessionNumber) && StringUtils.isNotBlank(motifName)) {
			SparqlEntity sparqlEntity = new SparqlEntity();
			sparqlEntity.setValue(Motif.AccessionNumber, accessionNumber);
			sparqlEntity.setValue(Motif.MotifName, motifName);
			replaceMotifName.setSparqlEntity(sparqlEntity);
			try {
				sparqlDAO.execute(replaceMotifName);
			} catch (SparqlException e) {
				throw new MotifException(e);
			}
		} else {
			logger.info("some parameters are null");
			throw new MotifException("accession number or motif name cannot be null.");
		}
		String result = "motif name was replaced to " + motifName + ".";
		return result;
	}

	// Replace Reducing End
	@Transactional
	public String replaceReducingEnd(String accessionNumber, String reducingEnd) throws MotifException {
		if (StringUtils.isNotBlank(accessionNumber) && StringUtils.isNotBlank(reducingEnd)) {
			SparqlEntity sparqlEntity = new SparqlEntity();
			sparqlEntity.setValue(Motif.AccessionNumber, accessionNumber);
			sparqlEntity.setValue(Motif.ReducingEnd, reducingEnd);
			replaceReducingEnd.setSparqlEntity(sparqlEntity);
			try {
				sparqlDAO.insert(replaceReducingEnd);
			} catch (SparqlException e) {
				throw new MotifException(e);
			}
		} else {
			logger.info("some parameters are null");
			throw new MotifException("accession number or reducing end cannot be null.");
		}
		String result = "reducing end was replaced to " + reducingEnd + ".";
		return result;
	}

	// Add One Motif
	@Transactional
	public String addOneMotif(String accessionNumber, String motifName, String reducingEnd) throws MotifException {
		if (StringUtils.isNotBlank(accessionNumber) && StringUtils.isNotBlank(motifName) && StringUtils.isNotBlank(reducingEnd)) {
			SparqlEntity sparqlEntity = new SparqlEntity();
			sparqlEntity.setValue(Motif.AccessionNumber, accessionNumber);
			sparqlEntity.setValue(Motif.MotifName, motifName);
			sparqlEntity.setValue(Motif.ReducingEnd, reducingEnd);
			insertMotif.setSparqlEntity(sparqlEntity);
			try {
				sparqlDAO.insert(insertMotif);
			} catch (SparqlException e) {
				throw new MotifException(e);
			}
		} else {
			logger.info("some parameters are null");
			throw new MotifException("accession number, motif name or reducing end cannot be null.");
		}
		String result = accessionNumber + " was registerd as " + motifName + ".";
		return result;
	}

	// Remove One Motif
	@Transactional
	public String removeOnMotif(String accessionNumber) throws MotifException {
		if (StringUtils.isNotBlank(accessionNumber)) {
			SparqlEntity sparqlEntity = new SparqlEntity();
			sparqlEntity.setValue(Motif.AccessionNumber, accessionNumber);
			deleteMotif.setSparqlEntity(sparqlEntity);
			try {
				sparqlDAO.execute(deleteMotif);
			} catch (SparqlException e) {
				throw new MotifException(e);
			}
		} else {
			logger.info("accession number is null");
			throw new MotifException("accession number cannot be null.");
		}
		String result = accessionNumber + " was deleted."; 
		return result;
	}

	// Search One Motif
	@Transactional
	public List<SparqlEntity> searchOneMotif(String accessionNumber) throws MotifException {
		if (StringUtils.isNotBlank(accessionNumber)) {
			SparqlEntity sparqlEntity = new SparqlEntity();
			sparqlEntity.setValue(Motif.AccessionNumber, accessionNumber);
			selectMotif.setSparqlEntity(sparqlEntity);
			try {
			return sparqlDAO.query(selectMotif);
			} catch (SparqlException e) {
				throw new MotifException(e);
			}
		} else {
			logger.info("accession number is null");
			throw new MotifException("accession number cannot be null.");
		}
	}
}
