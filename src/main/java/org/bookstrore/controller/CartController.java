package org.bookstrore.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bookstrore.entity.CartItem;
import org.bookstrore.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "购物车接口", description = "购物车接口")
@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @Operation(summary = "添加到购物车")
    @PostMapping
    public ResponseEntity<CartItem> addToCart(@RequestParam Long bookId, @RequestParam int quantity){
        CartItem cartItem = null;
        try {
            cartItem = cartService.addToCart(bookId, quantity);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.METHOD_FAILURE);
        }
        return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
    }

    @Operation(summary = "获取购物车")
    @GetMapping
    public List<CartItem> getCartItems() {
        return cartService.getCartItems();
    }

    @Operation(summary = "获取购物车总价")
    @GetMapping("/total-price")
    public double getTotalPrice() {
        return cartService.getTotalPrice();
    }

}