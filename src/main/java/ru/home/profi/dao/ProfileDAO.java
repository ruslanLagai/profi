package ru.home.profi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.home.profi.entity.Profile;
import ru.home.profi.entity.Roles;
import ru.home.profi.entity.WorkExperience;

import java.util.List;

/*
    Interface for working with Profile DB
 */
public interface ProfileDAO extends JpaRepository<Profile, Long> {

    Profile save(Profile profile);
    Roles save(Roles roles);
    Profile findByUsername(String username);
    Profile findByEmail(String email);

    //@Modifying(clearAutomatically = true)
    @Query("UPDATE Profile SET password = :password, username = :username, email = :email, firstName = :name, lastName = :lastName, \n" +
            "       middleName = :middleName, courses = :courses, languages = :lang, skills = :skills " +
            "WHERE id = :id")
    Profile update(@Param("password") String password, @Param("username") String username,
                   @Param("email") String email, @Param("name") String name, @Param("lastName") String lastName,
                   @Param("middleName") String middleName, @Param("courses") String courses, @Param("lang") String lang,
                   @Param("skills") String skills);
}
