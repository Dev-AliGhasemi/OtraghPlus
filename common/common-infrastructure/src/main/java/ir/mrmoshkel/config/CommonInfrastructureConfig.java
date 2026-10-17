package ir.mrmoshkel.config;

import ir.mrmoshkel.eventstore.BaseEventStoreRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@ComponentScan("ir.mrmoshkel")
@EnableKafka
@EnableMongoRepositories(basePackageClasses = BaseEventStoreRepository.class)
public class CommonInfrastructureConfig {
}
