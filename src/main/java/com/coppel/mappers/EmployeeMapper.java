package com.coppel.mappers;

import com.coppel.dto.requests.EmployeeRequestDTO;
import com.coppel.entities.Employee;
import com.coppel.entities.embedeed.Address;

public class EmployeeMapper {

    private EmployeeMapper() {throw new IllegalStateException("Utility class");}

    public static Employee mapToEntity(EmployeeRequestDTO request) {
        return mapToEntity(request, null);
    }

    public static Employee mapToEntity(EmployeeRequestDTO request, Employee reportsTo) {
        Employee employee = new Employee();

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setTitle(request.getTitle());
        employee.setTitleOfCourtesy(request.getTitleOfCourtesy());
        employee.setBirthDate(request.getBirthDate());
        employee.setHireDate(request.getHireDate());

        Address address = new Address();
        address.setAddress(request.getAddress());
        address.setCity(request.getCity());
        address.setRegion(request.getRegion());
        address.setCountry(request.getCountry());
        address.setPostalCode(request.getPostalCode());

        employee.setAddress(address);
        employee.setHomePhone(request.getHomePhone());
        employee.setExtension(request.getExtension());
        employee.setNotes(request.getNotes());
        employee.setPhotoPath(request.getPhotoPath());

        employee.setReportsTo(reportsTo);

        return employee;
    }
}
