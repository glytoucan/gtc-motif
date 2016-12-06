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
@SpringApplicationConfiguration(classes = { VirtSesameTransactionConfig.class, ReplaceMotifNameTest.class })
@Configuration
@EnableAutoConfiguration 
public class ReplaceMotifNameTest {

	@Autowired
	SparqlDAO sparqlDAO;

	@Bean
	ReplaceMotifName getReplaceMotifName() {
		ReplaceMotifName replaceMotifName = new ReplaceMotifName();
		SparqlEntity sparqlEntity = new SparqlEntity();
		sparqlEntity.setValue(Motif.AccessionNumber, "G00012MO");
		sparqlEntity.setValue(Motif.MotifName, "glycan_motif_name");
		replaceMotifName.setFromGraph("http://rdf.glytoucan.org/motif");
		replaceMotifName.setToGraph("http://rdf.glytoucan.org/motif");
		replaceMotifName.setUsing("http://rdf.glytoucan.org/motif");
		replaceMotifName.setSparqlEntity(sparqlEntity);
		return replaceMotifName;
	}
	
//	@Test
//	public void testReplaceMotif() throws SparqlException {
//		String test = getReplaceMotifName().getSparql();
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
		sparqlDAO.execute(getReplaceMotifName());
		sparqlDAO.query(getSelectMotif());
//		sparqlDAO.insert(getReplaceMotif());
//		sparqlDAO.delete(getReplaceMotif());
	}

}
