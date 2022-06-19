package com.hashkart.product.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hashkart.product.service.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

}
