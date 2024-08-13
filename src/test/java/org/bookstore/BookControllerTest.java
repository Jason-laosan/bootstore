package org.bookstore;


import org.bookstrore.controller.BookController;
import org.bookstrore.entity.Book;
import org.bookstrore.service.BookService;
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

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @BeforeEach
    void setUp() {
        Book book1 = new Book();
        book1.setTitle("The Great Gatsby");
        book1.setAuthor("F. Scott Fitzgerald");
        book1.setPrice(12.99);
        book1.setCategory("Novel");

        Book book2 = new Book();
        book2.setTitle("To Kill a Mockingbird");
        book2.setAuthor("Harper Lee");
        book2.setPrice(9.99);
        book2.setCategory("Novel");

        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));
        when(bookService.addBook(book1)).thenReturn(book1);
    }

    @Test
    void shouldReturnAllBooks() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("The Great Gatsby")))
                .andExpect(jsonPath("$[1].title", is("To Kill a Mockingbird")));
    }

    @Test
    void shouldAddNewBook() throws Exception {
        Book book = new Book();
        book.setTitle("1984");
        book.setAuthor("George Orwell");
        book.setPrice(14.99);
        book.setCategory("Science Fiction");

        mockMvc.perform(post("/api/books")
                        .contentType("application/json")
                        .content("{\"title\": \"1984\", \"author\": \"George Orwell\", \"price\": 14.99, \"category\": \"Science Fiction\"}"))
                .andExpect(status().isCreated());
    }

}