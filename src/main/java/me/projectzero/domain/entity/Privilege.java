package me.projectzero.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import me.projectzero.domain.entity.auditing.mappedsuperclasses.AbstractCreateAuditableEntity;
import me.projectzero.domain.entity.interfaces.IdentifiableEntity;
import me.projectzero.domain.common.valueobjects.DbConstraint;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@SQLDelete(sql = "UPDATE tb_privilege SET is_deleted = null WHERE id_privilege=?")
@Table(name = "tb_privilege", uniqueConstraints = @UniqueConstraint(columnNames = {"uq_name", "is_deleted"}, name = DbConstraint.UK.PRIVILEGE_NAME))
public class Privilege extends AbstractCreateAuditableEntity implements IdentifiableEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_privilege", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "uq_name", nullable = false, length = 50)
    private String name;

    @Column(name = "tx_description", nullable = false, length = 1000)
    private String description;

    public Privilege(Privilege privilege) {
        if (privilege == null) return;
        this.id = privilege.getId();
        this.name = privilege.getName();
        this.description = privilege.getDescription();
        this.deleted = privilege.getDeleted();
        this.creator = privilege.getCreator();
        this.createdAt = privilege.getCreatedAt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        IdentifiableEntity<?> entity = (IdentifiableEntity<?>) o;
        return getId() != null && Objects.equals(getId(), entity.getId());
    }

    @Override
    public int hashCode() {
        if (getId() == null) return this.getClass().hashCode();
        return getId().hashCode();
    }

}
