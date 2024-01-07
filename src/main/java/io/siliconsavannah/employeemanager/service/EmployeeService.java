package io.siliconsavannah.employeemanager.service;

import io.siliconsavannah.employeemanager.exceptions.UserNotFoundException;
import io.siliconsavannah.employeemanager.model.Employee;
import io.siliconsavannah.employeemanager.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee createEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> readAllEmployees(){
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee){
        return employeeRepo.save(employee);
    }
    public void deleteEmployee(int id){
        employeeRepo.deleteEmployeebyId(id);
    }

    public Employee findEmployeeById(int id){
        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("employee with id "+ id +" not found"));
    }
}
