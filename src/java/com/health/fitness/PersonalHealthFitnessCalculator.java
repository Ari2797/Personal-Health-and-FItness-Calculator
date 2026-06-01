package com.health.fitness;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;

import javax.xml.namespace.QName;
import java.util.regex.Pattern;

@WebService(
    serviceName = "PersonalHealthFitnessCalculator",
    portName = "PersonalHealthFitnessCalculatorPort",
    targetNamespace = "http://fitness.health.com/"
)
public class PersonalHealthFitnessCalculator {

    private static final Pattern IC_PATTERN = Pattern.compile("^\\d{6}-\\d{2}-\\d{4}$");
    private static final int CURRENT_YEAR = 2026;

    // ─── Fault Factory ───────────────────────────────────────────────────────

    private SOAPFaultException buildFault(String message) {
        try {
            SOAPFault fault = MessageFactory.newInstance()
                    .createMessage().getSOAPBody().addFault();
            fault.setFaultString(message);
            fault.setFaultCode(new QName("http://schemas.xmlsoap.org/soap/envelope/", "Client"));
            return new SOAPFaultException(fault);
        } catch (Exception e) {
            throw new RuntimeException("Failed to construct SOAPFault: " + e.getMessage(), e);
        }
    }

    // ─── Validation Helpers ───────────────────────────────────────────────────

    private void validateWeight(double w) {
        if (w <= 0) throw buildFault("Invalid weight: must be > 0. Received: " + w);
    }

    private void validateHeight(double h) {
        if (h <= 0) throw buildFault("Invalid height: must be > 0. Received: " + h);
    }

    private void validateAge(int age) {
        if (age < 0 || age > 130) throw buildFault("Invalid age: " + age + ". Must be between 0 and 130.");
    }

    private void validateGender(int gender) {
        if (gender != 0 && gender != 1)
            throw buildFault("Invalid gender: must be 0 (female) or 1 (male). Received: " + gender);
    }

    private void validateIC(String ic) {
        if (ic != null && !ic.trim().isEmpty()) {
        } else {
            throw buildFault("IC number must not be null or empty.");
        }
        if (!IC_PATTERN.matcher(ic).matches())
            throw buildFault("Malformed IC: expected YYMMDD-XX-XXXX. Received: " + ic);

        int mm = Integer.parseInt(ic.substring(2, 4));
        int dd = Integer.parseInt(ic.substring(4, 6));
        if (mm < 1 || mm > 12)
            throw buildFault("IC contains invalid month: " + mm);
        if (dd < 1 || dd > 31)
            throw buildFault("IC contains invalid day: " + dd);
    }

    // ─── IC Age Parser ────────────────────────────────────────────────────────

    private int parseAgeFromIC(String ic) {
        int yy = Integer.parseInt(ic.substring(0, 2));
        // Pivot: YY >= 26 → 1900s, else → 2000s
        int birthYear = (yy >= 26) ? (1900 + yy) : (2000 + yy);
        return CURRENT_YEAR - birthYear;
    }

    // ─── Web Methods ──────────────────────────────────────────────────────────

    @WebMethod(operationName = "getUserProfile")
    public String getUserProfile(
            @WebParam(name = "name") String name,
            @WebParam(name = "ic") String ic,
            @WebParam(name = "gender") int gender,
            @WebParam(name = "weight") double w,
            @WebParam(name = "height") double h) {

        if (name == null || name.trim().isEmpty())
            throw buildFault("Name must not be null or empty.");

        validateIC(ic);
        validateGender(gender);
        validateWeight(w);
        validateHeight(h);

        int age = parseAgeFromIC(ic);
        String genderLabel = (gender == 1) ? "Male" : "Female";

        return String.format(
            "Name: %s | IC: %s | Age: %d | Gender: %s | Weight: %.1f kg | Height: %.1f cm",
            name.trim(), ic, age, genderLabel, w, h
        );
    }

    @WebMethod(operationName = "calculateBMI")
    public double calculateBMI(
            @WebParam(name = "weight") double w,
            @WebParam(name = "height") double h) {

        validateWeight(w);
        validateHeight(h);
        // Placeholder – real formula: w / ((h/100)^2)
        return 0.0;
    }

    @WebMethod(operationName = "calculateBodyFat")
    public double calculateBodyFat(
            @WebParam(name = "bmi") double bmi,
            @WebParam(name = "age") int age,
            @WebParam(name = "gender") int gender) {

        if (bmi <= 0) throw buildFault("Invalid BMI: must be > 0. Received: " + bmi);
        validateAge(age);
        validateGender(gender);
        // Placeholder – real formula: Deurenberg (1.20*BMI + 0.23*age − 10.8*gender − 5.4)
        return 0.0;
    }

    @WebMethod(operationName = "calculateCalories")
    public double calculateCalories(
            @WebParam(name = "met") double met,
            @WebParam(name = "weight") double w,
            @WebParam(name = "durationMinutes") double t) {

        if (met <= 0) throw buildFault("Invalid MET: must be > 0. Received: " + met);
        validateWeight(w);
        if (t <= 0) throw buildFault("Invalid duration: must be > 0. Received: " + t);
        // Placeholder – real formula: MET * w * (t/60)
        return 0.0;
    }

    @WebMethod(operationName = "predictWeightLoss")
    public double predictWeightLoss(
            @WebParam(name = "caloricDeficitPerDay") double deficit,
            @WebParam(name = "days") int days) {

        if (deficit <= 0) throw buildFault("Invalid caloric deficit: must be > 0. Received: " + deficit);
        if (days <= 0) throw buildFault("Invalid days: must be > 0. Received: " + days);
        // Placeholder – real formula: (deficit * days) / 7700
        return 0.0;
    }

    @WebMethod(operationName = "calculateHeartRate")
    public String calculateHeartRate(
            @WebParam(name = "age") int age) {

        validateAge(age);
        // Placeholder – real formula: max HR = 220 - age; zones as percentages
        return "Pending";
    }
}