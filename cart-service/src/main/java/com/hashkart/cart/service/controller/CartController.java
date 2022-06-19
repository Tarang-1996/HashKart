package com.hashkart.cart.service.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hashkart.cart.service.entities.AddItemRequest;
import com.hashkart.cart.service.model.Checkout;
import com.hashkart.cart.service.services.CartServices;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartServices cartServices;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCart() {
        return new ResponseEntity<>(cartServices.getAllCarts(), HttpStatus.OK);
    }

    @PostMapping("/addItem")
    public ResponseEntity<Object> addItemToCart(@RequestBody AddItemRequest addItemRequest) {
        cartServices.addItem(addItemRequest.getUserId(), addItemRequest.getProductId(), addItemRequest.getQuantity());
        return ResponseEntity.ok("Added");
    }

    @GetMapping("/checkout")
    public ResponseEntity<Object> checkoutCart(@RequestParam(value = "userId", required = true)  Integer userId){
        Optional<Checkout> checkout = cartServices.checkout(userId);
        return checkout.<ResponseEntity<Object>>map(value ->
                new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>("No cart for the given customer", HttpStatus.NO_CONTENT));
    }
}




