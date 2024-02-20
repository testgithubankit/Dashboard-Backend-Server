package com.paymentDashboard.dashboard.services;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {

    @Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;

    public String generateOtp() {
        // Generate a 6-digit random OTP
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public void sendOtp(String to, String otp) {
        // Send OTP via Twilio SMS
        String messageBody = "Your OTP is: " + otp;

        Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(twilioPhoneNumber),
                messageBody
        ).create();
    }
}
