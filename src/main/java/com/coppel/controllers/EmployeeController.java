package com.coppel.controllers;

import com.coppel.dto.ApiResponseDTO;
import com.coppel.dto.ResponseDTO;
import com.coppel.dto.requests.EmployeeRequestDTO;
import com.coppel.entities.Employee;
import com.coppel.mappers.EmployeeMapper;
import com.coppel.services.EmployeeService;
import com.coppel.util.Meta;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<ResponseDTO>> getAll() {
        ResponseDTO responseDTO = new ResponseDTO();

        List<Employee> employeeList = service.findAll();

        responseDTO.setStatus( employeeList.size() == 0 ? (short)0 : (short)1 );
        responseDTO.setData( employeeList );

        return new ResponseEntity<>(new ApiResponseDTO<>(new Meta("OK", 200), responseDTO), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<ResponseDTO>> save(@RequestBody @Valid EmployeeRequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        Employee employee;

        if (requestDTO.getReportsTo() != null) {
            Optional<Employee> optionalReportsTo = service.findById(requestDTO.getReportsTo() );
            employee = optionalReportsTo.map(value -> EmployeeMapper.mapToEntity(requestDTO, value))
                    .orElseGet(() -> EmployeeMapper.mapToEntity(requestDTO));
        } else
            employee = EmployeeMapper.mapToEntity(requestDTO);

        service.save(employee);

        responseDTO.setStatus((short)1);
        responseDTO.setData(employee);

        return new ResponseEntity<>(new ApiResponseDTO<>(new Meta("OK", 200), responseDTO), HttpStatus.OK);
    }
}
