package com.paymentDashboard.dashboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CustomerAuthentication {
    @Id
    private String email;
    private String password;
    private String userName;
    private String phoneNo;

    public CustomerAuthentication(String email, String password, String userName, String phoneNo) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.phoneNo = phoneNo;
    }

    public CustomerAuthentication() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
