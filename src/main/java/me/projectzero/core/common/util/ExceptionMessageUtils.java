package me.projectzero.core.common.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpMethod;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.Set;

@UtilityClass
public class ExceptionMessageUtils {

    public String buildSupportedHttpMethodsMessage(@NonNull HttpRequestMethodNotSupportedException exception) {
        Set<HttpMethod> supportedMethods = exception.getSupportedHttpMethods();
        final StringBuilder builder = new StringBuilder();
        builder.append('\'').append(exception.getMethod()).append('\'').append(" method is not supported for this request. ");
        if (supportedMethods != null) {
            int size = supportedMethods.size();
            if (size == 1) {
                builder.append("Supported method is ");
            } else {
                builder.append("Supported methods are ");
            }
            short counter = 0;
            for (HttpMethod httpMethod : supportedMethods) {
                if (counter == size - 1 && size != 1) {
                    builder.append(" and ");
                } else if (counter != 0 && size != 1) {
                    builder.append(", ");
                }
                builder.append('\'').append(httpMethod).append('\'');
                counter++;
            }
            builder.append(".");
        } else {
            builder.append("And, there is no supported method for this request.");
        }
        return builder.toString();
    }

}
