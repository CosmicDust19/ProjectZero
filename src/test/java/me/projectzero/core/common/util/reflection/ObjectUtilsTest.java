package me.projectzero.core.common.util.reflection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ObjectUtilsTest {

    private ReflectionTestPojo testObject;

    @BeforeEach
    public void init() {
        testObject = new ReflectionTestPojo();
    }

    @Test
    void givenObjectAndFieldName_whenGetValueCalled_thenShouldReturnRightValue() {
        Object givenObject = testObject;
        String givenFieldName = "field1";

        String expectedValue = "Field 1";
        String actualValue = ObjectUtils.getFieldValue(givenObject, givenFieldName).orElseThrow().toString();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void givenNullObjectAndNullFieldName_whenGetValueCalled_thenShouldReturnEmptyOptional() {
        Optional<Object> expectedValue = Optional.empty();
        Optional<Object> actualValue = ObjectUtils.getFieldValue(null, null);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void givenObjectAndFieldNameAndType_whenGetValueCalled_thenShouldReturnRightValue() {
        Object givenObject = testObject;
        String givenFieldName = "field1";
        Class<String> givenType = String.class;

        String expectedValue = "Field 1";
        String actualValue = ObjectUtils.getFieldValue(givenObject, givenFieldName, givenType).orElseThrow();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void givenNullObjectAndNullFieldNameAndNullType_whenGetValueCalled_thenShouldReturnEmptyOptional() {
        Optional<Object> expectedValue = Optional.empty();
        Optional<Object> actualValue = ObjectUtils.getFieldValue(null, null, null);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void givenObjectAndFieldNameAndValue_whenSetValueCalled_thenShouldSetToRightValue() {
        Object givenObject = testObject;
        String givenFieldName = "field1";
        Object givenValue = "New Field 1";

        boolean success = ObjectUtils.setFieldValue(givenObject, givenFieldName, givenValue);

        Object expectedValue = "New Field 1";
        Object actualValue = testObject.getField1();
        assertTrue(success);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void givenObjectAndFieldNameAndNullValue_whenSetValueCalled_thenShouldSetToRightValue() {
        Object givenObject = testObject;
        String givenFieldName = "field1";

        boolean success = ObjectUtils.setFieldValue(givenObject, givenFieldName, value -> null);

        Object actualValue = testObject.getField1();
        assertTrue(success);
        assertNull(actualValue);
    }

}
