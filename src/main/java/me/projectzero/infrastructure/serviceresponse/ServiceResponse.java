package me.projectzero.infrastructure.serviceresponse;

import manifold.ext.rt.api.Self;
import me.projectzero.infrastructure.enumeration.ResponseCode;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public interface ServiceResponse {

    abstract class ServiceResponseBuilder {

        protected String code;

        protected String message;

        protected Object data;

        public @Self ServiceResponseBuilder code(ResponseCode code) {
            this.code = code.get();
            return this;
        }

        public @Self ServiceResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public @Self ServiceResponseBuilder message(ResponseCode code, Object... args) {
            this.code = code.get();
            this.message = MessageSource.getSpringBean().getMessage(this.code, args, LocaleContextHolder.getLocale());
            return this;
        }

        public @Self ServiceResponseBuilder data(Object data) {
            this.data = data;
            return this;
        }

        protected void setIfNullMessageByCode() {
            if (message == null && code != null)
                this.message = MessageSource.getSpringBean().getMessage(this.code, null, LocaleContextHolder.getLocale());
        }

    }

}
