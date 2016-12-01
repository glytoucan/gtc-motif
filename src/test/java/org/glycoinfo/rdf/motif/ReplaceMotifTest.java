package org.glycoinfo.rdf.motif;

import org.glycoinfo.rdf.SparqlException;
import org.glycoinfo.rdf.dao.SparqlDAO;
import org.glycoinfo.rdf.dao.SparqlEntity;
import org.glycoinfo.rdf.dao.virt.VirtSesameTransactionConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { VirtSesameTransactionConfig.class, ReplaceMotif.class })
@Configuration
@EnableAutoConfiguration 
public class ReplaceMotifTest {

	@Autowired
	SparqlDAO sparqlDAO;

	@Bean
	ReplaceMotif getReplaceMotif() {
		ReplaceMotif replaceMotif = new ReplaceMotif();
		SparqlEntity sparqlEntity = new SparqlEntity();
		sparqlEntity.setValue(Motif.DeleteAccessionNumber, "G00012MO");
		sparqlEntity.setValue(Motif.InsertAccessionNumber, "G12345MO");
		replaceMotif.setFromGraph("http://rdf.glytoucan.org/motif");
		replaceMotif.setToGraph("http://rdf.glytoucan.org/motif");
		replaceMotif.setUsing("http://rdf.glytoucan.org/motif");
		replaceMotif.setSparqlEntity(sparqlEntity);
		return replaceMotif;
	}
	
	@Test
	public void testReplaceMotif() throws SparqlException {
		String test = getReplaceMotif().getSparql();
		System.out.println(test);
	}
	
	@Test
	@Transactional
	public void replaceSparql() throws SparqlException {
		sparqlDAO.execute(getReplaceMotif());
	}
}
