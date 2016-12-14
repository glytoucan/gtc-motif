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
@SpringApplicationConfiguration(classes = { VirtSesameTransactionConfig.class, ReplaceMotifTest.class })
@Configuration
@EnableAutoConfiguration 
public class ReplaceMotifTest {

	@Autowired
	SparqlDAO sparqlDAO;

	@Bean
	ReplaceMotif getReplaceMotif() {
		ReplaceMotif replaceMotif = new ReplaceMotif();
		SparqlEntity sparqlEntity = new SparqlEntity();
		sparqlEntity.setValue(Motif.DeleteAccessionNumber, "G00017MO");
		sparqlEntity.setValue(Motif.InsertAccessionNumber, "G12349MO");
		replaceMotif.setFromGraph("http://rdf.glytoucan.org/motif");
		replaceMotif.setToGraph("http://rdf.glytoucan.org/motif");
		replaceMotif.setUsing("http://rdf.glytoucan.org/motif");
		replaceMotif.setSparqlEntity(sparqlEntity);
		return replaceMotif;
	}
	
//	@Test
//	public void testReplaceMotif() throws SparqlException {
//		String test = getReplaceMotif().getSparql();
//		System.out.println(test);
//	}
	
	@Bean
	SelectMotif getSelectMotif() {
		SelectMotif selectMotif = new SelectMotif();
		SparqlEntity sparqlEntity = new SparqlEntity();
//		sparqlEntity.setValue(Motif.AccessionNumber, "G12345MO");
		sparqlEntity.setValue(Motif.AccessionNumber, "G12349MO");
		selectMotif.setFrom("FROM <http://rdf.glytoucan.org/motif>\n");
		selectMotif.setSparqlEntity(sparqlEntity);
		return selectMotif;
	}
//	@Test
//	public void testSelectMotif() throws SparqlException {
//		String test = getSelectMotif().getSparql();
//		System.out.println(test);
//	}
	
	@Test
	@Transactional
	public void replaceSparql() throws SparqlException {
		sparqlDAO.execute(getReplaceMotif());
		sparqlDAO.query(getSelectMotif());
//		sparqlDAO.insert(getReplaceMotif());
//		sparqlDAO.delete(getReplaceMotif());
	}

}
