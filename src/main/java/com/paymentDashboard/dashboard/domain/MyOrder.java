package com.paymentDashboard.dashboard.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Time;
import java.util.Date;


@Entity
public class MyOrder {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long myOrderId;
    private String orderId;
    private String amount;
    private Double totalAmount;

    private String receipt;
    private String status;
    private String email;
    private String paymentId;

    private Date paymentDate;

    private Time paymentTime;


    public MyOrder() {
    }

    public MyOrder(Long myOrderId, String orderId,String email, String amount, String receipt,
                   String status, String paymentId, Date paymentDate, Time paymentTime,Double totalAmount) {
        this.myOrderId = myOrderId;
        this.orderId = orderId;
        this.amount = amount;
        this.receipt = receipt;
        this.status = status;
        this.email=email;
        this.paymentId = paymentId;
        this.paymentDate=paymentDate;
        this.paymentTime=paymentTime;
        this.totalAmount=totalAmount;
    }

    public Long getMyOrderId() {
        return myOrderId;
    }

    public void setMyOrderId(Long myOrderId) {
        this.myOrderId = myOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Time getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Time paymentTime) {
        this.paymentTime = paymentTime;
    }

    @Override
    public String toString() {
        return "MyOrder{" +
                "myOrderId=" + myOrderId +
                ", orderId='" + orderId + '\'' +
                ", amount='" + amount + '\'' +
                ", receipt='" + receipt + '\'' +
                ", status='" + status + '\'' +
                ", email='" + email + '\'' +
                ", paymentId='" + paymentId + '\'' +
                '}';
    }
}