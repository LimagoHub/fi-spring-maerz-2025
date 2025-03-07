package de.fi.simplepart.batch;

import de.fi.simplepart.pojo.Umsatz;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import java.net.MalformedURLException;

@Configuration
public class PartitionBatchConfig {


    // Ersetzen durch junk step
    @Bean
    public Step slaveStep(JobRepository jobRepository, PlatformTransactionManager transactionManager)
    {
        return new StepBuilder("slaveStep", jobRepository).tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
                ExecutionContext ec = chunkContext.getStepContext().getStepExecution().getExecutionContext();
                System.out.printf("Min = %s und Max = %s\n", ec.getInt("minValue"), ec.getInt("maxValue"));
                return RepeatStatus.FINISHED;
            }
        },transactionManager).build();

    }

    @Bean
    public Step partitionStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, final TaskExecutor taskExecutor)
            throws UnexpectedInputException {
        return new StepBuilder("partitionStep", jobRepository)
                .partitioner("slaveStep", partitioner())
                .step(slaveStep(jobRepository, transactionManager))
                .taskExecutor(taskExecutor())
                .gridSize(10)
                .build();
    }

    @Bean(name = "partitionerJob")
    public Job partitionerJob(JobRepository jobRepository, Step partitionStep)
            throws UnexpectedInputException, MalformedURLException {
        return new JobBuilder("partitionerJob", jobRepository).incrementer( new RunIdIncrementer() )
                .start( partitionStep)


                .build();
    }

    @Bean
    public RangePartinionierer partitioner() {
        return  new RangePartinionierer();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public ItemWriter<Umsatz> writer() {
        return new ItemWriter<Umsatz>() {

            @Override
            public void write(Chunk<? extends Umsatz> chunk) throws Exception {
                System.out.println(chunk);
            }
        };
    }


}
