package me.projectzero.domain.entity.auditing.listeners;

import jakarta.persistence.PrePersist;
import me.projectzero.domain.entity.auditing.interfaces.SoftDeletable;

/**
 * Listener of the entities of type {@link SoftDeletable}
 */
public class SoftDeletableListener {

    @PrePersist
    public void prePersist(SoftDeletable target) {
        target.setDeleted(false);
    }

}
