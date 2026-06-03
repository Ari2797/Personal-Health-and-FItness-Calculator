/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.health.ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author AMI
 */
@WebService(serviceName = "HealthFitnessService")
public class HealthFitnessService {

    // ==================== MODULE 3.4: PREDICTED WEIGHT LOSS ====================
    
    @WebMethod(operationName = "calculatePredictedWeightLoss")
    public WeightLossResult calculatePredictedWeightLoss(
            @WebParam(name = "dailyCalorieIntake") double dailyCalorieIntake,
            @WebParam(name = "dailyCaloriesBurned") double dailyCaloriesBurned,
            @WebParam(name = "durationDays") int durationDays) {
        
        WeightLossResult result = new WeightLossResult();
        
        // Input validation
        if (dailyCalorieIntake < 0 || dailyCaloriesBurned < 0 || durationDays <= 0) {
            result.setError(true);
            result.setErrorMessage("Invalid input: Calorie values must be positive, duration must be > 0 days");
            return result;
        }
        
        // Calculate calorie deficit per day
        double calorieDeficit = dailyCaloriesBurned - dailyCalorieIntake;
        
        // Total calorie deficit over duration
        double totalCalorieDeficit = calorieDeficit * durationDays;
        
        // 7700 kcal ≈ 1 kg of body weight
        double predictedWeightLossKg = totalCalorieDeficit / 7700;
        
        // Determine weight change status
        String status;
        if (predictedWeightLossKg > 0) {
            status = "LOSS";
        } else if (predictedWeightLossKg < 0) {
            status = "GAIN";
        } else {
            status = "MAINTAIN";
        }
        
        result.setPredictedWeightLossKg(Math.round(predictedWeightLossKg * 100.0) / 100.0);
        result.setTotalCalorieDeficit(Math.round(totalCalorieDeficit));
        result.setDailyCalorieDeficit(Math.round(calorieDeficit));
        result.setWeightChangeStatus(status);
        result.setError(false);
        
        // Generate recommendation
        result.setRecommendation(generateWeightLossRecommendation(predictedWeightLossKg, calorieDeficit));
        
        return result;
    }
    
    private String generateWeightLossRecommendation(double predictedLoss, double dailyDeficit) {
        if (predictedLoss > 0.5) {
            return "Excellent progress! Maintain this calorie deficit for consistent results. ⚠️ Keep deficit between 300-700 kcal/day for healthy weight loss.";
        } else if (predictedLoss > 0) {
            return "Good start! To accelerate results, consider increasing physical activity or adjusting calorie intake slightly.";
        } else if (predictedLoss < -0.5) {
            return "Warning: You're likely gaining weight. Reduce calorie intake or increase exercise for better results.";
        } else if (predictedLoss < 0) {
            return "Minor weight gain detected. Try adding a 30-minute walk daily to balance calories.";
        } else {
            return "Weight maintenance detected. To lose weight, aim for 300-500 kcal daily deficit.";
        }
    }
    
    // ==================== MODULE 3.5: HEART RATE CALCULATION ====================
    
    @WebMethod(operationName = "calculateHeartRateZones")
    public HeartRateResult calculateHeartRateZones(
            @WebParam(name = "age") int age) {
        
        HeartRateResult result = new HeartRateResult();
        
        // Input validation
        if (age < 1 || age > 120) {
            result.setError(true);
            result.setErrorMessage("Invalid age. Please enter age between 1 and 120 years.");
            return result;
        }
        
        // Formula: Max Heart Rate = 220 - Age
        int maxHeartRate = 220 - age;
        
        // Calculate target zones as percentages of max heart rate
        int lightMin = (int) Math.round(maxHeartRate * 0.50);
        int lightMax = (int) Math.round(maxHeartRate * 0.60);
        
        int moderateMin = (int) Math.round(maxHeartRate * 0.60);
        int moderateMax = (int) Math.round(maxHeartRate * 0.75);
        
        int intenseMin = (int) Math.round(maxHeartRate * 0.75);
        int intenseMax = (int) Math.round(maxHeartRate * 0.85);
        
        // Store results
        result.setMaxHeartRate(maxHeartRate);
        result.setLightZoneMin(lightMin);
        result.setLightZoneMax(lightMax);
        result.setModerateZoneMin(moderateMin);
        result.setModerateZoneMax(moderateMax);
        result.setIntenseZoneMin(intenseMin);
        result.setIntenseZoneMax(intenseMax);
        
        // Generate target heart rate range (50-85% of max)
        result.setTargetHeartRateMin((int) Math.round(maxHeartRate * 0.50));
        result.setTargetHeartRateMax((int) Math.round(maxHeartRate * 0.85));
        
        // Generate recommendation based on age
        result.setRecommendation(generateHeartRateRecommendation(age, maxHeartRate));
        result.setError(false);
        
        return result;
    }
    
    private String generateHeartRateRecommendation(int age, int maxHR) {
        if (age < 20) {
            return "Your target heart rate range is " + (int)Math.round(maxHR * 0.50) + 
                   "-" + (int)Math.round(maxHR * 0.85) + " bpm. Start with moderate exercise (60-75% of max).";
        } else if (age < 40) {
            return "Your target heart rate range is " + (int)Math.round(maxHR * 0.50) + 
                   "-" + (int)Math.round(maxHR * 0.85) + " bpm. For fat burning, aim for 60-70% of max heart rate.";
        } else if (age < 60) {
            return "Your target heart rate range is " + (int)Math.round(maxHR * 0.50) + 
                   "-" + (int)Math.round(maxHR * 0.85) + " bpm. Consult your doctor before starting intense workouts.";
        } else {
            return "Your target heart rate range is " + (int)Math.round(maxHR * 0.50) + 
                   "-" + (int)Math.round(maxHR * 0.70) + " bpm. Focus on light to moderate intensity exercises.";
        }
    }
}