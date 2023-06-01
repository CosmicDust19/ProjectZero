package me.projectzero.infrastructure.extensions.org.springframework.http.ResponseEntity.BodyBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import me.projectzero.infrastructure.serviceresponse.ServiceResponseError;
import me.projectzero.infrastructure.serviceresponse.ServiceResponseSuccess;
import org.springframework.http.ResponseEntity;

@Extension
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseBodyBuilderExtensions {

    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class Body {
        private ServiceResponseSuccess success;
        private ServiceResponseError error;
    }

    public static ResponseEntity<Object> body(@This ResponseEntity.BodyBuilder builder, ServiceResponseSuccess success) {
        return builder.body(Body.builder().success(success).build());
    }

    public static ResponseEntity<Object> body(@This ResponseEntity.BodyBuilder builder, ServiceResponseError error) {
        return builder.body(Body.builder().error(error).build());
    }

    public static ResponseEntity<Object> body(@This ResponseEntity.BodyBuilder builder, ServiceResponseSuccess success, ServiceResponseError error) {
        return builder.body(Body.builder().success(success).error(error).build());
    }

}
