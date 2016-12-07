package org.glycoinfo.rdf.motif;

import org.apache.commons.lang.StringUtils;
import org.glycoinfo.rdf.DeleteSparqlBean;

/*
PREFIX glycan: <http://purl.jp/bio/12/glyco/glycan#> 
PREFIX glytoucan:  <http://www.glytoucan.org/glyco/owl/glytoucan#>

DELETE DATA
{ GRAPH <http://rdf.glytoucan.org/motif>
{
    ?Saccharide glycan:has_motif <http://rdf.glycoinfo.org/glycan/[Accession number]> .
    <http://rdf.glycoinfo.org/glycan/[Accession number]>
		        a glycan:glycan_motif ;
		        glycan:has_glycosequence <http://rdf.glycoinfo.org/glycan/[Accession number]/glycoct> ;
		        rdfs:label "[MotifName]"@en ;
		        glytoucan:is_reducing_end "[ReducingEnd]"@en .
 }
}

	@author shinmachi
 */ 
public class DeleteMotif extends DeleteSparqlBean implements Motif {

	void init(){
		this.prefix = "\n\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" 
				+ "PREFIX glycan: <http://purl.jp/bio/12/glyco/glycan#>\n" 
				+ "PREFIX glytoucan:  <http://www.glytoucan.org/glyco/owl/glytoucan#>\n";
	}
	
	public DeleteMotif() {
		init();
	}

	public String getDelete() {
		if (StringUtils.isNotBlank(getSparqlEntity().getValue(AccessionNumber))) {
			this.delete = "?Saccharide glycan:has_motif  <http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(AccessionNumber) + ">. \n"
					+ "<http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(AccessionNumber) + "> \n" 
					+ " a glycan:glycan_motif ; \n"
					+ " rdfs:label ?MotifName ; \n"
					+ " glycan:has_glycosequence ?GSequence ; \n"
					+ " glytoucan:is_reducing_end ?ReducingEnd .";
		}
		return delete;		
	}

//	public String getDelete() {
//		if (StringUtils.isNotBlank(getSparqlEntity().getValue(AccessionNumber))) {
//			this.delete = "?Saccharide glycan:has_motif  <http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(AccessionNumber) + ">. \n"
//					+ "<http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(AccessionNumber) + "> \n" 
//					+ " a glycan:glycan_motif ; \n"
//					+ " glycan:has_glycosequence <http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(AccessionNumber) + "/glycoct>; \n"
//					+ " rdfs:label \"" + getSparqlEntity().getValue(MotifName) + "\"@en ; \n" 
//					+ " glytoucan:is_reducing_end " + getSparqlEntity().getValue(ReducingEnd) + " . \n";
//		}
//		return delete;		
//	}
	
	@Override
	public String getFormat() {
		return DeleteSparqlBean.DELETEWHERE;
	}
}
