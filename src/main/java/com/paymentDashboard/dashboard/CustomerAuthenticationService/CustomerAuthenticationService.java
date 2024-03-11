package com.paymentDashboard.dashboard.CustomerAuthenticationService;

import com.paymentDashboard.dashboard.Exception.CustomerAlreadyExitsException;
import com.paymentDashboard.dashboard.Exception.CustomerNotFoundException;
import com.paymentDashboard.dashboard.domain.CustomerAuthentication;
import com.paymentDashboard.dashboard.repository.CustomerAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAuthenticationService {

    private final CustomerAuthenticationRepository customerAuthenticationRepositoryRepository;


    @Autowired
    public CustomerAuthenticationService (CustomerAuthenticationRepository customerAuthenticationRepositoryRepository) {
        this.customerAuthenticationRepositoryRepository = customerAuthenticationRepositoryRepository;
    }


    public CustomerAuthentication registerCustomer(CustomerAuthentication customer) throws CustomerAlreadyExitsException {
        if(customerAuthenticationRepositoryRepository.findById(customer.getEmail()).isPresent()){
            throw new CustomerAlreadyExitsException();
        }
//        if(!(addCustomer.getEmail().isEmpty())){
//            ResponseEntity responseEntity = customerProxy.saveCustomer(customer);
//            System.out.println(responseEntity.getBody());
//        }
        return customerAuthenticationRepositoryRepository.save(customer);
    }


    public CustomerAuthentication findByEmailAndPassword(String email, String password) throws CustomerNotFoundException{
        CustomerAuthentication customerAuthentication = customerAuthenticationRepositoryRepository.findByEmailAndPassword(email, password);
        if (customerAuthentication == null){
            throw new CustomerNotFoundException();
        }
        return customerAuthentication;
    }
}
