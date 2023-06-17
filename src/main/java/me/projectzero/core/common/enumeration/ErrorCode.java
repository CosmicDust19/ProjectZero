package me.projectzero.core.common.enumeration;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import me.projectzero.core.common.severitylevels.Error;
import me.projectzero.core.common.severitylevels.SeverityLevel;

/**
 * Codes of possible errors. All error codes have corresponding message
 * under resources/{@link messages} package
 */
@Getter
public enum ErrorCode {

    UNEXPECTED(0, Error.class),
    MISSING_SERVLET_REQUEST_PARAMETER(10000, Error.class),
    TYPE_MISMATCH(10001, Error.class),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED(10002, Error.class),
    MISSING_SERVLET_REQUEST_PART(10003, Error.class),
    METHOD_ARGUMENT_TYPE_MISMATCH(10004, Error.class),
    NO_HANDLER_FOUND(10005, Error.class),
    NESTED_RUNTIME(10006, Error.class),
    PROPERTY_REFERENCE(10007, Error.class),
    VALIDATION(10008, Error.class),
    NULL_POINTER(10009, Error.class),

    JWT(11000, Error.class),
    JWT_CLAIM_NOT_FOUND(11001, Error.class),
    JWT_CLAIM_MALFORMED(11002, Error.class),
    JWT_EXPIRED(11003, Error.class),
    ACCESS_IS_DENIED(11100, Error.class),
    WRONG_USERNAME(11101, Error.class),
    WRONG_PASSWORD(11102, Error.class),
    CANNOT_FIND_THE_USER_PERFORMING_THE_ACTION(11103, Error.class),

    DATA_INTEGRITY_VIOLATION(18000, Error.class),
    ENTITY_NOT_FOUND(18001, Error.class),

    FK_USER_ROLE_USER(20000, Error.class),
    FK_USER_ROLE_ROLE(20001, Error.class),
    FK_ROLE_PRIVILEGE_ROLE(20002, Error.class),
    FK_ROLE_PRIVILEGE_PRIVILEGE(20003, Error.class),

    UK_USER_USERNAME(23000, Error.class),
    UK_ROLE_NAME(23001, Error.class),
    UK_PRIVILEGE_NAME(23002, Error.class),
    UK_USER_ROLE_USER_ROLE(23003, Error.class),
    UK_ROLE_PRIVILEGE_ROLE_PRIVILEGE(23004, Error.class);

    @UtilityClass
    public class Validation {
        public final String REFERENCE = "{80000}";
        public final String UNIQUE = "{80001}";
        public final String MAX_COLLECTION_SIZE = "{80002}";
        public final String COMPARABLE_CHECK = "{80003}";
        public final String TCKN = "{80004}";
        public final String UUID = "{80005}";
    }

    private final int code;
    private final Class<? extends SeverityLevel> level;

    ErrorCode(int code, @NonNull Class<? extends SeverityLevel> level) {
        if (level.equals(SeverityLevel.class))
            throw new IllegalArgumentException("Only subclasses of SeverityLevel.class are accepted.");
        this.code = code;
        this.level = level;
    }

}