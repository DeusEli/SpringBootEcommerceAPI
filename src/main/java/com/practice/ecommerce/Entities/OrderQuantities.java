package com.practice.ecommerce.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_quantities")
public class OrderQuantities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_quantities_id", nullable = false)
    private long order_quantities_id;

    @ManyToOne(optional= false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    @Column(name = "quantities", nullable = false)
    private int quantities;

    @ManyToOne(optional= false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
