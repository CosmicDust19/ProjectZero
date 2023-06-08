package me.projectzero.infrastructure.common.util;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ApplicationInfo {

    public final String PACKAGE_NAME = packageName();

    private String packageName() {
        File current = Paths.get("src/main/java").toAbsolutePath().toFile();
        File[] subFiles;
        List<String> directories = new ArrayList<>();
        while ((subFiles = current.listFiles()) != null && subFiles.length == 1 && (current = subFiles[0]).isDirectory()) {
            directories.add(current.getName());
        }
        return String.join(".", directories);
    }

}
