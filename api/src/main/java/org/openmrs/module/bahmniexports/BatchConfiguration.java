package org.openmrs.module.bahmniexports;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration {

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Value("classpath:20070122.teststream.ImportTradeDataStep.txt")
	private Resource file;

//	@Bean
//	public DataSource dataSource(){
//		DbSessionFactory dbSessionFactory = Context.getRegisteredComponents(DbSessionFactory.class).get(0);
//		DataSource ds = new SingleConnectionDataSource(dbSessionFactory.getHibernateSessionFactory().openStatelessSession().connection(), true);
//		return ds;
//	}

	@Bean
	public Job job() {
		return jobs.get("myJob")
				.incrementer(new RunIdIncrementer())
				.start(step1())
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory
				.get("somename")
				.<String, String>chunk(50)
				.reader(flatFileItemReader())
				.writer(flatFileItemWriter())
				.build();

	}

	private FlatFileItemReader<String> flatFileItemReader() {
		FlatFileItemReader<String> reader = new FlatFileItemReader<>();
		reader.setResource(file);
		reader.setLineMapper(new DefaultLineMapper<String>());
		return reader;
	}

	private FlatFileItemWriter<String> flatFileItemWriter(){
		FlatFileItemWriter flatFileItemWriter = new FlatFileItemWriter();
		flatFileItemWriter.setResource(new FileSystemResource("/tmp/abc.txt"));
		flatFileItemWriter.setLineAggregator(new PassThroughLineAggregator());
		return flatFileItemWriter;
	}

//	protected JobRepository createJobRepository() throws Exception {
//		MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean(transactionManager);
//		factory.setValidateTransactionState(true);
//		factory.afterPropertiesSet();
//		return  factory.getObject();
//
////		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
////		factory.setDataSource(dataSource());
////		factory.setTransactionManager(transactionManager);
////		factory.setValidateTransactionState(false);
////		factory.afterPropertiesSet();
////		return  factory.getObject();
//	}

//	protected JobLauncher createJobLauncher() throws Exception {
//		BatchJobLauncher jobLauncher = new BatchJobLauncher();
//		jobLauncher.setJobRepository(getJobRepository());
//		jobLauncher.afterPropertiesSet();
//		return jobLauncher;
//	}

}
