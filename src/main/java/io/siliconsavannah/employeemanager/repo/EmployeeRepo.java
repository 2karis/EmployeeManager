package io.siliconsavannah.employeemanager.repo;

import io.siliconsavannah.employeemanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    void deleteEmployeebyId(int id);

    Optional<Employee> findEmployeeById(int id);
}
