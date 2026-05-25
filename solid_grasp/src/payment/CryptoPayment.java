package payment;

public class CryptoPayment
        implements PaymentStrategy {

    @Override
    public double pay(double amount) {

        return amount * 1.2;

    }
}
