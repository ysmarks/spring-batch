package br.com.spring.batch.treinamento.springbatch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.spring.batch.treinamento.springbatch.domain.Autobot;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport{
	
	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");
			jdbcTemplate.query("SELECT nome, carro FROM autobot", 
					(rs, row) -> new Autobot(rs.getString(1), rs.getString(2)))
			.forEach(autobot -> log.info("Found <" + autobot + "> in the database."));
		}
		
	}

	

}
