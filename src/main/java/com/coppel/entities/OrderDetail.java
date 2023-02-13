package com.coppel.entities;

import com.coppel.entities.embedeed.OrderDetailID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetail {

    @EmbeddedId
    @AttributeOverride(name = "orderId", column = @Column(name = "order_id"))
    @AttributeOverride(name = "productId", column = @Column(name = "product_id"))
    private OrderDetailID id;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "unit_price", nullable = false)
    private Integer unitPrice;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "quantity", nullable = false)
    private Short quantity;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "discount", nullable = false)
    private BigDecimal discount;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
}
