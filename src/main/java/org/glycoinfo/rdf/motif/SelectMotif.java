package org.glycoinfo.rdf.motif;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glycoinfo.rdf.SelectSparqlBean;
import org.glycoinfo.rdf.SparqlException;
import org.springframework.stereotype.Component;

/*
 * Select SPARQL
 * Input: Accession number
 * Output: Motif name, Reducing end, Glycosequence URI
 * 
 * @author shinmachi
 */
@Component
public class SelectMotif extends SelectSparqlBean implements Motif {

	public SelectMotif(String sparql) {
		super(sparql);
	}

	public SelectMotif() {
		super();
		this.prefix = "\n\n"
				+ "PREFIX rdf:	<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" 
				+ "PREFIX xsd:	<http://www.w3.org/2001/XMLSchema#>\n"
				+ "PREFIX glycan:	<http://purl.jp/bio/12/glyco/glycan#>\n"
				+ "PREFIX glytoucan: <http://www.glytoucan.org/glyco/owl/glytoucan#>\n\n";
		this.select = "DISTINCT ?GlycanMotif ?MotifName ?Glycosequence ?ReducingEnd \n";
	}
	
	@Override
	public String getWhere() throws SparqlException {
		String where = "\n"
//				+ "<http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(AccessionNumber) +"> \n"
//				+ " a glycan:glycan_motif; \n"
				+ " VALUES ?GlycanMotif { <http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(AccessionNumber) + "> }  \n"
				+ " ?GlycanMotif a glycan:glycan_motif; \n"
				+ " rdfs:label " + "?MotifName; \n"
				+ " glycan:has_glycosequence " + "?Glycosequence; \n"
				+ " glytoucan:is_reducing_end " + "?ReducingEnd. \n";
		return where;
	}
	protected Log Logger = LogFactory.getLog(getClass());
}
