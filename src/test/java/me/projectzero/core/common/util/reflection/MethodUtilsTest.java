package me.projectzero.core.common.util.reflection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MethodUtilsTest {

    private ReflectionTestPojo testObject;

    @BeforeEach
    public void init() {
        testObject = new ReflectionTestPojo();
    }

    @Test
    void givenMethodAndObject_whenGetField1InvokedByTryInvoke_thenShouldReturnRightValue() {
        Method givenMethod = ClassUtils.getMethod(ReflectionTestPojo.class, "getField1").orElseThrow();
        Object givenObject = testObject;

        String expectedValue = "Field 1";
        String actualValue = MethodUtils.tryInvoke(givenMethod, givenObject).orElseThrow().toString();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void givenMethodAndObjectAndType_whenGetField1InvokedByTryInvoke_thenShouldReturnRightValue() {
        Method givenMethod = ClassUtils.getMethod(ReflectionTestPojo.class, "getField1").orElseThrow();
        Object givenObject = testObject;
        Class<String> givenType = String.class;

        String expectedValue = "Field 1";
        String actualValue = MethodUtils.tryInvoke(givenMethod, givenObject, givenType).orElseThrow();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void givenMethodAndObjectAndAValue_whenSetField1InvokedByTryInvoke_thenShouldReturnRightValue() {
        Method givenMethod = ClassUtils.getMethod(ReflectionTestPojo.class, "setField1", String.class).orElseThrow();
        Object givenObject = testObject;
        String givenValue = "New Field 1";

        MethodUtils.tryInvoke(givenMethod, givenObject, givenValue);

        String expectedValue = "New Field 1";
        String actualValue = testObject.getField1();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void givenNullMethodAndNullObjectAndNullType_whenTryInvokeCalled_thenShouldReturnEmptyOptional() {
        Optional<?> expectedValue = Optional.empty();
        Optional<?> actualValue = MethodUtils.tryInvoke(null, null, (Class<?>) null);
        assertEquals(expectedValue, actualValue);
    }

}
