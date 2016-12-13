package org.glycoinfo.rdf.service.impl;

import org.glycoinfo.rdf.SparqlException;
import org.glycoinfo.rdf.motif.DeleteMotif;
import org.glycoinfo.rdf.motif.InsertMotif;
import org.glycoinfo.rdf.motif.ReplaceMotif;
import org.glycoinfo.rdf.motif.ReplaceMotifName;
import org.glycoinfo.rdf.motif.ReplaceReducingEnd;
import org.glycoinfo.rdf.motif.SelectMotif;
import org.openrdf.query.algebra.evaluation.function.string.Replace;
import org.springframework.context.annotation.Bean;

public class MotifProcedureConfig implements GraphConfig {

	@Bean(name = "MotifProcedure")
	MotifProcedure getMotifProcedure() throws SparqlException {
		MotifProcedure motifProcedure = new MotifProcedure();
		return motifProcedure;
	}

	// Replace
	@Bean
	ReplaceMotif getReplaceMotif() {
		ReplaceMotif replaceMotif = new ReplaceMotif();
		replaceMotif.setFromGraph(graph + "/motif");
		replaceMotif.setToGraph(graph + "/motif");
		replaceMotif.setUsing(graph + "/motif");
//		replaceMotif.setFromGraph(graph + "/motif/test");
//		replaceMotif.setToGraph(graph + "/motif/test");
//		replaceMotif.setUsing(graph + "/motif/test");
		return replaceMotif;
	}
	
	// Replace Motif Name
	@Bean
	ReplaceMotifName getReplaceMotifName() {
		ReplaceMotifName replaceMotifName = new ReplaceMotifName();
		replaceMotifName.setFromGraph(graph + "/motif");
		replaceMotifName.setToGraph(graph + "/motif");
		replaceMotifName.setUsing(graph + "/motif");
//		replaceMotifName.setFromGraph(graph + "/motif/test");
//		replaceMotifName.setToGraph(graph + "/motif/test");
//		replaceMotifName.setUsing(graph + "/motif/test");
		return replaceMotifName;
	}

	// Replace Reducing End
	@Bean
	ReplaceReducingEnd getReplaceReduci() {
		ReplaceReducingEnd replaceReducingEnd = new ReplaceReducingEnd();
		replaceReducingEnd.setFromGraph(graph + "/motif");
		replaceReducingEnd.setToGraph(graph + "/motif");
		replaceReducingEnd.setUsing(graph + "/motif");
//		replaceReducingEnd.setFromGraph(graph + "/motif/test");
//		replaceReducingEnd.setToGraph(graph + "/motif/test");
//		replaceReducingEnd.setUsing(graph + "/motif/test");
		return replaceReducingEnd;
	}
	
	// Insert
	@Bean
	InsertMotif getInsertMotif() {
		InsertMotif insertMotif = new InsertMotif();
		insertMotif.setGraph(graph + "/motif");
//		insertMotif.setGraph(graph + "/motif/test");
		return insertMotif;
	}

	// Delete
	@Bean
	DeleteMotif getDeleteMotif() {
		DeleteMotif deleteMotif = new DeleteMotif();
		deleteMotif.setGraph(graph + "/motif");
//		deleteMotif.setGraph(graph + "/motif/test");
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
