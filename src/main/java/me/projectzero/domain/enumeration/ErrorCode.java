package me.projectzero.domain.enumeration;

import lombok.NonNull;

public enum ErrorCode {

    UNEXPECTED(0, Type.ERROR),
    MISSING_SERVLET_REQUEST_PARAMETER(10000, Type.ERROR),
    TYPE_MISMATCH(10001, Type.ERROR),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED(10002, Type.ERROR),
    MISSING_SERVLET_REQUEST_PART(10003, Type.ERROR),
    METHOD_ARGUMENT_TYPE_MISMATCH(10004, Type.ERROR),
    NO_HANDLER_FOUND(10005, Type.ERROR),
    NESTED_RUNTIME(10006, Type.ERROR),
    PROPERTY_REFERENCE(10007, Type.ERROR),
    VALIDATION(10008, Type.ERROR),
    NULL_POINTER(10009, Type.ERROR),

    JWT(11000, Type.ERROR),
    JWT_CLAIM_NOT_FOUND(11001, Type.ERROR),
    JWT_CLAIM_MALFORMED(11002, Type.ERROR),
    JWT_EXPIRED(11003, Type.ERROR),
    ACCESS_IS_DENIED(11100, Type.ERROR),
    WRONG_USERNAME(11101, Type.ERROR),
    WRONG_PASSWORD(11102, Type.ERROR),

    DATA_INTEGRITY_VIOLATION(18000, Type.ERROR),
    ENTITY_NOT_FOUND(18001, Type.ERROR),

    FK_USER_ROLE_USER(20000, Type.ERROR),
    FK_USER_ROLE_ROLE(20001, Type.ERROR),
    FK_ROLE_PRIVILEGE_ROLE(20002, Type.ERROR),
    FK_ROLE_PRIVILEGE_PRIVILEGE(20003, Type.ERROR),

    UK_USER_USERNAME(23000, Type.ERROR),
    UK_ROLE_NAME(23001, Type.ERROR),
    UK_PRIVILEGE_NAME(23002, Type.ERROR),
    UK_USER_ROLE_USER_ROLE(23003, Type.ERROR),
    UK_ROLE_PRIVILEGE_ROLE_PRIVILEGE(23004, Type.ERROR),

    ;

    private final int code;
    private final Type type;

    ErrorCode(int code, @NonNull Type type) {
        this.code = code;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        ERROR, WARNING
    }

}