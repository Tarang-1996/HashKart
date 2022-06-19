package com.hashkart.inventory.service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hashkart.inventory.service.exceptions.NoCategoryFoundException;
import com.hashkart.inventory.service.model.Product;
import com.hashkart.inventory.service.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/all")
	public ResponseEntity<Object> getAllProductDetails() {
		return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getProductById(@PathVariable("id") Integer productId) {
		Optional<Product> optProduct = productService.getProductById(productId);
		return optProduct.<ResponseEntity<Object>>map(product -> new ResponseEntity<>(product, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>("Product not found", HttpStatus.UNPROCESSABLE_ENTITY));

	}

	@GetMapping("/name/{productName}/all")
	public ResponseEntity<Object> getProductByName(@PathVariable("productName") String productName) {
		return new ResponseEntity<>(productService.getProductByProductName(productName), HttpStatus.OK);
	}

	@GetMapping("/category/{category}/all")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("category") String category) throws NoCategoryFoundException
			 {

		List<Product> product = productService.getProductByCategory(category);
		return new ResponseEntity<List<Product>>(product, new HttpHeaders(), HttpStatus.OK);

	}

	@GetMapping("/{sortAttribute}/{sortOrder}")
	public ResponseEntity<List<Product>> getSortedProducts(@PathVariable("sortAttribute") String sortAttribute,
			@PathVariable("sortOrder") String sortOrder) {

		List<Product> EmptyProducts = new ArrayList<>();

		if (sortAttribute.equalsIgnoreCase("rating")) {
			List<Product> product = productService.getSortedProductByRating(sortOrder);
			return new ResponseEntity<List<Product>>(product, new HttpHeaders(), HttpStatus.OK);

		} else if (sortAttribute.equalsIgnoreCase("price")) {

			List<Product> product = productService.getSortedProductByRating(sortOrder);
			return new ResponseEntity<List<Product>>(product, new HttpHeaders(), HttpStatus.OK);

		}
		return new ResponseEntity<List<Product>>(EmptyProducts, new HttpHeaders(), HttpStatus.OK);

	}

	@GetMapping("/filter/{filterAttribute}")
	public ResponseEntity<List<Product>> getFilteredProducts(@PathVariable("filterAttribute") String filterAttribute,
			@RequestParam("start") Double start, @RequestParam("end") Double end) {

		List<Product> EmptyProducts = new ArrayList<>();

		if (filterAttribute.equalsIgnoreCase("rating")) {

			List<Product> product = productService.getFilteredProductByRating(start, end);
			return new ResponseEntity<List<Product>>(product, new HttpHeaders(), HttpStatus.OK);

		} else if (filterAttribute.equalsIgnoreCase("price")) {

			List<Product> product = productService.getFilteredProductByPrice(start, end);
			return new ResponseEntity<List<Product>>(product, new HttpHeaders(), HttpStatus.OK);

		}
		return new ResponseEntity<List<Product>>(EmptyProducts, new HttpHeaders(), HttpStatus.OK);

	}
}
