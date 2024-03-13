package com.tutorial.apidemo.Springboot.demo.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Product {
    // this is "primary key"
    @Id
    @GeneratedValue // auto
    private UUID id;
    private String productName;
    @Column(name = "y")
    private int year;
    private Double price;
    private String url;

    // default contructor

    public Product() {
    }

    public Product(String productName, int year, Double price, String url) {
        this.productName = productName;
        this.year = year;
        this.price = price;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getYear() {
        return year;
    }

    public Double getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
