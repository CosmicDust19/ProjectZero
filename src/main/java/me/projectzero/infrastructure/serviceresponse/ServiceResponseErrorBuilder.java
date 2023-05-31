package me.projectzero.infrastructure.serviceresponse;

public class ServiceResponseErrorBuilder {

    private String code;

    private String type;

    private String message;

    private String detail;

    private Object data;

    public ServiceResponseErrorBuilder code(String code) {
        this.code = code;
        return this;
    }

    public ServiceResponseErrorBuilder type(String type) {
        this.type = type;
        return this;
    }

    public ServiceResponseErrorBuilder message(String message) {
        this.message = message;
        return this;
    }

    public ServiceResponseErrorBuilder detail(String detail) {
        this.detail = detail;
        return this;
    }

    public ServiceResponseErrorBuilder data(Object data) {
        this.data = data;
        return this;
    }

    public ServiceResponseError build() {
        return new ServiceResponseError(code, type, message, detail, data);
    }

}
