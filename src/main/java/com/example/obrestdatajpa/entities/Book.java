package com.example.obrestdatajpa.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {
    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String litle;
    private String author;
    private Integer pages;
    private Double price;
    private LocalDate releaseDate;
    private Boolean online;

    // constructors
    public Book() {
    }

    public Book(Long id, String litle, String author, Integer pages, Double price, LocalDate releaseDate, Boolean online) {
        this.id = id;
        this.litle = litle;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.releaseDate = releaseDate;
        this.online = online;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLitle() {
        return litle;
    }

    public void setLitle(String litle) {
        this.litle = litle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    //toString
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", litle='" + litle + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                ", online=" + online +
                '}';
    }
}
