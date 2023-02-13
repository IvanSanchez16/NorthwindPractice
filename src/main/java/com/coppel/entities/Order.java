package com.coppel.entities;

import com.coppel.entities.embedeed.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "order_date", nullable = false)
    private LocalDateTime createdAt;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "required_date", nullable = false)
    private LocalDate requiredDate;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "shipped_date", nullable = false)
    private LocalDateTime shippedDate;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "freight", nullable = false)
    private BigDecimal freight;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ship_name", nullable = false)
    private String shipName;

    @Embedded
    @AttributeOverride(name = "address", column = @Column(name = "ship_address"))
    @AttributeOverride(name = "city", column = @Column(name = "ship_city"))
    @AttributeOverride(name = "region", column = @Column(name = "ship_region"))
    @AttributeOverride(name = "postalCode", column = @Column(name = "ship_postal_code"))
    @AttributeOverride(name = "country", column = @Column(name = "ship_country"))
    private Address shipAddress;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "ship_via", nullable = false)
    private Shipper shipper;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<OrderDetail> detail;
}
