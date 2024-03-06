package com.paymentDashboard.dashboard.services;

import com.paymentDashboard.dashboard.domain.Admin;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String, String> generateToken(Admin admin);
}
