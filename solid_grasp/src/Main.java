import controller.OrderController;
import service.PaymentService;

public class Main {

    public static void main(String[] args) {

        PaymentService paymentService =
                new PaymentService();

        OrderController controller =
                new OrderController(paymentService);

        controller.createOrder();
    }
}