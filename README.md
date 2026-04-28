# Personal-Health-and-FItness-Calculator
Group 4 BIW20404

![Version](https://img.shields.io/badge/version-1.0-blue)
![SOAP](https://img.shields.io/badge/protocol-SOAP-green)
![Status](https://img.shields.io/badge/status-active-success)

## 📋 Overview

The **Personal Health & Fitness Calculator** is a SOAP-based web service designed to assist users in monitoring and improving their health through automated calculations. It provides a centralized solution that simplifies complex health calculations and delivers accurate results instantly.

## 🧮 Modules

| Module | Formula | Output |
|--------|---------|--------|
| **BMI** | weight (kg) / height(m)² | Underweight, Normal, Overweight, Obese |
| **Body Fat %** | (1.20 × BMI) + (0.23 × Age) - (10.8 × Gender) - 5.4 | Low, Normal, High, Very High |
| **Calories Burned** | MET × Weight (kg) × Time (hours) | Total calories + intensity level |
| **Predicted Weight Loss** | (Calorie Deficit × Duration) / 7700 | Weight loss (kg) + status |
| **Heart Rate** | 220 - Age | Max HR + Target Zone (50%-85%) |

## 👥 Team Members

| Name | ID | Role |
|------|-----|------|
| Arivarasan A/L Panja Varnam | AI240175 | Leader & SOAP Developer |
| Marsya Nadirah Islam Binti Mazli | CI240051 | BMI, Body Fat, Calorie Modules |
| Nurul Liyana Binti Awang | AI240083 | Weight Loss & Heart Rate Modules |
| Nuratqah Syahirah Binti Mohd Yusaimi | CI240056 | Tester & Documentation |
| Nafisa Rasyiqah Binti Razman | AI240045 | Tester & Documentation |

## 🚀 Quick Start

```python
# Sample BMI Calculation Request
{
    "weight": 70,      # kg
    "height": 1.75     # meters
}
# Returns: BMI = 22.9 (Normal weight)
