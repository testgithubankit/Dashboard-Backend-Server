package com.paymentDashboard.dashboard.CustomerAuthenticationService;


import com.paymentDashboard.dashboard.domain.CustomerAuthentication;

import java.util.Map;

public interface SecurityTokenGeneratorCustomer {
    Map<String, String> generateToken(CustomerAuthentication customerAuthentication);
}