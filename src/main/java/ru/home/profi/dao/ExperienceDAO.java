package ru.home.profi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.home.profi.entity.WorkExperience;

import java.util.List;

/*
Interface to work with Work Experience database using Jpa
 */
public interface ExperienceDAO extends JpaRepository<WorkExperience, Long> {

    WorkExperience save(WorkExperience workExperience);
    List<WorkExperience> findAllByEmail(String email);

    @Query("UPDATE WorkExperience SET year = :year, duties = :duties, occupation = :occupation" +
            " WHERE email = :email AND orderId = :order")
    void update(@Param("year") String year, @Param("duties") String duties, @Param("occupation") String occupation,
                @Param("email") String email, @Param("order") String order);
}
