package com.paymentDashboard.dashboard.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED,reason = "Customer Already Register")
public class CustomerAlreadyExitsException extends Exception{
}
