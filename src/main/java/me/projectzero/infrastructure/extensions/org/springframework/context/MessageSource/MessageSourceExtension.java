package me.projectzero.infrastructure.extensions.org.springframework.context.MessageSource;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.ThisClass;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Extension
@Configuration
public class MessageSourceExtension {

    private static MessageSource messageSource;

    @Bean
    public synchronized void setMessageSource() {
        if (messageSource == null)
            messageSource = MessageSource.class.getSpringBean();
    }

    public static MessageSource getSpringBean(@ThisClass Class<MessageSource> ignored) {
        return MessageSourceExtension.messageSource;
    }

}
