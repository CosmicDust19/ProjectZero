package me.projectzero.domain.entity.auditing.listeners;

import jakarta.persistence.PrePersist;
import me.projectzero.domain.entity.auditing.interfaces.CreateAuditable;
import me.projectzero.core.common.enumeration.ErrorCode;
import me.projectzero.core.common.helper.SpringHelper;
import me.projectzero.core.common.util.security.AuthenticationUtils;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDateTime;

/**
 * Listener of the entities of type {@link CreateAuditable}
 */
public class CreateAuditableListener {

    @PrePersist
    public void prePersist(CreateAuditable target) {
        target.setCreatedAt(LocalDateTime.now());
        target.setCreator(AuthenticationUtils.getUsername().orElseThrow(() ->
                new AccessDeniedException(SpringHelper.getMessage(ErrorCode.CANNOT_FIND_THE_USER_PERFORMING_THE_ACTION.getCode()))
        ));
    }

}
