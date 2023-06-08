package me.projectzero.service.entitylistener;

import jakarta.persistence.PrePersist;
import me.projectzero.domain.common.abstraction.entity.CreateAuditable;
import me.projectzero.infrastructure.common.util.SecurityUtils;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDateTime;

public class CreateAuditableListener {

    @PrePersist
    public void prePersist(CreateAuditable target) {
        target.setCreatedAt(LocalDateTime.now());
        target.setCreator(SecurityUtils.getUsername().orElseThrow(() -> new AccessDeniedException("Cannot found the user performing the action.")));
    }

}
