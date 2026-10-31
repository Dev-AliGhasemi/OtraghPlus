package ir.mrmoshkel.host.infrastructure.persistence.config;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan("ir.mrmoshkel.host.infrastructure.persistence")
@EnableJpaRepositories(basePackages = "ir.mrmoshkel.host.infrastructure.persistence")
@EnableMongoRepositories(basePackages = "ir.mrmoshkel.host.infrastructure.persistence")
@EnableJpaAuditing
@EntityScan(
        basePackages = "ir.mrmoshkel.host.infrastructure.persistence"
)
public class PersistenceConfig {
}
