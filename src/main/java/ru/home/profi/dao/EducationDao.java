package ru.home.profi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.home.profi.entity.Education;

import java.util.List;

/**
 * Interface for working with Education Table
 */
public interface EducationDao extends JpaRepository<Education, Long> {

    public Education save(Education education);
    public List<Education> findByEmail(String email);
}
