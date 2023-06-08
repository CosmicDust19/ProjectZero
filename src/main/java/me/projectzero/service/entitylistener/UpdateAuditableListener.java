package me.projectzero.service.entitylistener;

import jakarta.persistence.PreUpdate;
import me.projectzero.domain.common.abstraction.entity.UpdateAuditable;
import me.projectzero.infrastructure.common.util.SecurityUtils;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDateTime;

public class UpdateAuditableListener {

    @PreUpdate
    public void preUpdate(UpdateAuditable target) {
        target.setModifiedAt(LocalDateTime.now());
        target.setModifier(SecurityUtils.getUsername().orElseThrow(() -> new AccessDeniedException("Cannot found the user performing the action.")));
    }

}
