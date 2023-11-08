package com.dataflow.demo.configuration;

import com.dataflow.demo.entities.EitDmnStagingShadow;
import com.dataflow.demo.processor.CustomProcessor;
import com.dataflow.demo.reader.JpaReader;
import com.dataflow.demo.repository.IEitDmnStagingShadowRepository;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@EnableBatchProcessing
@Slf4j
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    IEitDmnStagingShadowRepository eitDmnStagingShadowRepository;
    @Autowired
    private JpaReader jpaReader;
    @Autowired
    public BatchConfiguration(final JobBuilderFactory jobBuilderFactory,
                              final StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }


    @Bean
    public Step dmnUpdateStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return stepBuilderFactory.get("fetchDatafromDMNandSaveUpdated")
                .<EitDmnStagingShadow, EitDmnStagingShadow>chunk(2)
                .reader(jpaReader)
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemWriter<EitDmnStagingShadow> writer() {
        //todo
        return items -> {
            for (EitDmnStagingShadow item : items) {
                System.out.println("From Writer" +  item.toString());
                eitDmnStagingShadowRepository.save(item);
            }
        };
    }

    @Bean
    public ItemProcessor<EitDmnStagingShadow,EitDmnStagingShadow> processor() {
        return new CustomProcessor();
    }
    @Bean
    public Job eitSyncMSS(JobRepository jobRepository, Step dmnUpdateStep) {
        return jobBuilderFactory.get("EitDMNUpdateJob")
                .start(dmnUpdateStep)
                .build();
    }
}
