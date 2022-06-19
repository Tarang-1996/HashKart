package com.hashkart.inventory.service.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hashkart.inventory.service.exceptions.ProductNotExistsException;
import com.hashkart.inventory.service.model.Category;
import com.hashkart.inventory.service.model.Product;
import com.hashkart.inventory.service.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	public void createProduct(Product product, Category category) {
		Product product1 = new Product();
		product1.setDescription(product1.getDescription());
		product1.setName(product1.getName());
		product1.setCategory(product1.getCategory());
		product1.setPrice(product1.getPrice());
		productRepository.save(product1);
	}

	public Product getProduct(Product product) {
		Product product1 = new Product();
		product1.setDescription(product1.getDescription());
		product1.setName(product1.getName());
		product1.setCategory(product1.getCategory());
		product1.setPrice(product1.getPrice());
		product1.setId(product1.getId());
		return product1;
	}

	public List<Product> getAllProducts() {
		List<Product> allProducts = productRepository.findAll();

		List<Product> products = new ArrayList<>();
		for (Product product : allProducts) {
			products.add(getProduct(product));
		}
		return products;
	}

	public void updateProduct(Product product, Integer productId) throws Exception {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		// throw an exception if product does not exists
		if (!optionalProduct.isPresent()) {
			throw new Exception("product not present");
		}
		Product product1 = optionalProduct.get();
		product1.setDescription(product.getDescription());
		product1.setName(product.getName());
		product1.setPrice(product.getPrice());
		product1.setQuantity(product.getQuantity());
		product1.setCategory(product.getCategory());
		productRepository.save(product1);
	}

	public Product findById(Integer productId) throws ProductNotExistsException {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if (optionalProduct.isEmpty()) {
			throw new ProductNotExistsException("product id is invalid: " + productId);
		}
		return optionalProduct.get();
	}
}
