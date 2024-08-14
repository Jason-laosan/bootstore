package org.bookstrore.service;


import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bookstrore.entity.Book;
import org.bookstrore.entity.CartItem;
import org.bookstrore.repository.BookRepository;
import org.bookstrore.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CartService(CartItemRepository cartItemRepository, BookRepository bookRepository) {
        this.cartItemRepository = cartItemRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public CartItem addToCart(Long bookId, int quantity) throws JsonProcessingException {
        CartItem existingCartItem = cartItemRepository.findByBookId(bookId);
        if (existingCartItem != null) {
            existingCartItem.setQuantity(quantity);
            cartItemRepository.updateQuantity(bookId, quantity);
            return existingCartItem;
        }

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        CartItem cartItem = new CartItem();

        cartItem.setBook(JSONUtil.toJsonStr(book));
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }

    public double getTotalPrice() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        return cartItems.stream()
                .mapToDouble(item -> {
                    Book book = JSONUtil.toBean(item.getBook(), Book.class);
                    return book.getPrice() * item.getQuantity();
                })
                .sum();
    }

}