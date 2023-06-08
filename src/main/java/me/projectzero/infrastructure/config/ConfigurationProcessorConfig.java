package me.projectzero.infrastructure.config;

import me.projectzero.api.security.SecurityConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SecurityConfigurationProperties.class)
public class ConfigurationProcessorConfig {
}
