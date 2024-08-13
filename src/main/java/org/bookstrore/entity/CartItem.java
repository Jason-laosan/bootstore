package org.bookstrore.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@io.swagger.v3.oas.annotations.media.Schema(description = "购物车项")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @io.swagger.v3.oas.annotations.media.Schema(description = "购物车项ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")

    @io.swagger.v3.oas.annotations.media.Schema(description = "书籍")
    private Book book;

    @io.swagger.v3.oas.annotations.media.Schema(description = "数量")
    private int quantity;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}