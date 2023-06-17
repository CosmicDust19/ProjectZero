package me.projectzero.core.config;

import me.projectzero.core.config.properties.DataSeederProperties;
import me.projectzero.core.config.properties.DbNamingStrategyProperties;
import me.projectzero.core.config.properties.MessageSourceProperties;
import me.projectzero.core.config.properties.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class ConfigurationProcessorConfig {

    @Bean
    @ConfigurationProperties(prefix = "db-naming-strategy")
    public DbNamingStrategyProperties dbNamingStrategyProperties() {
        return new DbNamingStrategyProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "message-source")
    public MessageSourceProperties messageSourceProperties() {
        return new MessageSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "security")
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "data-seeder")
    public DataSeederProperties dataSeederProperties() {
        return new DataSeederProperties();
    }

}
