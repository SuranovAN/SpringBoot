package suranovan.springboot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import suranovan.springboot.profiles.DevProfile;
import suranovan.springboot.profiles.ProductionProfile;
import suranovan.springboot.profiles.SystemProfile;

@Configuration
public class JavaConfig {
    @Bean
    @ConditionalOnProperty(name = "suranovan.springboot.profiles.dev", havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(name = "suranovan.springboot.profiles.dev", havingValue = "false")
    public SystemProfile productionProfile() {
        return new ProductionProfile();
    }
}
