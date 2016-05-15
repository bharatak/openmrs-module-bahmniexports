package org.openmrs.module.bahmniexports;

import org.hibernate.SessionFactory;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.BatchConfigurationSelector;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

public class BahmniExportServiceImpl extends BaseOpenmrsService implements BahmniExportService {

	private SessionFactory sessionFactory;

	private PlatformTransactionManager transactionManager;

	@Autowired
	private JobLauncher jobLauncher;

	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}
	//	private JobRepository jobRepository;
//	private SimpleJobLauncher jobLauncher;
//	private JobExplorer jobExplorer;
//

	@Override
	public void onStartup() {
		super.onStartup();
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
//
//
//	@Override
//	public JobRepository getJobRepository() {
//		return jobRepository;
//	}
//
//	@Override
//	public PlatformTransactionManager getTransactionManager() {
//		return transactionManager;
//	}
//
//	@Override
//	public JobLauncher getJobLauncher() {
//		return jobLauncher;
//	}
//
//	@Override
//	public JobExplorer getJobExplorer() {
//		return jobExplorer;
//	}


	@Transactional(readOnly = true)
	public JobExecution launchJob(Job job) throws JobExecutionException {
//		try {
//			MapJobRepositoryFactoryBean jobRepositoryFactory = new MapJobRepositoryFactoryBean(this.transactionManager);
//			jobRepositoryFactory.afterPropertiesSet();
//			this.jobRepository = jobRepositoryFactory.getObject();
//
//			MapJobExplorerFactoryBean jobExplorerFactory = new MapJobExplorerFactoryBean(jobRepositoryFactory);
//			jobExplorerFactory.afterPropertiesSet();
//			this.jobExplorer = jobExplorerFactory.getObject();
//
//			this.jobLauncher = new SimpleJobLauncher();
//			jobLauncher.setJobRepository(jobRepository);
//			jobLauncher.afterPropertiesSet();
//
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}


		return getJobLauncher().run(job, new JobParametersBuilder().toJobParameters());
	}
}
