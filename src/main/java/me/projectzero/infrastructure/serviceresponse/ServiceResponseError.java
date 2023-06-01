package me.projectzero.infrastructure.serviceresponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponseError implements ServiceResponse {

    private final String code;

    private final String message;

    private final String type;

    private final Object data;

    private final List<ValidationError> validation;

    public static ServiceResponseErrorBuilder builder() {
        return new ServiceResponseErrorBuilder();
    }

    @Slf4j
    public static class ServiceResponseErrorBuilder extends ServiceResponseBuilder {

        private String type;

        private List<ValidationError> validation;

        public ServiceResponseErrorBuilder throwable(Throwable throwable) {
            this.type = throwable.getRootCause().getClass().getName();
            return this;
        }

        public ServiceResponseErrorBuilder validation(List<ValidationError> validation) {
            this.validation = validation;
            return this;
        }

        public ServiceResponseError build() {
            setIfNullMessageByCode();
            return new ServiceResponseError(code, message, type, data, validation);
        }

    }

}
