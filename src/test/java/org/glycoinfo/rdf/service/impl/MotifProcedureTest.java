package org.glycoinfo.rdf.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glycoinfo.rdf.dao.SparqlDAO;
import org.glycoinfo.rdf.dao.SparqlEntity;
import org.glycoinfo.rdf.dao.virt.VirtSesameTransactionConfig;
import org.glycoinfo.rdf.service.exception.MotifException;
import org.junit.Assert;
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
	public void testMotif() throws MotifException {
//		String id = motifProcedure.replaceMotif("G00018MO", "G12349MO");
//		logger.debug("result:> " + id);
//		Assert.assertNotNull(id);

//		String id = motifProcedure.replaceMotifName("G00012MO", "New Glycan Motif");
//		logger.debug("result:> " + id);
//		Assert.assertNotNull(id);

//		String id = motifProcedure.replaceReducingEnd("G12345MO", "false");
//		logger.debug("result:> " + id);
//		Assert.assertNotNull(id);

//		String id = motifProcedure.addOneMotif("G12344MO", "New glycan motif tyep 2", "true");
//		logger.debug("result:> " + id);
//		motifProcedure.searchOneMotif("G12344MO");
//		Assert.assertNotNull(id);

//		String id = motifProcedure.removeOnMotif("G00018MO");
//		logger.debug("result:> " + id);
//		Assert.assertNotNull(id);

		List<SparqlEntity> results = motifProcedure.searchOneMotif("G00045MO");
		for (SparqlEntity sparqlEntity : results) {
			String sparqlreturnValue = sparqlEntity.getValue("MotifName");
			logger.debug("MotifName:> " + sparqlreturnValue);
			Assert.assertNotNull(sparqlreturnValue);
		}

//		motifProcedure.searchOneMotif("G12345MO");
//		motifProcedure.deleteMotif("G12345MO", "12345");
//		motifProcedure.searchMotif("G12345MO");
//		String id = motifProcedure.addMotif("G12345MO", "123456");
//		Assert.assertNotNull(id);
//		Assert.assertNull(id);
	}
}
