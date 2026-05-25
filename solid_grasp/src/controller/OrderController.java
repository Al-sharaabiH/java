
package controller;

import model.Order;
import payment.CardPayment;
import payment.CryptoPayment;
import payment.PaymentStrategy;
import service.PaymentService;

public class OrderController {

    private final PaymentService paymentService;

    public OrderController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // GRASP: Controller
    public void createOrder() {

        Order order = new Order();

        order.addItem("Laptop", 1000);
        order.addItem("Mouse", 50);

        double total = order.calculateTotal();

        PaymentStrategy strategy =
                new CryptoPayment();

        double result =
                paymentService.processPayment(
                        strategy,
                        total
                );

        System.out.println(
                "Total: " + result
        );
    }
}