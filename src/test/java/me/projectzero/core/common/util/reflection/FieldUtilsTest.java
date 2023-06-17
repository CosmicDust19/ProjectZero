package me.projectzero.core.common.util.reflection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldUtilsTest {

    private ReflectionTestPojo testObject;

    @BeforeEach
    public void init() {
        testObject = new ReflectionTestPojo();
    }

    @Test
    void givenFieldAndObject_whenGetValueCalled_thenShouldReturnRightValue() {
        Field givenField = ClassUtils.getField(testObject.getClass(), "field1").orElseThrow();
        Object givenObject = testObject;

        String expectedValue = "Field 1";
        String actualValue = FieldUtils.getValue(givenField, givenObject).orElseThrow().toString();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void givenNullFieldAndNullObject_whenGetValueCalled_thenShouldReturnEmptyOptional() {
        Optional<Field> expected = Optional.empty();
        Optional<Object> actual = FieldUtils.getValue(null, null);
        assertEquals(expected, actual);
    }

    @Test
    void givenFieldAndObjectAndType_whenGetValueCalled_thenShouldReturnRightValue() {
        Field givenField = ClassUtils.getField(testObject.getClass(), "field1").orElseThrow();
        Object givenObject = testObject;
        Class<String> givenType = String.class;

        String expectedValue = "Field 1";
        String actualValue = FieldUtils.getValue(givenField, givenObject, givenType).orElseThrow();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void givenNullFieldAndNullObjectAndNullType_whenGetValueCalled_thenShouldReturnEmptyOptional() {
        Optional<Field> expected = Optional.empty();
        Optional<Object> actual = FieldUtils.getValue(null, null, null);
        assertEquals(expected, actual);
    }

    @Test
    void givenFieldAndObjectAndValue_whenSetValueCalled_thenShouldSetToRightValue() {
        Field givenField = ClassUtils.getField(testObject.getClass(), "field1").orElseThrow();
        Object givenObject = testObject;
        String givenValue = "New Field 1";

        boolean success = FieldUtils.setValue(givenField, givenObject, givenValue);

        String expectedValue = "New Field 1";
        String actualValue = testObject.getField1();
        assertTrue(success);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void givenFieldAndObjectAndTransformation_whenSetValueCalled_thenShouldSetToRightValue() {
        Field givenField = ClassUtils.getField(testObject.getClass(), "field1").orElseThrow();
        Object givenObject = testObject;
        Function<Optional<Object>, Object> givenTransformation = optional -> optional.map(value -> "New " + value).orElse(null);

        boolean success = FieldUtils.setValue(givenField, givenObject, givenTransformation);

        String expectedValue = "New Field 1";
        String actualValue = testObject.getField1();
        assertTrue(success);
        assertEquals(expectedValue, actualValue);
    }

}
