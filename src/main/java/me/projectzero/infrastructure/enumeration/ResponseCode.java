package me.projectzero.infrastructure.enumeration;

public enum ResponseCode {


    SUCCESS("1000"),
    FETCHED("1001"),
    SAVED("1002"),
    UPDATED("1003"),
    DELETED("1004"),
    ROLLED_BACK("1005"),


    ERROR("3000"),
    INVALID("3001"),
    ENTITY_NOT_FOUND("3002"),
    AUTH_ERROR("3003"),
    ACCESS_IS_DENIED("3004"),
    LOGIN_WRONG_USERNAME("3005"),
    MALFORMED_JWT_SCHEME("3006"),
    MALFORMED_JWT_USERNAME("3007"),
    MALFORMED_JWT_EXPIRATION_DATE("3008"),
    MALFORMED_JWT_USERNAME_NOT_FOUND("3009"),
    JWT_EXPIRED("3010"),
    MISSING_SERVLET_REQUEST_PARAMETER("3011"),
    TYPE_MISMATCH("3012"),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED("3013"),
    MISSING_SERVLET_REQUEST_PART("3014"),
    METHOD_ARGUMENT_TYPE_MISMATCH("3015"),
    NO_HANDLER_FOUND("3016"),
    NESTED_RUNTIME("3017"),
    PROPERTY_REFERENCE("3018"),
    VALIDATION_ERROR("3019"),


    ;


    private final String code;

    ResponseCode(String code) {
        this.code = code;
    }

    public String get() {
        return code;
    }

}