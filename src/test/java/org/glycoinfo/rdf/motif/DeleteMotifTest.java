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
@SpringApplicationConfiguration(classes = { VirtSesameTransactionConfig.class, DeleteMotifTest.class })
@Configuration
@EnableAutoConfiguration 
public class DeleteMotifTest {

	@Autowired
	SparqlDAO sparqlDAO;

	@Bean
	DeleteMotif getDeleteMotif() {
		DeleteMotif deleteMotif = new DeleteMotif();
		SparqlEntity sparqlEntity = new SparqlEntity();
		sparqlEntity.setValue(Motif.AccessionNumber, "G00015MO");
		deleteMotif.setGraph("http://rdf.glytoucan.org/motif/test");
		deleteMotif.setSparqlEntity(sparqlEntity);
		return deleteMotif;
	}
	
//	@Bean
//	DeleteMotif getDeleteMotif() {
//		DeleteMotif deleteMotif = new DeleteMotif();
//		SparqlEntity sparqlEntity = new SparqlEntity();
//		sparqlEntity.setValue(Motif.AccessionNumber, "G00012MO");
//		sparqlEntity.setValue(Motif.MotifName, "Glycosphingolipid Isoglobo series");
//		sparqlEntity.setValue(Motif.ReducingEnd, "true");
//		deleteMotif.setGraph("http://rdf.glytoucan.org/motif");
//		deleteMotif.setSparqlEntity(sparqlEntity);
//		return deleteMotif;
//	}
//	@Test
//	public void testDeleteMotif() throws SparqlException {
//		String test = getDeleteMotif().getSparql();
//		System.out.println(test);
//	}

	@Bean
	SelectMotif getSelectMotif() {
		SelectMotif selectMotif = new SelectMotif();
		SparqlEntity sparqlEntity = new SparqlEntity();
//		sparqlEntity.setValue(Motif.AccessionNumber, "G12345MO");
		sparqlEntity.setValue(Motif.AccessionNumber, "G00015MO");
		selectMotif.setFrom("FROM <http://rdf.glytoucan.org/motif/test>\n");
		selectMotif.setSparqlEntity(sparqlEntity);
		return selectMotif;
	}

	@Test
	@Transactional
	public void deleteSparql() throws SparqlException {
		sparqlDAO.query(getSelectMotif());
		sparqlDAO.execute(getDeleteMotif());
//		sparqlDAO.delete(getDeleteMotif());
		sparqlDAO.query(getSelectMotif());
	}

}
