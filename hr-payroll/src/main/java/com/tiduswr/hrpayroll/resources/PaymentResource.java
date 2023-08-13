package com.tiduswr.hrpayroll.resources;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiduswr.hrpayroll.entities.Payment;
import com.tiduswr.hrpayroll.services.PaymentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/payments")
public class PaymentResource {

    @Autowired
    private PaymentService service;
    
    @CircuitBreaker(name = "getPaymentCircuit", fallbackMethod = "getPaymentFallback")
    @TimeLimiter(name = "getPaymentCircuit", fallbackMethod = "getPaymentFallback")
    @GetMapping("/{workerId}/days/{days}")
    public CompletableFuture<ResponseEntity<Payment>> getPayment(@PathVariable("workerId") Long workerId, 
        @PathVariable("days") Integer days){

        return CompletableFuture.supplyAsync(() -> {
            Payment payment = service.getPayment(workerId, days);
            return ResponseEntity.ok(payment);
        });
    }

    //Caso o serviço esteja com problema, você pode executar um método alternativo com o CircuitBreaker
    //Caso uma api não esteja funcionando bem, o CircuitBreaker pode optar por ir em outro método e cai aqui
    public CompletableFuture<ResponseEntity<Payment>> getPaymentFallback(Long workerId, Integer days, Throwable exception){        
        return CompletableFuture.supplyAsync(() -> {
            Payment payment = new Payment("CircuitBreaker", 100d, 5);
            return ResponseEntity.ok(payment);
        });
    }

}
