package com.practice.ecommerce.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private long address_id;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name="address_line_1", nullable = false)
    private String address_line_1;

    @Column(name="address_line_2", nullable = false)
    private String address_line_2;

    @Column(name="postal_code", nullable = false)
    private String postal_code;

    //This creates a foreign key column in the address table that references the user_id column in the user table
    //JoinColumn(name = "user_id") means that the foreign key column in the address table will be called "user_id"
    @ManyToOne(optional= false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Address() {
    }
}
