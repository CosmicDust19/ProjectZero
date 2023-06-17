package me.projectzero.core.common.util;

import me.projectzero.ProjectZeroApplication;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationInfoTest {

    @Test
    void mainClassPackageNameShouldStartWithTheCalculatedPackageName() {
        assertTrue(ProjectZeroApplication.class.getPackageName().startsWith(ApplicationInfo.BASE_PACKAGE_NAME));
    }

}
