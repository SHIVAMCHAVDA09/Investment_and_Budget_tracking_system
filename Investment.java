package com.example.mcminiprojectjava.model;

public class Investment {
    private int id; // Added id field
    private String name;
    private double amount;
    private String date;
    private String type; // New field for investment type

    // Constructor with all parameters
    public Investment(int id, String name, double amount, String date, String type) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    // Constructor without id (optional)
    public Investment(String name, double amount, String date, String type) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    // Default constructor (optional)
    public Investment() {
    }

    // Getter and setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for amount
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Getter and setter for date
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Getter and setter for type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
