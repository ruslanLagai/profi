package ru.home.profi.entity;

import org.hibernate.validator.constraints.Email;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Profile")
public class Profile {

    @Id
    @SequenceGenerator(name = "jpaSequence", sequenceName = "jpa_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    @Size(max = 20, message = "Username must not exceed 20 characters long")
    private String username;

    @Column(name = "firstName")
    @Size(max = 100)
    private String firstName;

    @Column(name = "lastName")
    @Size(max = 100)
    private String lastName;

    @Column(name = "middleName")
    @Size(max = 100)
    private String middleName;

    @Column(name = "pass")
    @Size(min = 5, max = 20, message = "Password must be between 6 and 20 characters long")
    private String password;

    @Column
    @Email
    private String email;

    @Column(name = "skills")
    private String skills;

    @Column(name = "languages")
    private String languages;

    @Column(name = "courses")
    private String courses;

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
