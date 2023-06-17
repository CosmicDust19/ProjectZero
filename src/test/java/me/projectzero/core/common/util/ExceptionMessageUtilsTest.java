package me.projectzero.core.common.util;

import org.junit.jupiter.api.Test;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExceptionMessageUtilsTest {

    @Test
    void givenHttpRequestMethodNotSupportedExceptionWith3SupportedMethods_whenBuildSupportedHttpMethodsMessage_thenReturnCorrectMessage() {
        HttpRequestMethodNotSupportedException given = new HttpRequestMethodNotSupportedException("GET", List.of("POST", "PUT", "DELETE"));
        String actualMessage = ExceptionMessageUtils.buildSupportedHttpMethodsMessage(given);
        String expectedMessage = "'GET' method is not supported for this request. Supported methods are 'POST', 'PUT' and 'DELETE'.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenHttpRequestMethodNotSupportedExceptionWith2SupportedMethods_whenBuildSupportedHttpMethodsMessage_thenReturnCorrectMessage() {
        HttpRequestMethodNotSupportedException given = new HttpRequestMethodNotSupportedException("GET", List.of("POST", "PUT"));
        String actualMessage = ExceptionMessageUtils.buildSupportedHttpMethodsMessage(given);
        String expectedMessage = "'GET' method is not supported for this request. Supported methods are 'POST' and 'PUT'.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenHttpRequestMethodNotSupportedExceptionWith1SupportedMethods_whenBuildSupportedHttpMethodsMessage_thenReturnCorrectMessage() {
        HttpRequestMethodNotSupportedException given = new HttpRequestMethodNotSupportedException("GET", List.of("POST"));
        String actualMessage = ExceptionMessageUtils.buildSupportedHttpMethodsMessage(given);
        String expectedMessage = "'GET' method is not supported for this request. Supported method is 'POST'.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenHttpRequestMethodNotSupportedExceptionWithoutSupportedMethods_whenBuildSupportedHttpMethodsMessage_thenReturnCorrectMessage() {
        HttpRequestMethodNotSupportedException given = new HttpRequestMethodNotSupportedException("GET", (Collection<String>) null);
        String actualMessage = ExceptionMessageUtils.buildSupportedHttpMethodsMessage(given);
        String expectedMessage = "'GET' method is not supported for this request. And, there is no supported method for this request.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenNull_whenBuildSupportedHttpMethodsMessage_thenThrowNullPointer() {
        Exception exception = assertThrows(NullPointerException.class, () -> ExceptionMessageUtils.buildSupportedHttpMethodsMessage(null));
        String expectedMessage = "exception is marked non-null but is null";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}
