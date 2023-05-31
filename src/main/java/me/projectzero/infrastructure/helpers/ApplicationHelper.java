package me.projectzero.infrastructure.helpers;

import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationHelper implements ApplicationContextAware {

    @Getter
    private static ApplicationContext applicationContext;
    public static final String PACKAGE_NAME = calculateBasePackage();

    @Override
    public synchronized void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        if (ApplicationHelper.applicationContext == null) {
            ApplicationHelper.applicationContext = applicationContext;
        }
    }

    /**
     * @param type Type of bean to be wanted.
     * @return Spring bean instance of type parameter type without need of dependency injection.
     */
    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

    private static String calculateBasePackage() {
        File current = Paths.get("src/main/java").toAbsolutePath().toFile();
        File[] subFiles;
        List<String> directories = new ArrayList<>();
        while ((subFiles = current.listFiles()) != null && subFiles.length == 1 && (current = subFiles[0]).isDirectory()) {
            directories.add(current.getName());
        }
        return String.join(".", directories);
    }

}