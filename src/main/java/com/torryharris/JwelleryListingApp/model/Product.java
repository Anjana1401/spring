package com.torryharris.JwelleryListingApp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    private Category category;
    private Double price;
    private Double weight;
    private String description;
    private String imageName;
    private int stock;


}
