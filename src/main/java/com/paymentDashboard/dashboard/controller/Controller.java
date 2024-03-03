package com.paymentDashboard.dashboard.controller;
import com.paymentDashboard.dashboard.Exception.OtpNotVerified;
import com.paymentDashboard.dashboard.domain.*;
import com.paymentDashboard.dashboard.repository.MyOrderRepository;
import com.paymentDashboard.dashboard.services.BookingService;
import com.paymentDashboard.dashboard.services.CustomerServices;
import com.paymentDashboard.dashboard.services.OtpService;
import com.paymentDashboard.dashboard.services.PaypalService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin("http://localhost:4200")
@CrossOrigin("https://saurabhkumarniit.github.io")

public class Controller {
    @Autowired
    CustomerServices customerService;

    @Autowired
    private MyOrderRepository myOrderRepository;

    private final BookingService bookingService;

    @Autowired
    private PaypalService paypalService;
    @Autowired
    private OtpService otpService;



    @Autowired
    public Controller(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/userRegister")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        Customer customer1 = customerService.addCustomer(customer);
        bookingService.sendOtpEmail(customer.getEmail());
        return new ResponseEntity<>(customer1, HttpStatus.CREATED);
    }

    @GetMapping("/userData")
    public List<Customer> getAllUserData() {
        return customerService.getCustomer();
    }
    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> generateAccessToken() {
        String accessToken = paypalService.getAccessToken();

        if (accessToken != null) {
            Map<String, String> response = new HashMap<>();
            response.put("access_token", accessToken);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Failed to generate access token"));
        }
    }

    @PostMapping("/paypal-payment")
    public ResponseEntity<String> createPayment(@RequestBody PaypalOrders paypalOrder) {
        String accessToken = paypalService.getAccessToken();

        if (accessToken != null) {
            // Use the accessToken and paypalOrder to make the necessary API calls to PayPal
            // You might want to use the PayPal SDK to create the payment
            // For simplicity, we'll return a success message here

            return ResponseEntity.ok("Payment created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create payment");
        }
    }


    @PutMapping("/update/{_id}")
    public ResponseEntity<?> updateData(@RequestBody Customer customer, @PathVariable Long _id) {
        return new ResponseEntity<>(customerService.updateCustomerDetails(customer, _id), HttpStatus.CREATED);
    }


    @PostMapping("/payment/create_order/{email}")
    @ResponseBody
    public String createOrder(@RequestBody Map<String, Object> data, @PathVariable String email) {
        System.out.println(data);

        int amount = Integer.parseInt(data.get("amount").toString());

        RazorpayClient client = null;
        try {
            client = new RazorpayClient("rzp_test_LP91fzOg59Pohi", "H1ohtNvYEEO8YxskcB02URs1");
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amount", amount * 100);
        jsonObject.put("currency", "INR");
        jsonObject.put("receipt", "txt_23456");

        Order order = null;
        try {
            order = client.orders.create(jsonObject);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
        System.out.println(order);

        MyOrder myOrder = new MyOrder();

        myOrder.setAmount(order.get("amount") + "");
        myOrder.setOrderId(order.get("id"));
        myOrder.setPaymentId(null);
        myOrder.setStatus("created");
        myOrder.setEmail(email);
        myOrder.setReceipt(order.get("receipt"));
        myOrderRepository.save(myOrder);
//        boolean sendEmail = emailService.sendEmail(email,amount);
        return order.toString();
    }

    @GetMapping("/allHistory")
    public List<MyOrder> getAllPaymentHistory() {
        return myOrderRepository.findAll();
    }

//    @PostMapping("/payment/update_order")
//    public ResponseEntity<?> updatePayment(@RequestBody Map<String, Object> data) {
//        MyOrder myOrder = this.myOrderRepository.findByOrderId(data.get("order_id").toString());
//
//        myOrder.setPaymentId(data.get("payment_id") != null ? data.get("payment_id").toString() : null);
//
//        if (data.get("status") != null) {
//            myOrder.setStatus(data.get("status").toString());
//        } else {
//            myOrder.setStatus("paid");
//        }
//
//        myOrderRepository.save(myOrder);
//        System.out.println(data);
//        System.out.println(myOrder.getAmount());
//
//        return ResponseEntity.ok(Map.of("msg", "updated"));
//    }
@PostMapping("/payment/update_order")
public ResponseEntity<?> updatePayment(@RequestBody Map<String, Object> data) {
    MyOrder myOrder = this.myOrderRepository.findByOrderId(data.get("order_id").toString());

    myOrder.setPaymentId(data.get("payment_id") != null ? data.get("payment_id").toString() : null);

    // Check for null before invoking toString()
    if (data.get("status") != null) {
        myOrder.setStatus(data.get("status").toString());
    } else {
        // Handle the case where the "status" is null, e.g., set a default value
        myOrder.setStatus("paid");

    }
    double amountInRupees = Double.parseDouble(myOrder.getAmount()) / 100.0;
    myOrderRepository.save(myOrder);

    // Extract payment details from myOrder
    String customerEmail = myOrder.getEmail();
    String paymentDetails = "Amount: " + amountInRupees + ", Order ID: " + myOrder.getOrderId();

    // Send payment confirmation email
    bookingService.sendPaymentConfirmationEmail(customerEmail, paymentDetails);

    System.out.println(data);
    System.out.println("================X====================X==================X==============X=====");
    System.out.println(paymentDetails);

    return ResponseEntity.ok(Map.of("msg", "updated"));
}


    @PostMapping("/otpVerify")
    public ResponseEntity<?> otpVerification(@RequestBody OtpRequestData otpRequestData) throws OtpNotVerified {
        ResponseEntity responseEntity = null;
//        Map<String,String> map = null;
        try {
            OtpRequestData otpRequestData1 = bookingService.findByOtp(otpRequestData.getOtp());
//            System.out.println(otpRequestData1.getOtp());
//            System.out.println(otpRequestData.getOtp());
            if (otpRequestData1.getOtp() == (otpRequestData.getOtp())) {
                responseEntity = new ResponseEntity<>(otpRequestData1, HttpStatus.OK);
                System.out.println(bookingService.cearOtpAfterVerified(otpRequestData1));
            }
//            responseEntity = new ResponseEntity<>("Otp Not Verified",HttpStatus.BAD_REQUEST);
        } catch (OtpNotVerified e) {
            throw new OtpNotVerified();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Try After Some Time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/generate-otp")
    public void generateOtp(@RequestParam String phoneNumber) {
        System.out.println("Received phoneNumber: " + phoneNumber);
        ResponseEntity responseEntity = null;
        String otp = otpService.generateOtp();
        otpService.sendOtp(phoneNumber, otp);
        responseEntity = new ResponseEntity<>("Otp Send", HttpStatus.OK);

//        otpService.storeOtp(phoneNumber, otp);
    }

}

