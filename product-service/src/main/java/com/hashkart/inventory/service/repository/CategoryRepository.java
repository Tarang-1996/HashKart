package com.hashkart.inventory.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hashkart.inventory.service.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
