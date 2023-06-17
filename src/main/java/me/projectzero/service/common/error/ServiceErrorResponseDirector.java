package me.projectzero.service.common.error;

import lombok.experimental.UtilityClass;
import me.projectzero.core.common.enumeration.ErrorCode;

import java.rmi.UnexpectedException;

@UtilityClass
public class ServiceErrorResponseDirector {

    public ServiceErrorResponseBuilder unexpected() {
        return ServiceErrorResponse.thrown(UnexpectedException.class).message(ErrorCode.UNEXPECTED);
    }

}
