package com.coppel.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shippers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shipper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shippers_id", nullable = false)
    private Long id;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "company_name", length = 100, nullable = false)
    private String name;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "phone", length = 15, nullable = false)
    private String phone;
}
