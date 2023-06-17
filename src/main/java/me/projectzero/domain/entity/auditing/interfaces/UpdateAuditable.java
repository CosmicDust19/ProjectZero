package me.projectzero.domain.entity.auditing.interfaces;

import me.projectzero.domain.entity.auditing.listeners.UpdateAuditableListener;
import me.projectzero.domain.entity.interfaces.Entity;

import java.time.LocalDateTime;

/**
 * Implementations of this interface will be audited by {@link UpdateAuditableListener} for modification
 */
public interface UpdateAuditable extends Entity {

    LocalDateTime getModifiedAt();

    String getModifier();

    void setModifiedAt(LocalDateTime modifiedAt);

    void setModifier(String modifier);

}
