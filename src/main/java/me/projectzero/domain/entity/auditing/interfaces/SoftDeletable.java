package me.projectzero.domain.entity.auditing.interfaces;

import me.projectzero.domain.entity.auditing.listeners.SoftDeletableListener;
import me.projectzero.domain.entity.interfaces.Entity;

/**
 * Implementations of this interface will be audited by {@link SoftDeletableListener}
 */
public interface SoftDeletable extends Entity {

    Boolean getDeleted();

    void setDeleted(Boolean deleted);

}
