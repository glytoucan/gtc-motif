package org.glycoinfo.rdf.motif;

import org.apache.commons.lang.StringUtils;
import org.glycoinfo.rdf.DeleteInsertSparqlBean;

/*
PREFIX glycan: <http://purl.jp/bio/12/glyco/glycan#> 
PREFIX glytoucan:  <http://www.glytoucan.org/glyco/owl/glytoucan#>

DELETE
{ GRAPH <http://rdf.glytoucan.org/motif> {
    ?Saccharide glycan:has_motif <http://rdf.glycoinfo.org/glycan/[Accession number]> .
    <http://rdf.glycoinfo.org/glycan/[Accession number]>
		        a glycan:glycan_motif ;
		        glycan:has_glycosequence ?GSequence ;
		        rdfs:label ?MotifName ;
		        glytoucan:is_reducing_end ?ReducingEnd .
  }
}
INSERT
{ GRAPH <http://rdf.glytoucan.org/motif> {
    <http://rdf.glycoinfo.org/glycan/[Accession number]>
		        a glycan:glycan_motif ;
		        rdfs:label ?MotifName ;
		        glycan:has_glycosequence ?GSequence ;
		        glytoucan:is_reducing_end ?ReducingEnd .
  }
}
USING <http://rdf.glytoucan.org/motif> {
WHERE {
    ?Saccharide glycan:has_motif <http://rdf.glycoinfo.org/glycan/[Accession number]> .
    <http://rdf.glycoinfo.org/glycan/[Accession number]>
		        a glycan:glycan_motif ;
		        glycan:has_glycosequence ?GSequence ;
		        rdfs:label ?MotifName ;
		        glytoucan:is_reducing_end ?ReducingEnd .
};
	@author shinmachi
 */ 
public class ReplaceMotif extends DeleteInsertSparqlBean implements Motif {

	void init(){
		this.prefix = "\n\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" 
				+ "PREFIX glycan: <http://purl.jp/bio/12/glyco/glycan#>\n" 
				+ "PREFIX glytoucan:  <http://www.glytoucan.org/glyco/owl/glytoucan#>\n";
	}

	public ReplaceMotif() {
		init();
	}
	

/*
 * delete sample
DELETE
{ GRAPH <http://rdf.glytoucan.org/motif> {
    ?Saccharide glycan:has_motif <http://rdf.glycoinfo.org/glycan/[Accession number]> .
    <http://rdf.glycoinfo.org/glycan/[Accession number]>
		        a glycan:glycan_motif ;
		        rdfs:label ?MotifName ;
		        glycan:has_glycosequence ?GSequence ;
		        glytoucan:is_reducing_end ?ReducingEnd .
  }
}
*/
	public String getDelete() {
		if (StringUtils.isNotBlank(getSparqlEntity().getValue(DeleteAccessionNumber))) {
			this.delete = "?Saccharide glycan:has_motif  <http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(DeleteAccessionNumber) + ">. \n"
					+ "<http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(DeleteAccessionNumber) + "> \n" 
					+ " a glycan:glycan_motif ; \n"
					+ " rdfs:label ?MotifName ; \n"
					+ " glycan:has_glycosequence ?GSequence ; \n"
					+ " glytoucan:is_reducing_end ?ReducingEnd .";
		}
		return delete;		
	}
	
	
/*
INSERT
{ GRAPH <http://rdf.glytoucan.org/motif> {
    <http://rdf.glycoinfo.org/glycan/[Accession number]>
		        a glycan:glycan_motif ;
		        rdfs:label ?MotifName ;
		        glycan:has_glycosequence ?GSequence ;
		        glytoucan:is_reducing_end ?ReducingEnd .
  }
}
 */
	public String getInsert() {
		if (StringUtils.isNotBlank(getSparqlEntity().getValue(InsertAccessionNumber))) {
			this.insert = "<http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(InsertAccessionNumber) + "> \n" 
					+ " a glycan:glycan_motif ; \n"
					+ " rdfs:label ?MotifName ; \n"
					+ " glycan:has_glycosequence ?GSequence ; \n"
					+ " glytoucan:is_reducing_end ?ReducingEnd .";
		}
		return insert;		
	}
	
	
/*
WHERE {
    ?Saccharide glycan:has_motif <http://rdf.glycoinfo.org/glycan/[Accession number]> .
    <http://rdf.glycoinfo.org/glycan/[Accession number]>
		        a glycan:glycan_motif ;
		        glycan:has_glycosequence ?GSequence ;
		        rdfs:label ?MotifName ;
		        glytoucan:is_reducing_end ?ReducingEnd .
}
 */
	public String getWhere() {
		if (StringUtils.isNotBlank(getSparqlEntity().getValue(DeleteAccessionNumber))) {
			this.where = "?Saccharide glycan:has_motif  <http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(DeleteAccessionNumber) + ">. \n"
					+ "<http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(DeleteAccessionNumber) + "> \n" 
					+ " a glycan:glycan_motif ; \n"
					+ " rdfs:label ?MotifName ; \n"
					+ " glycan:has_glycosequence ?GSequence ; \n"
					+ " glytoucan:is_reducing_end ?ReducingEnd .";
		}
		return where;		
		
	}
}
