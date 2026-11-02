package ir.mrmoshkel.bootstrap;

import ir.mrmoshkel.common.infrastructure.config.CommonInfrastructureConfig;
import ir.mrmoshkel.host.infrastructure.endpoint.config.EndpointConfig;
import ir.mrmoshkel.host.infrastructure.persistence.config.PersistenceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        EndpointConfig.class,
        PersistenceConfig.class,
        CommonInfrastructureConfig.class,
})
public class BootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }

}
