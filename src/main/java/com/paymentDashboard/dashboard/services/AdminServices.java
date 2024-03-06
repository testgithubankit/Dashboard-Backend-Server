package com.paymentDashboard.dashboard.services;

import com.paymentDashboard.dashboard.Exception.AdminNotFoundException;
import com.paymentDashboard.dashboard.domain.Admin;
import com.paymentDashboard.dashboard.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminServices {

    private AdminRepository adminRepository;


    public AdminServices (AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }


    public Admin findByEmailAndPassword(String email, String password) throws AdminNotFoundException {
        Admin admin = adminRepository.findByEmailAndPassword(email, password);
        if (admin == null){
            throw new AdminNotFoundException();
        }
        return admin;
    }
}
