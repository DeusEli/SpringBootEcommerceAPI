package com.practice.ecommerce.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private long product_id;

    @Column(name = "product", nullable = false, unique = true)
    private String product;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="image", nullable = false)
    private String image;

    @ManyToOne(optional= false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true, optional = false)
    private Inventory inventory;

    public Product() {
    }
}
