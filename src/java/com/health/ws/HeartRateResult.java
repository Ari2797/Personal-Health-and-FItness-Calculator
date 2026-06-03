/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.health.ws;

/**
 *
 * @author AMI
 */
public class HeartRateResult {
    private int maxHeartRate;
    private int lightZoneMin;
    private int lightZoneMax;
    private int moderateZoneMin;
    private int moderateZoneMax;
    private int intenseZoneMin;
    private int intenseZoneMax;
    private int targetHeartRateMin;
    private int targetHeartRateMax;
    private String recommendation;
    private boolean error;
    private String errorMessage;
    
    // Getters and Setters
    public int getMaxHeartRate() { return maxHeartRate; }
    public void setMaxHeartRate(int maxHeartRate) { this.maxHeartRate = maxHeartRate; }
    
    public int getLightZoneMin() { return lightZoneMin; }
    public void setLightZoneMin(int lightZoneMin) { this.lightZoneMin = lightZoneMin; }
    
    public int getLightZoneMax() { return lightZoneMax; }
    public void setLightZoneMax(int lightZoneMax) { this.lightZoneMax = lightZoneMax; }
    
    public int getModerateZoneMin() { return moderateZoneMin; }
    public void setModerateZoneMin(int moderateZoneMin) { this.moderateZoneMin = moderateZoneMin; }
    
    public int getModerateZoneMax() { return moderateZoneMax; }
    public void setModerateZoneMax(int moderateZoneMax) { this.moderateZoneMax = moderateZoneMax; }
    
    public int getIntenseZoneMin() { return intenseZoneMin; }
    public void setIntenseZoneMin(int intenseZoneMin) { this.intenseZoneMin = intenseZoneMin; }
    
    public int getIntenseZoneMax() { return intenseZoneMax; }
    public void setIntenseZoneMax(int intenseZoneMax) { this.intenseZoneMax = intenseZoneMax; }
    
    public int getTargetHeartRateMin() { return targetHeartRateMin; }
    public void setTargetHeartRateMin(int targetHeartRateMin) { this.targetHeartRateMin = targetHeartRateMin; }
    
    public int getTargetHeartRateMax() { return targetHeartRateMax; }
    public void setTargetHeartRateMax(int targetHeartRateMax) { this.targetHeartRateMax = targetHeartRateMax; }
    
    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
    
    public boolean isError() { return error; }
    public void setError(boolean error) { this.error = error; }
    
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}