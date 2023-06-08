package me.projectzero.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.domain.common.abstraction.entity.BaseEntity;
import me.projectzero.domain.enumeration.RoleEnum;
import me.projectzero.domain.valueobject.DbConstraint;
import me.projectzero.service.entitylistener.UserListener;
import org.hibernate.annotations.ColumnDefault;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_user", uniqueConstraints = @UniqueConstraint(columnNames = "uq_username", name = DbConstraint.UK.USER_USERNAME))
@EntityListeners(UserListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity<UUID> {

    @Id
    @GeneratedValue
    @Column(name = "id_user", updatable = false, nullable = false, length = 16)
    protected UUID id;

    @Column(name = "uq_username", nullable = false)
    protected String username;

    @Column(name = "tx_password", nullable = false, length = 60)
    protected String password;

    @ColumnDefault("true")
    @Column(name = "is_enabled", insertable = false, nullable = false)
    protected Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_user_role",
            joinColumns = @JoinColumn(name = "rf_user", nullable = false), foreignKey = @ForeignKey(name = DbConstraint.FK.USER_ROLE_USER),
            inverseJoinColumns = @JoinColumn(name = "rf_role", nullable = false), inverseForeignKey = @ForeignKey(name = DbConstraint.FK.USER_ROLE_ROLE),
            uniqueConstraints = @UniqueConstraint(name = DbConstraint.UK.USER_ROLE_USER_ROLE, columnNames = {"rf_user", "rf_role"})
    )
    protected Set<Role> roles;

    public User(String username) {
        this.username = username;
    }

    public void addRoles(RoleEnum... roles) {
        this.roles = Objects.requireNonNullElseGet(this.roles, HashSet::new);
        for (RoleEnum role : roles) {
            this.roles.add(new Role(role));
        }
    }
}

