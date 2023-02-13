package com.coppel.mappers;

import com.coppel.dto.requests.EmployeeRequestDTO;
import com.coppel.dto.responses.EmployeeDTO;
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

    public static EmployeeDTO mapToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setTitle(employee.getTitle());
        employeeDTO.setTitleOfCourtesy(employee.getTitleOfCourtesy());

        employeeDTO.setAddress(employee.getAddress().getAddress());
        employeeDTO.setCountry(employee.getAddress().getCountry());
        employeeDTO.setCity(employee.getAddress().getCity());
        employeeDTO.setPostalCode(employee.getAddress().getPostalCode());
        employeeDTO.setRegion(employee.getAddress().getRegion());

        employeeDTO.setBirthDate(employee.getBirthDate());
        employeeDTO.setHireDate(employee.getHireDate());
        employeeDTO.setNotes(employee.getNotes());

        employeeDTO.setPhone(String.format("%s %s", employee.getExtension(), employee.getHomePhone()));

        String reportsTo;
        if (employee.getReportsTo() != null)
            reportsTo = String.format("%s %s", employee.getReportsTo().getFirstName(), employee.getReportsTo().getLastName());
        else
            reportsTo = "Nobody";
        employeeDTO.setReportsTo(reportsTo);

        return employeeDTO;
    }
}
