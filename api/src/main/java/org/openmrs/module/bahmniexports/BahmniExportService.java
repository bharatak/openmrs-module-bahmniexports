package org.openmrs.module.bahmniexports;

import org.openmrs.api.OpenmrsService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface BahmniExportService extends OpenmrsService{

	@Transactional(readOnly = true)
	JobExecution launchJob(Job job) throws JobExecutionException;
}
