package me.projectzero.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import me.projectzero.domain.entity.auditing.mappedsuperclasses.AbstractCreateAuditableEntity;
import me.projectzero.domain.entity.interfaces.IdentifiableEntity;
import me.projectzero.domain.common.valueobjects.DbConstraint;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@SQLDelete(sql = "UPDATE tb_role SET is_deleted = null WHERE id_role=?")
@Table(name = "tb_role", uniqueConstraints = @UniqueConstraint(columnNames = {"uq_name", "is_deleted"}, name = DbConstraint.UK.ROLE_NAME))
public class Role extends AbstractCreateAuditableEntity implements IdentifiableEntity<Integer> {

    @Id
    @Column(name = "id_role", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uq_name", nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 1000)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_role_privilege",
            joinColumns = @JoinColumn(name = "rf_role", nullable = false), foreignKey = @ForeignKey(name = DbConstraint.FK.ROLE_PRIVILEGE_ROLE),
            inverseJoinColumns = @JoinColumn(name = "rf_privilege", nullable = false), inverseForeignKey = @ForeignKey(name = DbConstraint.FK.ROLE_PRIVILEGE_PRIVILEGE),
            uniqueConstraints = @UniqueConstraint(name = DbConstraint.UK.ROLE_PRIVILEGE_ROLE_PRIVILEGE, columnNames = {"rf_role", "rf_privilege"})
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Privilege> privileges;

    @ManyToMany(mappedBy = "roles")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<User> users;

    public Role(Role role, boolean deep) {
        if (role == null) return;
        this.id = role.getId();
        this.name = role.getName();
        this.description = role.getDescription();
        this.deleted = role.getDeleted();
        this.creator = role.getCreator();
        this.createdAt = role.getCreatedAt();
        if (deep) {
            this.privileges = new HashSet<>();
            for (Privilege privilege : role.getPrivileges())
                privileges.add(new Privilege(privilege));
        }
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

