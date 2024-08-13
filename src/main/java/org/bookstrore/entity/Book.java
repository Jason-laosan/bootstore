package org.bookstrore.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@io.swagger.v3.oas.annotations.media.Schema(description = "书籍信息")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @io.swagger.v3.oas.annotations.media.Schema(description = "书籍ID")
    private Long id;

    @io.swagger.v3.oas.annotations.media.Schema(description = "书籍标题")
    private String title;

    @io.swagger.v3.oas.annotations.media.Schema(description = "书籍作者")
    private String author;

    @io.swagger.v3.oas.annotations.media.Schema(description = "书籍价格")
    private double price;

    @io.swagger.v3.oas.annotations.media.Schema(description = "书籍分类")
    private String category;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}