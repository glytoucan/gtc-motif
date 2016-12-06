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


/*
 * Test the insert data for the motif
 * 
 * @author shinmachi
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { VirtSesameTransactionConfig.class, InsertMotifTest.class })
@Configuration
@EnableAutoConfiguration 
public class InsertMotifTest  {

	@Autowired
	SparqlDAO sparqlDAO;
	
	@Bean
	InsertMotif getInsertMotif() {
		InsertMotif insertMotif = new InsertMotif();
		SparqlEntity sparqlEntity = new SparqlEntity();
		sparqlEntity.setValue(Motif.AccessionNumber, "G122345AA");
		insertMotif.setSparqlEntity(sparqlEntity);
		return insertMotif;
	}
	
	@Bean
	SelectMotif getSelectMotif() {
		SelectMotif selectMotif = new SelectMotif();
		SparqlEntity sparqlEntity = new SparqlEntity();
		sparqlEntity.setValue(Motif.AccessionNumber, "G00012MO");
//		sparqlEntity.setValue(Motif.AccessionNumber, "G12345AA");
		selectMotif.setSparqlEntity(sparqlEntity);
		return selectMotif;
	}
	
/*	
	@Test
	public void testInsertSparql() throws SparqlException {
		String test = getInsertMotif().getSparql();
		System.out.println(test);
	}

	@Test
	@Transactional
	public void insertSpalrql() throws SparqlException {
		sparqlDAO.insert(getInsertMotif());
	}
 */
	@Test
	@Transactional
	public void selectSparql() throws SparqlException {
		sparqlDAO.query(getSelectMotif());
	}
	
	
}
