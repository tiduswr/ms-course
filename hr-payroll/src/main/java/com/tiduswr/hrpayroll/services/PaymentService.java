package com.tiduswr.hrpayroll.services;

import org.springframework.stereotype.Service;

import com.tiduswr.hrpayroll.entities.Payment;

@Service
public class PaymentService {
    
    public Payment getPayment(long workerId, int days){
        return new Payment("Bob", 200d, days);
    }

}
