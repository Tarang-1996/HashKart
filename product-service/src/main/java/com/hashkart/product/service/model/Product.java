package com.hashkart.product.service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    private String name;
    private double price;
    private Integer quantity;
    private String description;
    
	public Product(Integer id, String name, double price, Integer quantity, String description) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	

}
