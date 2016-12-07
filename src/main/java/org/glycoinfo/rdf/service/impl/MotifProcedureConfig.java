package org.glycoinfo.rdf.service.impl;

import org.glycoinfo.rdf.SparqlException;
import org.glycoinfo.rdf.motif.DeleteMotif;
import org.glycoinfo.rdf.motif.InsertMotif;
import org.glycoinfo.rdf.motif.SelectMotif;
import org.springframework.context.annotation.Bean;

public class MotifProcedureConfig implements GraphConfig {

	@Bean(name = "MotifProcedure")
	MotifProcedure getMotifProcedure() throws SparqlException {
		MotifProcedure motifProcedure = new MotifProcedure();
		return motifProcedure;
	}

	// Insert
	@Bean
	InsertMotif getInsertMotif() {
		InsertMotif insertMotif = new InsertMotif();
		insertMotif.setGraph(graph + "/motif");
		return insertMotif;
	}

	// Delete
	@Bean
	DeleteMotif getDeleteMotif() {
		DeleteMotif deleteMotif = new DeleteMotif();
		deleteMotif.setGraph(graph + "/motif");
		return deleteMotif;
	}
	
	// Select
	@Bean
	SelectMotif getSelectMotif() {
		SelectMotif selectMotif = new SelectMotif();
		selectMotif.setFrom("FROM <" + graph + "/motif" + ">\n");
		return selectMotif;
	}
}
