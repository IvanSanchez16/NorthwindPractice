package com.coppel.services;

import com.coppel.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAll();

    Optional<Employee> findById(long employeeId);

    Employee save(Employee employee);

    Employee update(long employeeId, Employee employee);

    void delete(Employee employee);
}
