package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Shirt {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shirtId;
    private int shirtSize;
    private String shirtColor;
    private double shirtPrice;
    private String shirtStyle;


    public Shirt() {
    }


    public int getShirtId() {
        return shirtId;
    }
    public void setShirtId(int shirtId) {
        this.shirtId = shirtId;
    }
    public int getShirtSize() {
        return shirtSize;
    }
    public void setShirtSize(int shirtSize) {
        this.shirtSize = shirtSize;
    }
    public String getShirtColor() {
        return shirtColor;
    }
    public void setShirtColor(String shirtColor) {
        this.shirtColor = shirtColor;
    }
    public double getShirtPrice() {
        return shirtPrice;
    }
    public void setShirtPrice(double shirtPrice) {
        this.shirtPrice = shirtPrice;
    }
    public String getShirtStyle() {
        return shirtStyle;
    }
    public void setShirtStyle(String shirtStyle) {
        this.shirtStyle = shirtStyle;
    }
    
}
