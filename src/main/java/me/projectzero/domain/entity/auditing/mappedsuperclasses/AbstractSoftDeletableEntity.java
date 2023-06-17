package me.projectzero.domain.entity.auditing.mappedsuperclasses;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.domain.entity.auditing.interfaces.SoftDeletable;
import me.projectzero.domain.entity.auditing.listeners.SoftDeletableListener;
import me.projectzero.domain.common.valueobjects.HibernateFilter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

/**
 * Soft delete feature will be added to the subclasses of this class, thus it can fetch undeleted or deleted entities by using the {@link HibernateFilter.SoftDelete} filter.
 * The entity's {@code deleted} field will be listened by {@link SoftDeletableListener}
 *
 * @implNote This entity can also be hard deleted by default, so its deletion process should be configured independently.
 * @see SoftDeletable
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
@FilterDef(name = HibernateFilter.SoftDelete.NAME, parameters = @ParamDef(name = HibernateFilter.SoftDelete.PARAM_0_NAME, type = Boolean.class))
@Filter(name = HibernateFilter.SoftDelete.NAME, condition = HibernateFilter.SoftDelete.CONDITION)
@EntityListeners(SoftDeletableListener.class)
public abstract class AbstractSoftDeletableEntity implements SoftDeletable {

    @Column(name = "is_deleted")
    protected Boolean deleted;

}
