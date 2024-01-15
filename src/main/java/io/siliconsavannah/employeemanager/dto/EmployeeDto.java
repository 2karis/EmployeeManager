package io.siliconsavannah.employeemanager.dto;

public record EmployeeDto(
        int id,
        String name,
        String email,
        String title,
        String phone,
        String imageUrl,
        String employeeCode
) {


}
