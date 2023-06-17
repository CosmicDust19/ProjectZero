package me.projectzero.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import me.projectzero.domain.entity.auditing.listeners.UserListener;
import me.projectzero.domain.entity.auditing.mappedsuperclasses.AbstractAuditableEntity;
import me.projectzero.domain.entity.interfaces.IdentifiableEntity;
import me.projectzero.domain.common.enumeration.RoleEnum;
import me.projectzero.domain.common.objectcache.RoleCache;
import me.projectzero.domain.common.valueobjects.DbConstraint;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@SQLDelete(sql = "UPDATE tb_user SET is_deleted = null WHERE id_user=?")
@Table(name = "tb_user", uniqueConstraints = @UniqueConstraint(columnNames = {"uq_username", "is_deleted"}, name = DbConstraint.UK.USER_USERNAME))
@EntityListeners(UserListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends AbstractAuditableEntity implements IdentifiableEntity<UUID> {

    @Id
    @GeneratedValue
    @Column(name = "id_user", updatable = false, nullable = false)
    protected UUID id;

    @Column(name = "uq_username", nullable = false)
    protected String username;

    @Column(name = "tx_password", nullable = false, length = 60)
    protected String password;

    @Column(name = "is_enabled", nullable = false)
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

    @Transient
    public void addRoles(@NonNull RoleEnum... roles) {
        this.roles = Objects.requireNonNullElseGet(this.roles, HashSet::new);
        this.roles.addAll(RoleCache.get(roles));
    }

    @Transient
    public org.springframework.security.core.userdetails.User getUserDetails() {
        return new org.springframework.security.core.userdetails.User(username, password, enabled, true, true, true, getGrantedAuthorities());
    }

    @Transient
    public Set<GrantedAuthority> getGrantedAuthorities() {
        if (roles == null) return new HashSet<>();
        Set<GrantedAuthority> set = new HashSet<>();
        for (Role role : roles) {
            set.add(new SimpleGrantedAuthority(role.getName()));
            for (Privilege privilege : role.getPrivileges()) {
                set.add(new SimpleGrantedAuthority(privilege.getName()));
            }
        }
        return set;
    }
}

