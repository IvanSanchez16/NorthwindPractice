package com.coppel.entities.embedeed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address implements Serializable {

    @Column(length = 150, nullable = false)
    private String address;

    @Column(length = 65, nullable = false)
    private String city;

    @Column(length = 75, nullable = false)
    private String region;

    @Column(name = "postal_code", length = 5, nullable = false)
    private String postalCode;

    @Column(length = 35, nullable = false)
    private String country;
}