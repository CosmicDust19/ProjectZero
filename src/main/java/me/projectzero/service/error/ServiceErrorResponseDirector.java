package me.projectzero.service.error;

import lombok.experimental.UtilityClass;
import me.projectzero.domain.enumeration.ErrorCode;

import java.rmi.UnexpectedException;

@UtilityClass
public class ServiceErrorResponseDirector {

    public ServiceErrorResponseBuilder unexpected() {
        return ServiceErrorResponse.thrown(UnexpectedException.class).message(ErrorCode.UNEXPECTED);
    }

}
