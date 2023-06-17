package me.projectzero.core.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ImportAutoConfiguration({MessageSourceConfig.class, ConfigurationProcessorConfig.class})
@DirtiesContext
class MessageSourceIntegrationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void testMessageSource() {
        assertThat(this.context.getMessage("test", null, "This is the default test message.", Locale.ENGLISH))
                .isEqualTo("This is a test message.");
    }

    @Test
    void testMessageSourceWithArgs() {
        assertThat(this.context.getMessage("test-with-args", new Object[]{"Test Arg 1", "Test Arg 2"}, "This is the default test message.", Locale.ENGLISH))
                .isEqualTo("This is a test message. Passed arguments: Test Arg 1, Test Arg 2");
    }

    @Test
    void testMessageSourceDefaultMessage() {
        assertThat(this.context.getMessage("test-default-message", null, "This is the default test message.", Locale.ENGLISH))
                .isEqualTo("This is the default test message.");
    }

    @Configuration(proxyBeanMethods = false)
    static class Config {

    }

}
