package io.siliconsavannah.employeemanager.mapper;

import io.siliconsavannah.employeemanager.dto.EmployeeDto;
import io.siliconsavannah.employeemanager.model.Employee;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Mapper(componentModel = "spring")
public interface EmployeeMapper  {
    EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "imageUrl", source = "imageUrl")
    @Mapping(target = "employeeCode", source = "employeeCode")
    Employee mapDtoToEntity(EmployeeDto source);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "imageUrl", source = "imageUrl")
    @Mapping(target = "employeeCode", source = "employeeCode")
    EmployeeDto mapEntityToDto(Employee destination);
    EmployeeDto createEmployeeDTOWithoutId(Employee employee);
    default Employee dtoToEntity(EmployeeDto dto) {
        return mapDtoToEntity(dto);
    }
    default EmployeeDto entityToDto(Employee entity) {
        return mapEntityToDto(entity);
    }
}
