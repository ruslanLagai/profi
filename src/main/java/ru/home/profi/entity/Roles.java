package ru.home.profi.entity;

import javax.persistence.*;

@Entity
@Table(name = "Roles")
public class Roles {
    public enum RoleName {
        ROLE_ADMIN, ROLE_EMPLOYER, ROLE_EMPLOYEE
    }

    @Id
    @SequenceGenerator(name = "jpaSequence", sequenceName = "jpa_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
    private long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @Column
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Roles() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

}
