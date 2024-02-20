package com.paymentDashboard.dashboard.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long _id;
    private String studentName;
    private String yearOfStudy;
    private String address;
    private String demoTime;
    private String email;
    private String phoneNo;
    private Long totalAmount;
    private String bookedSlot;
    private List<String> paymentHistory;

    public Customer(Long _id, String studentName, String yearOfStudy, String address, String demoTime, String email, String phoneNo, Long totalAmount, String bookedSlot, List<String> paymentHistory) {
        this._id = _id;
        this.studentName = studentName;
        this.yearOfStudy = yearOfStudy;
        this.address = address;
        this.demoTime = demoTime;
        this.email = email;
        this.phoneNo = phoneNo;
        this.totalAmount = totalAmount;
        this.bookedSlot = bookedSlot;
        this.paymentHistory = paymentHistory;
    }

    public Customer() {
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDemoTime() {
        return demoTime;
    }

    public void setDemoTime(String demoTime) {
        this.demoTime = demoTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBookedSlot() {
        return bookedSlot;
    }

    public void setBookedSlot(String bookedSlot) {
        this.bookedSlot = bookedSlot;
    }

    public List<String> getPaymentHistory() {
        return paymentHistory;
    }

    public void setPaymentHistory(List<String> paymentHistory) {
        this.paymentHistory = paymentHistory;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "_id=" + _id +
                ", studentName='" + studentName + '\'' +
                ", yearOfStudy='" + yearOfStudy + '\'' +
                ", address='" + address + '\'' +
                ", demoTime='" + demoTime + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", totalAmount=" + totalAmount +
                ", bookedSlot='" + bookedSlot + '\'' +
                ", paymentHistory=" + paymentHistory +
                '}';
    }
}
