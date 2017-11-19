package ru.home.profi.entity;

import javax.persistence.*;

/*
    User work experience entity. Id must be set implicitly according with the id of the Person when pushing to the DB
 */
@Entity
@Table(name = "experience")
public class WorkExperience {

    @Id
    @SequenceGenerator(name = "jpaSequence", sequenceName = "jpa_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
    @Column(name = "id")
    private long id;

    @Column(name = "year")
    private String year;

    @Column(name = "duty")
    private String duties;

    @Column(name = "email")
    private String email;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "orderId")
    private String orderId;

    public String getOrderId() { return orderId; }

    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getOccupation() {return occupation; }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }
}
