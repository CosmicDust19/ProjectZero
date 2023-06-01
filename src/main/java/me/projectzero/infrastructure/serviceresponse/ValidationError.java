package me.projectzero.infrastructure.serviceresponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationError {

    private final String field;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private final Object rejectedValue;

    private final String code;

    private final String message;

}