package me.projectzero.infrastructure.serviceresponse;

public class ServiceResponseSuccessBuilder {

    private String code;

    private String message;

    private String detail;

    private Object data;

    public ServiceResponseSuccessBuilder code(String code) {
        this.code = code;
        return this;
    }

    public ServiceResponseSuccessBuilder message(String message) {
        this.message = message;
        return this;
    }

    public ServiceResponseSuccessBuilder detail(String detail) {
        this.detail = detail;
        return this;
    }

    public ServiceResponseSuccessBuilder data(Object data) {
        this.data = data;
        return this;
    }

    public ServiceResponseSuccess build() {
        return new ServiceResponseSuccess(code, message, data);
    }

}
