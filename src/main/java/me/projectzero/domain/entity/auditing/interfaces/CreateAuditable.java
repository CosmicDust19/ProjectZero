package me.projectzero.domain.entity.auditing.interfaces;

import me.projectzero.domain.entity.auditing.listeners.CreateAuditableListener;
import me.projectzero.domain.entity.interfaces.Entity;

import java.time.LocalDateTime;

/**
 * Implementations of this interface will be audited by {@link CreateAuditableListener} for creation
 */
public interface CreateAuditable extends Entity {

    LocalDateTime getCreatedAt();

    String getCreator();

    void setCreatedAt(LocalDateTime createdAt);

    void setCreator(String creator);

}
