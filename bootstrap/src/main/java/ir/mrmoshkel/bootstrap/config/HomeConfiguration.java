package ir.mrmoshkel.bootstrap.config;

import ir.mrmoshkel.home.handler.query.HomeQueryHandler;
import ir.mrmoshkel.persistence.home.repository.HomeRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HomeConfiguration {
    @Bean
    public HomeQueryHandler homeQueryHandler(HomeRepositoryAdapter homeRepositoryAdapter) {
        return new HomeQueryHandler(homeRepositoryAdapter);
    }
}
