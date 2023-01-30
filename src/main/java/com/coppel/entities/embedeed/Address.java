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

    @Column(length = 150)
    private String address;

    @Column(length = 65)
    private String city;

    @Column(length = 75)
    private String region;

    @Column(length = 5)
    private String postalCode;

    @Column(length = 35)
    private String country;
}