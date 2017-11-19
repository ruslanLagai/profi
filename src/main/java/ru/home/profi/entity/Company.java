package ru.home.profi.entity;

import org.hibernate.validator.constraints.Email;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Company {

    @Id
    @SequenceGenerator(name = "jpaSequence", sequenceName = "jpa_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
    private long id;

    @Column
    @Size(max = 60, message = "Company name must not exceed 60 characters")
    private String name;

    @Column
    @Email
    private String companyEmail;

    @Column
    private String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String email) {
        this.companyEmail = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
