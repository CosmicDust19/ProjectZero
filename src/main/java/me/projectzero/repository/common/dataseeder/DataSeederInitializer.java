package me.projectzero.repository.common.dataseeder;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.projectzero.core.aop.annotation.AuthenticateSystemOperation;
import me.projectzero.core.aop.annotation.Validate;
import me.projectzero.core.common.util.PathUtils;
import me.projectzero.core.config.properties.DataSeederProperties;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Comparator;

@Component
@RequiredArgsConstructor
public class DataSeederInitializer {

    private final Collection<AbstractDataSeeder<?, ?>> dataSeeders;

    private final DataSeederProperties config;

    private boolean seeded = false;

    @AuthenticateSystemOperation("data-seeder")
    @EventListener(classes = ContextRefreshedEvent.class)
    public void start(@Validate ContextRefreshedEvent event) {
        if (!config.isEnabled() || seeded) return;
        dataSeeders.stream().sorted(Comparator.comparingInt(AbstractDataSeeder::getOrder)).forEach(AbstractDataSeeder::seed);
        seeded = true;
        dataSeeders.forEach(AbstractDataSeeder::cleanup);
        if (config.isDisableAfterSeed()) disable();
    }

    @SneakyThrows
    private void disable() {
        Path path = PathUtils.findAllMatchingPaths(Paths.get("src"), "application\\.properties").findFirst().orElseThrow(FileNotFoundException::new);
        String content = Files.readString(path).replace("data-seeder.enabled=true", "data-seeder.enabled=false");
        Files.writeString(path, content);
    }

}
