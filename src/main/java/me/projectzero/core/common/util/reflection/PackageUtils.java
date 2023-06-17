package me.projectzero.core.common.util.reflection;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class PackageUtils {

    /**
     * @param clazz           The super class
     * @param packageName The searched package
     * @return Set of all subclasses of the {@code clazz}
     */
    @SneakyThrows(ClassNotFoundException.class)
    public Set<Class<?>> getSubClassesInsidePackage(String packageName, Class<?> clazz) {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(clazz));
        Set<BeanDefinition> components = provider.findCandidateComponents(packageName);
        Set<Class<?>> subClasses = new HashSet<>();
        for (BeanDefinition component : components) {
            subClasses.add(Class.forName(component.getBeanClassName()));
        }
        return subClasses;
    }

    /**
     * @param packageName The searched package
     * @return Set of all subclasses of the {@code clazz}
     */
    public Set<Class<?>> getClassesInsidePackage(String packageName) {
        return getSubClassesInsidePackage(packageName, Object.class);
    }

}
