package me.projectzero.domain.entity.auditing.listeners;

import jakarta.persistence.PreUpdate;
import me.projectzero.domain.entity.auditing.interfaces.UpdateAuditable;
import me.projectzero.core.common.enumeration.ErrorCode;
import me.projectzero.core.common.helper.SpringHelper;
import me.projectzero.core.common.util.security.AuthenticationUtils;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDateTime;

/**
 * Listener of the entities of type {@link UpdateAuditable}
 */
public class UpdateAuditableListener {

    @PreUpdate
    public void preUpdate(UpdateAuditable target) {
        target.setModifiedAt(LocalDateTime.now());
        target.setModifier(AuthenticationUtils.getUsername().orElseThrow(() ->
                new AccessDeniedException(SpringHelper.getMessage(ErrorCode.CANNOT_FIND_THE_USER_PERFORMING_THE_ACTION.getCode()))
        ));
    }

}
