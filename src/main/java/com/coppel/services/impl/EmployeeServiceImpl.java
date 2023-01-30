package com.coppel.services.impl;

import com.coppel.entities.Employee;
import com.coppel.repositories.EmployeeRepository;
import com.coppel.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Employee> findById(long employeeId) {
        return repository.findById(employeeId);
    }

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee update(long employeeId, Employee employee) {
        return null;
    }

    @Override
    public void delete(Employee employee) {
        repository.delete(employee);
    }
}
