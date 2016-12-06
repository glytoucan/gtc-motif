package org.glycoinfo.rdf.motif;

import org.apache.commons.lang.StringUtils;
import org.glycoinfo.rdf.DeleteInsertSparqlBean;

/*
PREFIX glycan: <http://purl.jp/bio/12/glyco/glycan#> 
PREFIX glytoucan:  <http://www.glytoucan.org/glyco/owl/glytoucan#>

DELETE
{ GRAPH <http://rdf.glytoucan.org/motif> {
    <http://rdf.glycoinfo.org/glycan/[Accession number]>
		        rdfs:label ?MotifName .
  }
}
INSERT
{ GRAPH <http://rdf.glytoucan.org/motif> {
    <http://rdf.glycoinfo.org/glycan/[Accession number]>
		        rdfs:label ?MotifName .
  }
}
USING <http://rdf.glytoucan.org/motif> {
WHERE {
    <http://rdf.glycoinfo.org/glycan/[Accession number]>
		        rdfs:label ?MotifName .
};
	@author shinmachi
 */ 
public class ReplaceMotifName extends DeleteInsertSparqlBean implements Motif {

	void init(){
		this.prefix = "\n\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" 
				+ "PREFIX glycan: <http://purl.jp/bio/12/glyco/glycan#>\n" 
				+ "PREFIX glytoucan:  <http://www.glytoucan.org/glyco/owl/glytoucan#>\n";
	}

	public ReplaceMotifName() {
		init();
	}

	public String getDelete() {
		if (StringUtils.isNotBlank(getSparqlEntity().getValue(AccessionNumber))) {
			this.delete = "<http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(AccessionNumber) + "> \n" 
					+ " rdfs:label ?MotifName . \n";
		}
		return delete;		
	}
	
	public String getInsert() {
		if (StringUtils.isNotBlank(getSparqlEntity().getValue(AccessionNumber))) {
			this.insert = "<http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(AccessionNumber) + "> \n" 
					+ " rdfs:label \"" + getSparqlEntity().getValue(MotifName) + "\"@en . \n";
		}
		return insert;		
	}
	
	public String getWhere() {
		if (StringUtils.isNotBlank(getSparqlEntity().getValue(AccessionNumber))) {
			this.where = "<http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(AccessionNumber) + "> \n" 
					+ " rdfs:label ?MotifName . \n";
		}
		return where;		
		
	}
}


