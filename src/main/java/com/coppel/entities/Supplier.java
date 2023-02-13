package com.coppel.entities;

import com.coppel.entities.embedeed.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id", nullable = false)
    private Long id;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "company_name", length = 100, nullable = false)
    private String name;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "contact_name", length = 75, nullable = false)
    private String contactName;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "contact_title", length = 15, nullable = false)
    private String contactTitle;

    @Embedded
    private Address address;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "phone", length = 15, nullable = false)
    private String phone;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "fax", length = 30)
    private String fax;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "homepage", length = 70, nullable = false)
    private String homePage;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.PERSIST)
    private List<Product> products;
}
