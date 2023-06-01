package me.projectzero.infrastructure.serviceresponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponseSuccess implements ServiceResponse {

    private final String code;

    private final String message;

    private final Object data;

    public static ServiceResponseSuccessBuilder builder() {
        return new ServiceResponseSuccessBuilder();
    }

    public static class ServiceResponseSuccessBuilder extends ServiceResponseBuilder {

        public ServiceResponseSuccess build() {
            setIfNullMessageByCode();
            return new ServiceResponseSuccess(code, message, data);
        }

    }

}
