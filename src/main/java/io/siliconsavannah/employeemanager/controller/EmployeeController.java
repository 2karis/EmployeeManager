package io.siliconsavannah.employeemanager.controller;

import io.siliconsavannah.employeemanager.dto.EmployeeDto;
import io.siliconsavannah.employeemanager.model.Employee;
import io.siliconsavannah.employeemanager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin(origins ={"http://localhost:4200", "http://localhost:80"})
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;

    @GetMapping("/readall")
    public Flux<EmployeeDto> getAllEmployees(){
        return employeeService.readAllEmployees();
    }


    @GetMapping("/read/{id}")
    public Mono<ResponseEntity<EmployeeDto>> getEmployee(@PathVariable("id") int id){
        return employeeService.findEmployeeById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<EmployeeDto>> createEmployee(@RequestBody Mono<EmployeeDto> employeeDto){
        return employeeService.createEmployee(employeeDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<EmployeeDto>> updateEmployee(@RequestBody Mono<EmployeeDto> employeeDto){
        return employeeService.updateEmployee(employeeDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteEmployee(@PathVariable("id") int id){
        return employeeService.deleteEmployee(id);
    }
}
