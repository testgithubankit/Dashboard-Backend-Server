package com.paymentDashboard.dashboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OtpInfo {

    @Id
    private String phoneNumber;
    private String otp;

    public OtpInfo() {
        // Default constructor for Jackson (if using JSON serialization/deserialization)
    }

    public OtpInfo(String phoneNumber, String otp) {
        this.phoneNumber = phoneNumber;
        this.otp = otp;
    }

    // Getters and setters

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "OtpInfo{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
}
