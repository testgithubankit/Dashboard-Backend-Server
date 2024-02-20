package com.paymentDashboard.dashboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OtpRequestData {

    @Id
    private String userEmail;

    private int otp;

    public OtpRequestData(String userEmail, int otp) {
        this.userEmail = userEmail;
        this.otp = otp;
    }

    public OtpRequestData() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "OtpRequestData{" +
                "userEmail='" + userEmail + '\'' +
                ", otp=" + otp +
                '}';
    }
}
