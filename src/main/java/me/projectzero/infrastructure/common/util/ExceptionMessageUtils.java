package me.projectzero.infrastructure.common.util;

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
        if (supportedMethods != null) {
            int size = supportedMethods.size();
            builder.append('\'').append(exception.getMethod()).append('\'').append(" method is not supported for this request. Supported method");
            if (size == 1) builder.append(" is ");
            else builder.append("s are ");
            short counter = 0;
            for (HttpMethod httpMethod : supportedMethods) {
                if (counter == size - 1 && size != 1) builder.append(" and ");
                else if (counter != 0 && size != 1) builder.append(", ");
                builder.append('\'').append(httpMethod).append('\'');
                counter++;
            }
        } else {
            builder.append("There is no supported method for this request.");
        }
        return builder.toString();
    }

}
