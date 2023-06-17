package me.projectzero.core.common.util.reflection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassUtilsTest {

    private ReflectionTestPojo testObject;

    @BeforeEach
    public void init() {
        testObject = new ReflectionTestPojo();
    }

    @Test
    void givenClassAndMethodName_whenGetMethodCalled_thenShouldReturnRightMethod() {
        String givenMethodName = "getField1";
        Class<?> givenClass = testObject.getClass();

        Method method = ClassUtils.getMethod(givenClass, givenMethodName).orElseThrow();

        String expectedMethodName = "getField1";
        Class<?> expectedClass = ReflectionTestPojo.class;
        int expectedParameterCount = 0;
        Class<?> expectedReturnType = String.class;

        String actualMethodName = method.getName();
        Class<?> actualClass = method.getDeclaringClass();
        int actualParameterCount = method.getParameterCount();
        Class<?> actualReturnType = method.getReturnType();

        assertEquals(expectedMethodName, actualMethodName);
        assertEquals(expectedClass, actualClass);
        assertEquals(expectedParameterCount, actualParameterCount);
        assertEquals(expectedReturnType, actualReturnType);
    }

    @Test
    void givenNullClassAndNullMethodName_whenGetMethodCalled_thenShouldReturnEmptyOptional() {
        Optional<Method> expected = Optional.empty();
        Optional<Method> actual = ClassUtils.getMethod(null, null);
        assertEquals(expected, actual);
    }

    @Test
    void givenClassAndFieldName_whenGetFieldCalled_thenShouldReturnRightField() {
        String givenFieldName = "field1";
        Class<?> givenClass = testObject.getClass();

        Field field = ClassUtils.getField(givenClass, givenFieldName).orElseThrow();

        String expectedMethodName = "field1";
        Class<?> expectedClass = ReflectionTestPojo.class;
        Class<?> expectedType = String.class;

        String actualMethodName = field.getName();
        Class<?> actualClass = field.getDeclaringClass();
        Class<?> actualType = field.getType();

        assertEquals(expectedMethodName, actualMethodName);
        assertEquals(expectedClass, actualClass);
        assertEquals(expectedType, actualType);
    }

    @Test
    void givenNullClassAndNullFieldName_whenGetFieldCalled_thenShouldReturnEmptyOptional() {
        Optional<Field> expected = Optional.empty();
        Optional<Field> actual = ClassUtils.getField(null, null);
        assertEquals(expected, actual);
    }

}
