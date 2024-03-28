package io.siliconsavannah.employeemanager.service;

import io.siliconsavannah.employeemanager.dto.EmployeeDto;
import io.siliconsavannah.employeemanager.mapper.EmployeeMapper;
import io.siliconsavannah.employeemanager.model.Employee;
import io.siliconsavannah.employeemanager.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public Mono<EmployeeDto> createEmployee(Mono<EmployeeDto> employeeDto){

        return employeeDto
                .map(EmployeeMapper.mapper::dtoToEntity)
                .map(employeeRepo::save)
                .map(EmployeeMapper.mapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }
    public Flux<EmployeeDto> readAllEmployees(){
        return Flux.fromStream(()->employeeRepo.findAll().stream())
                .map(EmployeeMapper.mapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<EmployeeDto> updateEmployee(Mono<EmployeeDto> employeeDto){
        return employeeDto
                .map(dto->{
                    Employee entity = employeeRepo.findEmployeeById(dto.id()).get();
                    if (dto.name()!= null) entity.setName(dto.name());
                    if (dto.email()!= null)entity.setEmail(dto.email());
                    if (dto.title()!= null)entity.setTitle(dto.title());
                    if (dto.phone()!= null)entity.setPhone(dto.phone());
                    if (dto.imageUrl()!= null)entity.setImageUrl(dto.imageUrl());
                    return employeeRepo.save(entity);
                })
                .map(EmployeeMapper.mapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }
    public Mono<Void> deleteEmployee(int id){
        return Mono.fromRunnable(()->{employeeRepo.deleteEmployeeById(id) ;})
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<EmployeeDto> findEmployeeById(int id){
        return Mono.fromSupplier(()->employeeRepo.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(EmployeeMapper.mapper::entityToDto)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
