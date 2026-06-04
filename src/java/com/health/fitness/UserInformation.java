/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fitness.service;

/**
 *
 * @author mrsya
 */
public class UserInformation {
    private String name;
    private String icNumber;
    private String gender; // "male" or "female"
    private double weight; // in kg
    private double height; // in meters

    // Default constructor (Required for JAX-WS serialization)
    public UserInformation() {}

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIcNumber() { return icNumber; }
    public void setIcNumber(String icNumber) { this.icNumber = icNumber; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }
}
