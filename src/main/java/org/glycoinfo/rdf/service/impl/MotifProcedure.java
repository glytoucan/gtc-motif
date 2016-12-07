package org.glycoinfo.rdf.service.impl;

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
	SelectMotif selectMotif;

	@Autowired
	InsertMotif insertMotif;

/*
	@Autowired
	DeleteMotif deleteMotif;
	
	@Autowired
	ReplaceMotif replaceMotif;
	
	@Autowired
	ReplaceMotifName replaceMotifName;
	
	@Autowired
	ReplaceReducingEnd replaceReducingEnd;
*/
	
	
	// Replace Motif
	// Replace Motif Name
	// Replace Reducing End
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
		return motifName;
	}

	// Remove One Motif
	// Search One Motif
	@Transactional
	public String searchOneMotif(String accessionNumber) throws MotifException {
		if (StringUtils.isNotBlank(accessionNumber)) {
			SparqlEntity sparqlEntity = new SparqlEntity();
			sparqlEntity.setValue(Motif.AccessionNumber, accessionNumber);
			selectMotif.setSparqlEntity(sparqlEntity);
			try {
				sparqlDAO.query(selectMotif);
			} catch (SparqlException e) {
				throw new MotifException(e);
			}
		} else {
			logger.info("accession number is null");
			throw new MotifException("accession number cannot be null.");
		}
		return accessionNumber;
	}
}
