package payment;

public class CardPayment implements PaymentStrategy {

    @Override
    public double pay(double amount) {

        return amount;

    }
}

