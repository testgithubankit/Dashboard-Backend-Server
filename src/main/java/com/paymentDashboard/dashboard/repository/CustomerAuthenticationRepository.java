package com.paymentDashboard.dashboard.repository;

import com.paymentDashboard.dashboard.domain.CustomerAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAuthenticationRepository extends JpaRepository<CustomerAuthentication,String> {

    CustomerAuthentication findByEmailAndPassword(String email, String password);

    CustomerAuthentication findByEmail(String email);

}
