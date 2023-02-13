package com.coppel.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "product_name", length = 125, nullable = false)
    private String name;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "quantity_per_unit", length = 20, nullable = false)
    private String quantityPerUnit;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "units_in_stock", nullable = false)
    private Short stock;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "units_on_order")
    private Short unitsOnOrder;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "reorder_level")
    private Short reorderLevel;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "discontinued", nullable = false)
    private Boolean discontinued;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
