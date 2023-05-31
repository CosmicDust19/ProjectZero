package me.projectzero.infrastructure.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("security")
public record SecurityConfigurationProperties(String secret) {
}
