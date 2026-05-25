package payment;

public class PaypalPayment implements PaymentStrategy {

    @Override
    public double pay(double amount) {

        return amount * 1.1;

    }
}