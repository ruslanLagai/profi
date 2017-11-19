package ru.home.profi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.home.profi.entity.Company;
import ru.home.profi.entity.Roles;

public interface CompanyDao extends JpaRepository<Company, Long> {

    Company save(Company company);
    Roles save(Roles roles);
}
