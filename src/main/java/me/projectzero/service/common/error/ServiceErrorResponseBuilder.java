package me.projectzero.service.common.error;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import me.projectzero.core.common.enumeration.ErrorCode;
import me.projectzero.core.common.helper.SpringHelper;
import me.projectzero.core.common.util.ThrowableUtils;
import me.projectzero.service.common.error.sub.ValidationError;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ServiceErrorResponseBuilder {

    private String type;

    private int code;

    private String message;

    private List<ValidationError> validation;

    private List<ServiceErrorResponse> errors;

    ServiceErrorResponseBuilder(Throwable throwable) {
        this.type = ThrowableUtils.getRootCause(throwable).getClass().getName();
    }

    ServiceErrorResponseBuilder(Class<? extends Throwable> type) {
        this.type = type.getName();
    }

    public ServiceErrorResponseBuilder code(@NonNull ErrorCode code) {
        this.code = code.getCode();
        return this;
    }

    public ServiceErrorResponseBuilder message(String message) {
        this.message = message;
        return this;
    }

    public ServiceErrorResponseBuilder message(@NonNull ErrorCode code, Object... args) {
        this.code = code.getCode();
        setMessageByCode(this.code, args);
        return this;
    }

    public ServiceErrorResponseBuilder validation(List<ValidationError> validation) {
        this.validation = validation;
        return this;
    }

    public ServiceErrorResponseBuilder errors(ServiceErrorResponse... errors) {
        this.errors = List.of(errors);
        return this;
    }

    public ServiceErrorResponseBuilder errors(List<ServiceErrorResponse> errors) {
        this.errors = errors;
        return this;
    }

    public ServiceErrorResponse build() {
        if (message == null) setMessageByCode(code);
        return new ServiceErrorResponse(type, code, message, validation, errors);
    }

    public ServiceErrorResponse wrap() {
        return new ServiceErrorResponseBuilder().errors(build()).build();
    }

    private void setMessageByCode(int code, Object... args) {
        message = SpringHelper.getMessage(String.valueOf(code), args);
    }

}
