package me.projectzero.domain.entity.auditing.mappedsuperclasses;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.domain.entity.auditing.interfaces.SoftDeletable;
import me.projectzero.domain.entity.auditing.interfaces.UpdateAuditable;
import me.projectzero.domain.entity.auditing.listeners.UpdateAuditableListener;

import java.time.LocalDateTime;

/**
 * Modification of the subclasses of this class will be listened and will also have properties of class {@link AbstractSoftDeletableEntity}.
 *
 * @see UpdateAuditable
 * @see SoftDeletable
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
@EntityListeners(UpdateAuditableListener.class)
public abstract class AbstractUpdateAuditableEntity extends AbstractSoftDeletableEntity implements UpdateAuditable, SoftDeletable {

    @Column(name = "dt_modified", insertable = false)
    protected LocalDateTime modifiedAt;

    @Column(name = "tx_modifier", insertable = false)
    protected String modifier;

}
