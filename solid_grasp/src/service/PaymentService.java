package service;

import payment.PaymentStrategy;

public class PaymentService {

    public double processPayment(
            PaymentStrategy strategy,
            double amount
    ) {

        return strategy.pay(amount);

    }
}