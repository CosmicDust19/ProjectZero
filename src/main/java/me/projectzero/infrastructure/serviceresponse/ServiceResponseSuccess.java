package me.projectzero.infrastructure.serviceresponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.RequiredArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@RequiredArgsConstructor
public class ServiceResponseSuccess implements ServiceResponse {

    private final String code;

    private final String message;

    private final Object data;

}
