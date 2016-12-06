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
@SpringApplicationConfiguration(classes = { VirtSesameTransactionConfig.class, ReplaceReducingEndTest.class })
@Configuration
@EnableAutoConfiguration 
public class ReplaceReducingEndTest {

	@Autowired
	SparqlDAO sparqlDAO;

	@Bean
	ReplaceReducingEnd getReplaceReducingEnd() {
		ReplaceReducingEnd replaceReducingEnd = new ReplaceReducingEnd();
		SparqlEntity sparqlEntity = new SparqlEntity();
		sparqlEntity.setValue(Motif.AccessionNumber, "G00012MO");
		sparqlEntity.setValue(Motif.ReducingEnd, "false");
		replaceReducingEnd.setFromGraph("http://rdf.glytoucan.org/motif");
		replaceReducingEnd.setToGraph("http://rdf.glytoucan.org/motif");
		replaceReducingEnd.setUsing("http://rdf.glytoucan.org/motif");
		replaceReducingEnd.setSparqlEntity(sparqlEntity);
		return replaceReducingEnd;
	}
	
//	@Test
//	public void testReplaceMotif() throws SparqlException {
//		String test = getReplaceReducingEnd().getSparql();
//		System.out.println(test);
//	}

	@Bean
	SelectMotif getSelectMotif() {
		SelectMotif selectMotif = new SelectMotif();
		SparqlEntity sparqlEntity = new SparqlEntity();
//		sparqlEntity.setValue(Motif.AccessionNumber, "G12345MO");
		sparqlEntity.setValue(Motif.AccessionNumber, "G00012MO");
		selectMotif.setFrom("FROM <http://rdf.glytoucan.org/motif>\n");
		selectMotif.setSparqlEntity(sparqlEntity);
		return selectMotif;
	}

	@Test
	@Transactional
	public void replaceSparql() throws SparqlException {
		sparqlDAO.query(getSelectMotif());
		sparqlDAO.execute(getReplaceReducingEnd());
		sparqlDAO.query(getSelectMotif());
	}
}
