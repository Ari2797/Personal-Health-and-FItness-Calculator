/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.health.ws;

/**
 *
 * @author AMI
 */
public class WeightLossResult {
    private double predictedWeightLossKg;
    private double totalCalorieDeficit;
    private double dailyCalorieDeficit;
    private String weightChangeStatus;
    private String recommendation;
    private boolean error;
    private String errorMessage;
    
    // Getters and Setters
    public double getPredictedWeightLossKg() { return predictedWeightLossKg; }
    public void setPredictedWeightLossKg(double predictedWeightLossKg) { this.predictedWeightLossKg = predictedWeightLossKg; }
    
    public double getTotalCalorieDeficit() { return totalCalorieDeficit; }
    public void setTotalCalorieDeficit(double totalCalorieDeficit) { this.totalCalorieDeficit = totalCalorieDeficit; }
    
    public double getDailyCalorieDeficit() { return dailyCalorieDeficit; }
    public void setDailyCalorieDeficit(double dailyCalorieDeficit) { this.dailyCalorieDeficit = dailyCalorieDeficit; }
    
    public String getWeightChangeStatus() { return weightChangeStatus; }
    public void setWeightChangeStatus(String weightChangeStatus) { this.weightChangeStatus = weightChangeStatus; }
    
    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
    
    public boolean isError() { return error; }
    public void setError(boolean error) { this.error = error; }
    
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}