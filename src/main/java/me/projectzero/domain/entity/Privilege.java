package me.projectzero.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.domain.common.abstraction.entity.BaseEntity;
import me.projectzero.domain.enumeration.PrivilegeEnum;
import me.projectzero.domain.valueobject.DbConstraint;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_privilege", uniqueConstraints = @UniqueConstraint(columnNames = "uq_name", name = DbConstraint.UK.PRIVILEGE_NAME))
public class Privilege extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_privilege", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "uq_name", nullable = false, length = 50)
    private String name;

    @Column(name = "tx_description", nullable = false, length = 1000)
    private String description;

    public Privilege(PrivilegeEnum privilege) {
        if (privilege == null) return;
        this.id = privilege.getId();
        this.name = privilege.getName();
        this.description = privilege.getDescription();
    }

}
