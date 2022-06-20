package com.hashkart.cart.service.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hashkart.cart.service.model.Cart;
import com.hashkart.cart.service.model.Checkout;
import com.hashkart.cart.service.model.Item;
import com.hashkart.cart.service.repository.CartRepository;
import com.hashkart.cart.service.repository.ItemRepository;
import com.hashkart.cart.service.clients.InventoryService;
import com.hashkart.cart.service.entities.Product;


@Service
public class CartServices {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    InventoryService productService;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public void addItem(Integer userId, Integer productId, Integer quantity) {
        Optional<Cart> opCart = cartRepository.findByUserId(userId);
        Cart cart = opCart.orElseGet(Cart::new);

        List<Item> itemList = cart.getItemList() == null ? new ArrayList<>() : cart.getItemList();
        itemList.add(Item.builder().productId(productId).quantity(quantity).build());

        cart.setItemList(itemRepository.saveAll(itemList));
        cart.setUserId(userId);
        cartRepository.save(cart);
    }

    public Optional<Checkout> checkout(Integer userId) {
        Optional<Cart> optCart = cartRepository.findByUserId(userId);
        if(!optCart.isPresent()){
            return Optional.empty();
        }
        double totalPrice = 0.0;
        for (Item item : optCart.get().getItemList()) {
            Product product = productService.getProductById(item.getProductId());
            totalPrice += product.getPrice() * item.getQuantity();
        }
        return Optional.of(
                Checkout.builder().cart(optCart.get()).totalPrice(totalPrice).build());
    }
}
