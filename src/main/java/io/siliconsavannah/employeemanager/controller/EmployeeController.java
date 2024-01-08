package io.siliconsavannah.employeemanager.controller;

import io.siliconsavannah.employeemanager.model.Employee;
import io.siliconsavannah.employeemanager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(employeeService.readAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployeebyId(@PathVariable("id") int id){
        return new ResponseEntity<>(employeeService.findEmployeeById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(Employee employee){
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee>updateEmployee(Employee employee){
        return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployeebyId(@PathVariable("id") int id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}