package ir.mrmoshkel.common.infrastructure.config;

import ir.mrmoshkel.common.infrastructure.eventstore.BaseEventStoreRepository;
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
