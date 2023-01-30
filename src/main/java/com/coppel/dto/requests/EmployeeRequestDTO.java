package com.coppel.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeRequestDTO {

    @NotNull(message = "El nombre del empleado es obligatorio")
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String title;

    @NotNull
    private String titleOfCourtesy;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private LocalDate hireDate;

    @NotNull
    private String address;

    @NotNull
    private String city;

    @NotNull
    private String region;

    @NotNull
    private String postalCode;

    @NotNull
    private String country;

    @NotNull
    private String homePhone;

    @NotNull
    private String extension;

    @NotNull
    private String notes;

    @NotNull
    private String photoPath;

    private Long reportsTo;

}
