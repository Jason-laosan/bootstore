package org.bookstore;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bookstrore.controller.CartController;
import org.bookstrore.entity.Book;
import org.bookstrore.entity.CartItem;
import org.bookstrore.service.BookService;
import org.bookstrore.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @MockBean
    private BookService bookService;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("The Great Gatsby");
        book1.setAuthor("F. Scott Fitzgerald");
        book1.setPrice(12.99);
        book1.setCategory("Novel");

        ObjectMapper objectMapper = new ObjectMapper();
        CartItem cartItem1 = new CartItem();
        cartItem1.setBook(objectMapper.writeValueAsString(book1));
        cartItem1.setQuantity(2);

        when(bookService.addBook(book1)).thenReturn(book1);
        when(cartService.addToCart(1L, 2)).thenReturn(cartItem1);
        when(cartService.getCartItems()).thenReturn(List.of(cartItem1));
        when(cartService.getTotalPrice()).thenReturn(25.98);
    }

    @Test
    void shouldAddToCart() throws Exception {
        mockMvc.perform(post("/api/cart?bookId=1&quantity=2"))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldGetCartItems() throws Exception {
        mockMvc.perform(get("/api/cart"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].book.title", is("The Great Gatsby")))
                .andExpect(jsonPath("$[0].quantity", is(2)));
    }

    @Test
    void shouldCalculateTotalPrice() throws Exception {
        mockMvc.perform(get("/api/cart/total-price"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(25.98)));
    }

}