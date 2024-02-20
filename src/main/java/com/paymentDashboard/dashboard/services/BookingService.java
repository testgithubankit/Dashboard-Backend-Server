package com.paymentDashboard.dashboard.services;

// BookingService.java

import com.paymentDashboard.dashboard.Exception.OtpNotFound;
import com.paymentDashboard.dashboard.Exception.OtpNotVerified;
import com.paymentDashboard.dashboard.domain.OtpRequestData;
import com.paymentDashboard.dashboard.repository.OtpRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class BookingService {

    @Autowired
    private OtpRequestRepository otpRequestRepository;

    @Autowired
    private JavaMailSender javaMailSender;


    private static final String COMPANY_EMAIL = "sky.royaltiwari@gmail.com"; // Replace with your company's email

    // Your Razorpay integration logic goes here

    public void sendPaymentConfirmationEmail(String customerEmail, String paymentDetails) {
        // Send email to customer
        sendEmail(customerEmail, "Payment Confirmation", buildCustomerEmailBody(paymentDetails));

        // Send email to company
        sendEmail(COMPANY_EMAIL, "New Payment Received", buildCompanyEmailBody(customerEmail, paymentDetails));

        System.out.println("mail Send -:------:");
    }

    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }

    private String buildCustomerEmailBody(String paymentDetails) {
        return "Dear Customer,\n\n"
                + "Thank you for your payment. Below are the payment details:\n\n"
                + paymentDetails
                + "\n\nIf you have any questions or concerns, please contact our support team.\n\n"
                + "Thank you,\nThe Company";
    }

    private String buildCompanyEmailBody(String customerEmail, String paymentDetails) {
        return "New payment received from customer:\n\n"
                + "Customer Email: " + customerEmail + "\n"
                + "Payment Details:\n" + paymentDetails;
    }


    public void sendOtpEmail(String userEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject("Your OTP for Verification");

        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        message.setText("Your OTP is: " + otp);
        OtpRequestData otpRequestData=new OtpRequestData();
        otpRequestData.setUserEmail(userEmail);
        otpRequestData.setOtp(otp);
        otpRequestRepository.save(otpRequestData);
        System.out.println(otpRequestData);

        javaMailSender.send(message);
    }

    public OtpRequestData findByOtp( int otp) throws OtpNotVerified {
        OtpRequestData otpRequestData = otpRequestRepository.findByOtp(otp);
        if (otp == 0){
           throw new OtpNotVerified();
        }
        return otpRequestData;
    }

    public boolean cearOtpAfterVerified(OtpRequestData otpRequestData) throws OtpNotFound {
        boolean result=false;
        if(otpRequestRepository.findById(otpRequestData.getUserEmail()).isEmpty()){
            throw new OtpNotFound();
        }else {
            otpRequestRepository.deleteById(otpRequestData.getUserEmail());
            result= true;
        }
        return result;
    }
}

