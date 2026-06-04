/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.fitness.service;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author mrsya
 */
@WebService(serviceName = "FitnessCalculator")
public class FitnessCalculator {

   private void validateUser(UserInformation user) throws Exception {
        if (user == null) {
            throw new javax.xml.ws.soap.SOAPFaultException(
                javax.xml.soap.SOAPFactory.newInstance().createFault(
                    "Missing User Information payload.", 
                    new javax.xml.namespace.QName("http://service.fitness.com/", "Client")
                )
            );
        }
        if (user.getWeight() <= 0 || user.getHeight() <= 0) {
            throw new javax.xml.ws.soap.SOAPFaultException(
                javax.xml.soap.SOAPFactory.newInstance().createFault(
                    "Invalid profile entries: Weight and Height must be greater than zero.", 
                    new javax.xml.namespace.QName("http://service.fitness.com/", "Client")
                )
            );
        }
    }

    @WebMethod(operationName = "getAgeFromID")
    public int getAgeFromID(@WebParam(name = "user") UserInformation user) throws Exception {
        if (user == null || user.getIcNumber() == null || user.getIcNumber().length() < 6) {
            throw new javax.xml.ws.soap.SOAPFaultException(
                javax.xml.soap.SOAPFactory.newInstance().createFault(
                    "Invalid IC Number: Must provide at least 6 digits (YYMMDD).", 
                    new javax.xml.namespace.QName("http://service.fitness.com/", "Client")
                )
            );
        }
        int yearPart = Integer.parseInt(user.getIcNumber().substring(0, 2));
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        int currentYearLastTwo = currentYear % 100; 

        int birthYear = (yearPart > currentYearLastTwo) ? (1900 + yearPart) : (2000 + yearPart);
        return currentYear - birthYear;
    }

    @WebMethod(operationName = "calculateBMI")
    public double calculateBMI(@WebParam(name = "user") UserInformation user) throws Exception {
        validateUser(user);
        
        double bmi = user.getWeight() / (user.getHeight() * user.getHeight());
        return Math.round(bmi * 100.0) / 100.0; 
    }

    @WebMethod(operationName = "calculateBodyFat")
    public double calculateBodyFat(@WebParam(name = "user") UserInformation user) throws Exception {
        validateUser(user);
        
        // Internal chaining to fulfill structural calculations automatically
        double bmi = user.getWeight() / (user.getHeight() * user.getHeight());
        int age = getAgeFromID(user);
        int genderNumeric = user.getGender().equalsIgnoreCase("male") ? 1 : 0;
        
        double bf = (1.20 * bmi) + (0.23 * age) - (10.8 * genderNumeric) - 5.4;
        return Math.round(bf * 100.0) / 100.0;
    }
    
    @WebMethod(operationName = "calculateBMR")
    public double calculateBMR(@WebParam(name = "user") UserInformation user) throws Exception {
        validateUser(user);
        
        int age = getAgeFromID(user);
        double heightCm = user.getHeight() * 100.0; // Automatically converts meters to cm
        double bmr;
        
        if (user.getGender().equalsIgnoreCase("male")) {
            bmr = (10 * user.getWeight()) + (6.25 * heightCm) - (5 * age) + 5;
        } else {
            bmr = (10 * user.getWeight()) + (6.25 * heightCm) - (5 * age) - 161;
        }
        return Math.round(bmr * 100.0) / 100.0;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "userInformation")
    public String userInformation(@WebParam(name = "name") String name, @WebParam(name = "icNumber") String icNumber, @WebParam(name = "gender") String gender, @WebParam(name = "weight") double weight, @WebParam(name = "height") double height) {
        //TODO write your implementation code here:
        return null;
    }
}
