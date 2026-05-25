abstract class Handler {

    protected Handler next;

    // Устанавливаем следующий обработчик
    public void setNext(Handler next) {
        this.next = next;
    }

    // Метод обработки запроса
    public abstract void handle(String role);
}

class UserHandler extends Handler {

    @Override
    public void handle(String role) {

        if (role.equals("USER")) {
            System.out.println("Доступ USER подтвержден");
        } else if (next != null) {
            next.handle(role);
        } else {
            System.out.println("Роль не обработана");
        }
    }
}

class AdminHandler extends Handler {

    @Override
    public void handle(String role) {

        if (role.equals("ADMIN")) {
            System.out.println("Доступ ADMIN подтвержден");
        } else if (next != null) {
            next.handle(role);
        } else {
            System.out.println("Роль не обработана");
        }
    }
}

public class Main {

    public static void main(String[] args) {

        // Создаем обработчики
        Handler userHandler = new UserHandler();
        Handler adminHandler = new AdminHandler();

        // Формируем цепочку
        userHandler.setNext(adminHandler);

        // Отправляем запрос
        userHandler.handle("ADMIN");

        userHandler.handle("USER");

        userHandler.handle("MODERATOR");
    }
}

