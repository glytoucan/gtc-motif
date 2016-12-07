package org.glycoinfo.rdf.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glycoinfo.rdf.dao.SparqlDAO;
import org.glycoinfo.rdf.dao.virt.VirtSesameTransactionConfig;
import org.glycoinfo.rdf.service.exception.MotifException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MotifProcedureTest.class, VirtSesameTransactionConfig.class, MotifProcedureConfig.class})
@Configuration
@EnableAutoConfiguration
public class MotifProcedureTest {

  private static final Log logger = LogFactory.getLog(MotifProcedureTest.class);

	@Autowired
	SparqlDAO sparqlDAO;
	
	@Autowired
	MotifProcedure motifProcedure;
	
	
	@Test
	@Transactional
	public void testAddMotif() throws MotifException {
		motifProcedure.searchOneMotif("G12345MO");
		motifProcedure.addOneMotif("G12345MO", "New Glycan Motif", "false");
		motifProcedure.searchOneMotif("G12345MO");
//		motifProcedure.deleteMotif("G12345MO", "12345");
//		motifProcedure.searchMotif("G12345MO");
//		String id = motifProcedure.addMotif("G12345MO", "123456");
//		Assert.assertNotNull(id);
	}
}
