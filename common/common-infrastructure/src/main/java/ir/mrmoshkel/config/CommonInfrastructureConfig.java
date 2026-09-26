package ir.mrmoshkel.config;

import ir.mrmoshkel.eventstore.EventStoreRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@ComponentScan("ir.mrmoshkel")
@EnableKafka
@EnableMongoRepositories(basePackageClasses = EventStoreRepository.class)
public class CommonInfrastructureConfig {
}
