package com.hashkart.inventory.service.model;

import com.hashkart.inventory.service.model.Category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "category_id")
	Category category;

	public Product(String name, double price, Integer quantity, String description) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
