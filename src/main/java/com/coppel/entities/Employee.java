package com.coppel.entities;

import com.coppel.entities.embedeed.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long id;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "title", nullable = false)
    private String title;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "title_of_courtesy", nullable = false)
    private String titleOfCourtesy;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Embedded
    @AttributeOverride(name = "address", column = @Column(length = 80))
    @AttributeOverride(name = "postalCode", column = @Column(name = "postal_code"))
    private Address address;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "home_phone", nullable = false)
    private String homePhone;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "extension", nullable = false)
    private String extension;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "notes", nullable = false)
    private String notes;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "photo_path", nullable = false)
    private String photoPath;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "reports_to")
    @JsonIgnore
    private Employee reportsTo;

}