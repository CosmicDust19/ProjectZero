package me.projectzero.infrastructure.serviceresponse.directors;

import lombok.experimental.UtilityClass;
import me.projectzero.infrastructure.enumeration.ResponseCode;
import me.projectzero.infrastructure.serviceresponse.ServiceResponseError;

@UtilityClass
public class ServiceResponseErrorDirector {

    public ServiceResponseError.ServiceResponseErrorBuilder buildDefault() {
        return ServiceResponseError.builder().message(ResponseCode.SUCCESS);
    }

}
