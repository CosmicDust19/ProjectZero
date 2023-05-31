package me.projectzero.infrastructure.serviceresponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.RequiredArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@RequiredArgsConstructor
public class ServiceResponseError implements ServiceResponse {

    private final String code;

    private final String type;

    private final String message;

    private final String detail;

    private final Object data;

}
