package com.hashkart.product.service.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hashkart.product.service.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;

}
