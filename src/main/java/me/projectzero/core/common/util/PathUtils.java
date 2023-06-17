package me.projectzero.core.common.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@UtilityClass
public class PathUtils {

    @SneakyThrows(IOException.class)
    public Stream<Path> findAllMatchingPaths(Path start, String regex) {
        if (Files.notExists(start)) return Stream.empty();
        return Files.find(start, Integer.MAX_VALUE, (path, basicFileAttributes) -> path.toFile().getName().matches(regex));
    }

}
