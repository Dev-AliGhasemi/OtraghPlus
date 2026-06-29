package ir.mrmoshkel.persistence.config;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("ir.mrmoshkel.persistence")
@EnableJpaRepositories(basePackages = "ir.mrmoshkel.persistence")
@EnableJpaAuditing
@EntityScan(
        basePackages = "ir.mrmoshkel.persistence"
)
public class PersistenceConfig {
}
