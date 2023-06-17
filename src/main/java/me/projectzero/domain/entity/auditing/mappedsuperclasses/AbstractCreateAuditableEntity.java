package me.projectzero.domain.entity.auditing.mappedsuperclasses;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.domain.entity.auditing.interfaces.CreateAuditable;
import me.projectzero.domain.entity.auditing.interfaces.SoftDeletable;
import me.projectzero.domain.entity.auditing.listeners.CreateAuditableListener;

import java.time.LocalDateTime;

/**
 * Creation of the subclasses of this class will be audited and will also have properties of class {@link AbstractSoftDeletableEntity}.
 *
 * @see CreateAuditable
 * @see SoftDeletable
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
@EntityListeners(CreateAuditableListener.class)
public abstract class AbstractCreateAuditableEntity extends AbstractSoftDeletableEntity implements CreateAuditable, SoftDeletable {

    @Column(name = "dt_created", nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    @Column(name = "tx_creator", nullable = false, updatable = false)
    protected String creator;

}
