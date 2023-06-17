package me.projectzero.core.config.properties;

import lombok.Data;
import org.springframework.boot.convert.DurationUnit;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Data
public class MessageSourceProperties {

    /**
     * Comma-separated list of basenames (essentially a fully-qualified classpath
     * location), each following the ResourceBundle convention with relaxed support for
     * slash based locations. If it doesn't contain a package qualifier (such as
     * "org.mypackage"), it will be resolved from the classpath root.
     */
    private String basename = "classpath:messages/messages";

    /**
     * Message bundles encoding.
     */
    private Charset encoding = StandardCharsets.UTF_8;

    /**
     * Loaded resource bundle files cache duration. When not set, bundles are cached
     * forever. If a duration suffix is not specified, seconds will be used.
     */
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration cacheDuration = Duration.ofSeconds(3600);

    /**
     * Whether to fall back to the system Locale if no files for a specific Locale have
     * been found. if this is turned off, the only fallback will be the default file (e.g.
     * "messages.properties" for basename "messages").
     */
    private boolean fallbackToSystemLocale = false;

    /**
     * Whether to always apply the MessageFormat rules, parsing even messages without
     * arguments.
     */
    private boolean alwaysUseMessageFormat = false;

    /**
     * Whether to use the message code as the default message instead of throwing a
     * "NoSuchMessageException". Recommended during development only.
     */
    private boolean useCodeAsDefaultMessage = true;

}
