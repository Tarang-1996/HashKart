package com.hashkart.inventory.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hashkart.inventory.service.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
