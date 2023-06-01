package me.projectzero.infrastructure.serviceresponse.directors;

import lombok.experimental.UtilityClass;
import me.projectzero.infrastructure.enumeration.ResponseCode;
import me.projectzero.infrastructure.serviceresponse.ServiceResponseSuccess;

@UtilityClass
public class ServiceResponseSuccessDirector {

    public ServiceResponseSuccess.ServiceResponseSuccessBuilder buildDefault() {
        return ServiceResponseSuccess.builder().message(ResponseCode.ERROR);
    }

}
