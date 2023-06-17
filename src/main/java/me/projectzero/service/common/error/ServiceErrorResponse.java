package me.projectzero.service.common.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.projectzero.service.common.error.sub.ValidationError;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceErrorResponse {

    private final String type;

    private final int code;

    private final String message;

    private final List<ValidationError> validation;

    private final List<ServiceErrorResponse> errors;

    public static ServiceErrorResponseBuilder thrown(Throwable throwable) {
        return new ServiceErrorResponseBuilder(throwable);
    }

    public static ServiceErrorResponseBuilder thrown(Class<? extends Throwable> type) {
        return new ServiceErrorResponseBuilder(type);
    }

}
