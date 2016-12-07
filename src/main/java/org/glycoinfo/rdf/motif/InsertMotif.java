package org.glycoinfo.rdf.motif;

import org.apache.commons.lang.StringUtils;
import org.glycoinfo.rdf.InsertSparqlBean;

/*
Insert Accession number which means motif structure by insert SPARQL.

@prefix rdf:	<http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
@prefix rdfs:	<http://www.w3.org/2000/01/rdf-schema#> .
@prefix xsd:	<http://www.w3.org/2001/XMLSchema#> .
@prefix glycan:	<http://purl.jp/bio/12/glyco/glycan#> .
@prefix glytoucan:	<http://www.glytoucan.org/glyco/owl/glytoucan#> .

<http://rdf.glycoinfo.org/glycan/G00053MO>	rdf:type	glycan:glycan_motif .
<http://rdf.glycoinfo.org/glycan/G00053MO>	rdfs:label	"Sialyl Lewis A"@en .
<http://rdf.glycoinfo.org/glycan/G00053MO>	glycan:has_glycosequence	<http://rdf.glycoinfo.org/glycan/G00053MO/glycoct> .
<http://rdf.glycoinfo.org/glycan/G00053MO>	glytoucan:is_reducing_end	"false"^^xsd:boolean .

 @author shinmachis
*/
public class InsertMotif extends InsertSparqlBean implements Motif {

	void init(){
		this.prefix = "\n\n"
				+ "PREFIX rdf:	<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" 
				+ "PREFIX xsd:	<http://www.w3.org/2001/XMLSchema#>\n"
				+ "PREFIX glycan:	<http://purl.jp/bio/12/glyco/glycan#>\n"
				+ "PREFIX glytoucan: <http://www.glytoucan.org/glyco/owl/glytoucan#>\n\n";
	}
        
	public InsertMotif() {
			init();
	}
	
	@Override
	public String getInsert() {
		if (StringUtils.isNotBlank(getSparqlEntity().getValue(AccessionNumber))) {
			this.insert = "<http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(AccessionNumber) + "> \n" 
					+ " a glycan:glycan_motif ; \n"
					+ " glycan:has_glycosequence <http://rdf.glycoinfo.org/glycan/" + getSparqlEntity().getValue(AccessionNumber) + "/glycoct>; \n"
					+ " rdfs:label \"" + getSparqlEntity().getValue(MotifName) + "\"@en ; \n" 
					+ " glytoucan:is_reducing_end " + getSparqlEntity().getValue(ReducingEnd) + " . \n";
		}
		return insert;
	}

        
}
