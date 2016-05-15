package org.openmrs.module.bahmniexports;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.transaction.annotation.Transactional;

public class BatchJobLauncher extends SimpleJobLauncher{

	@Transactional(readOnly = true)
	@Override
	public JobExecution run(Job job, JobParameters jobParameters)
			throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException,
			JobParametersInvalidException {
		return super.run(job, jobParameters);
	}
}
