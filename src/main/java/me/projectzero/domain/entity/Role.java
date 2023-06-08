package me.projectzero.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import me.projectzero.domain.common.abstraction.entity.BaseEntity;
import me.projectzero.domain.enumeration.RoleEnum;
import me.projectzero.domain.valueobject.DbConstraint;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_role", uniqueConstraints = @UniqueConstraint(columnNames = "uq_name", name = DbConstraint.UK.ROLE_NAME))
public class Role extends BaseEntity<Integer> {

    @Id
    @EqualsAndHashCode.Include
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

    public Role(RoleEnum role) {
        if (role == null) return;
        this.id = role.getId();
        this.name = role.getName();
        this.description = role.getDescription();
        this.privileges = null;
    }

}

