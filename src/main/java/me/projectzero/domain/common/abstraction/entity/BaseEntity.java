package me.projectzero.domain.common.abstraction.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.service.entitylistener.CreateAuditableListener;
import me.projectzero.service.entitylistener.UpdateAuditableListener;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
@EntityListeners({CreateAuditableListener.class, UpdateAuditableListener.class})
public abstract class BaseEntity<I> implements Entity, Identifiable<I>, CreateAuditable, UpdateAuditable {

    @Column(name = "dt_created", nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    @Column(name = "dt_modified", insertable = false)
    protected LocalDateTime modifiedAt;

    @Column(name = "tx_creator", nullable = false, updatable = false, length = 0)
    protected String creator;

    @Column(name = "tx_modifier", insertable = false, length = 0)
    protected String modifier;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BaseEntity<?> baseEntity = (BaseEntity<?>) o;
        return getId() != null && Objects.equals(getId(), baseEntity.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

}
