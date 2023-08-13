package com.tiduswr.hrpayroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tiduswr.hrpayroll.entities.Payment;
import com.tiduswr.hrpayroll.entities.Worker;
import com.tiduswr.hrpayroll.feignclients.WorkerFeignClient;

import feign.FeignException;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(long workerId, int days){

        Worker worker = null;

        try{
            worker = workerFeignClient.findById(workerId).getBody();

            if(worker == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            return new Payment(worker.getName(), worker.getDailyIncome(), days);
        }catch(FeignException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.status()));
        }
    }

}
