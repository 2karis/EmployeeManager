package io.siliconsavannah.employeemanager.service;

import io.siliconsavannah.employeemanager.dto.EmployeeDto;
import io.siliconsavannah.employeemanager.exceptions.UserNotFoundException;
import io.siliconsavannah.employeemanager.mapper.EmployeeMapper;
import io.siliconsavannah.employeemanager.model.Employee;
import io.siliconsavannah.employeemanager.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee createEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<EmployeeDto> readAllEmployees(){
        return employeeRepo.findAll().stream().map(employeeMapper).collect(Collectors.toList());
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto){
        Employee employee = employeeRepo.findEmployeeById(employeeDto.id())
                .orElseThrow(() -> new UserNotFoundException("employee with id "+ employeeDto.id() +" not found"));
        employee.setName(employeeDto.name());
        employee.setEmail(employeeDto.email());
        employee.setTitle(employeeDto.title());
        employee.setPhone(employeeDto.phone());
        employee.setImageUrl(employeeDto.imageUrl());
        return Optional.of(employeeRepo.save(employee)).stream().map(employeeMapper).findFirst().get();
    }
    public void deleteEmployee(int id){
        employeeRepo.deleteEmployeeById(id);
    }

    public EmployeeDto findEmployeeById(int id){
        return employeeRepo.findEmployeeById(id).stream().map(employeeMapper).findAny()
                .orElseThrow(() -> new UserNotFoundException("employee with id "+ id +" not found"));
    }
}
