package de.fi.first.batchprocessing;


import de.fi.first.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
public class BatchConfig {


    @Bean
    public Step step1(JobRepository jobRepository,
                                   PlatformTransactionManager transactionManager)
    {
        return new StepBuilder("meinLeerzeilenStep", jobRepository).tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
                Thread.sleep((long)(Math.random()*1000));

                System.out.println( "Step1 Thread Nr." + Thread.currentThread().getId() );
                return RepeatStatus.FINISHED;
            }
        },transactionManager).build();

    }
    @Bean
    public Step step2(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager)
    {
        return new StepBuilder("meinLeerzeilenStep", jobRepository).tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
                Thread.sleep((long)(Math.random()*1000));

                System.out.println( "Step2 Thread Nr." + Thread.currentThread().getId() );
                return RepeatStatus.FINISHED;
            }
        },transactionManager).build();

    }


    @Bean
    public Step step3(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager)
    {
        return new StepBuilder("meinLeerzeilenStep", jobRepository).tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
                Thread.sleep((long)(Math.random()*1000));

                System.out.println( "Step3 Thread Nr." + Thread.currentThread().getId() );
                return RepeatStatus.FINISHED;
            }
        },transactionManager).build();

    }

    @Bean
    public Step step4(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager)
    {
        return new StepBuilder("meinLeerzeilenStep", jobRepository).tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
                Thread.sleep((long)(Math.random()*1000));

                System.out.println( "Step4 Thread Nr." + Thread.currentThread().getId() );
                return RepeatStatus.FINISHED;
            }
        },transactionManager).build();

    }
    @Bean
    public Step step5(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager)
    {
        return new StepBuilder("meinLeerzeilenStep", jobRepository).tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
                Thread.sleep((long)(Math.random()*1000));

                System.out.println( "Step5 Thread Nr." + Thread.currentThread().getId() );
                return RepeatStatus.FINISHED;
            }
        },transactionManager).build();

    }


    @Bean
    public Step step6(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager)
    {
        return new StepBuilder("meinLeerzeilenStep", jobRepository).tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
                Thread.sleep((long)(Math.random()*1000));

                System.out.println( "Step6 Thread Nr." + Thread.currentThread().getId() );
                return RepeatStatus.FINISHED;
            }
        },transactionManager).build();

    }






    @Bean
    public Job importUserJob(JobRepository jobRepository, JobCompletionNotificationListener listener
            , Step step1
            , Step step2

            , Step step6
    ) {

        Flow flow3 = new FlowBuilder<Flow>( "flow3" ).from( step3(null, null) ).end();
        Flow flow4 = new FlowBuilder<Flow>( "flow4" ).from( step4(null, null) ).end();
        Flow flow5 = new FlowBuilder<Flow>( "flow5" ).from( step5(null,null) ).end();

        Flow splitFlow345 = new FlowBuilder<Flow>( "splitFlow345" )
                .start( flow3 )
                .split( new SimpleAsyncTaskExecutor() )
                .add(   flow4, flow5 )
                .build();
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())

                //.listener(listener)

                .flow(step1)
                .next(step2)
                .next(splitFlow345)
                .next(step6)
                .end()
                .build();
    }




}