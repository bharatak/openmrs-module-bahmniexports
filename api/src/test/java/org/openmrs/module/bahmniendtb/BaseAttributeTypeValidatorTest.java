package org.openmrs.module.bahmniendtb;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openmrs.VisitAttributeType;
import org.openmrs.api.context.Context;
import org.openmrs.customdatatype.datatype.RegexValidatedTextDatatype;
import org.openmrs.module.bahmniexports.BahmniExportService;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.validator.VisitAttributeTypeValidator;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

public class BaseAttributeTypeValidatorTest extends BaseModuleContextSensitiveTest {

	VisitAttributeTypeValidator validator;

	@SuppressWarnings("rawtypes")
	VisitAttributeType attributeType;


	@Autowired
	private Job job;


//	@Value("classpath:schema-hsqldb.sql")
//	private Resource batchSchemaScript;
//
//	@Autowired
//	private DataSource dataSource;

	BindException errors;

	@Before
	public void before() {
		validator = new VisitAttributeTypeValidator();
		attributeType = new VisitAttributeType();
		errors = new BindException(attributeType, "attributeType");

		Context.addConfigProperty("transaction.auto_close_session",true);

//		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//		populator.addScript(batchSchemaScript);
//		populator.setContinueOnError(true);
//		DatabasePopulatorUtils.execute(populator, dataSource);
	}


	@Test
	public void validate_shouldNotAllowMaxOccursLessThan1() throws Exception {
		attributeType.setMaxOccurs(0);
		validator.validate(attributeType, errors);
		Assert.assertTrue(errors.getFieldErrors("maxOccurs").size() > 0);
		Context.getService(BahmniExportService.class).launchJob(job);
//		jobLauncher.run(job,new JobParametersBuilder().toJobParameters());
	}

}
