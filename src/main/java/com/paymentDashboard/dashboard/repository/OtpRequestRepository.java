package com.paymentDashboard.dashboard.repository;

import com.paymentDashboard.dashboard.domain.Customer;
import com.paymentDashboard.dashboard.domain.OtpRequestData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRequestRepository extends JpaRepository<OtpRequestData,String> {

    OtpRequestData findByOtp(int otp);

    OtpRequestData findByUserEmailAndOtp(String userEmail, int otp);

}
