package io.siliconsavannah.employeemanager.mapper;

import io.siliconsavannah.employeemanager.dto.EmployeeDto;
import io.siliconsavannah.employeemanager.model.Employee;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EmployeeMapper implements Function<Employee, EmployeeDto> {
    @Override
    public EmployeeDto apply(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getTitle(),
                employee.getPhone(),
                employee.getImageUrl(),
                employee.getEmployeeCode()
                );
    }
}
