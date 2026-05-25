interface Database {
    void connect();
}
class RealDatabase implements Database {
    public void connect() {
        System.out.println("Подключение к базе данных...");
    }
}
class DatabaseProxy implements Database {
    private RealDatabase realDatabase;
    private String role;
    public DatabaseProxy(String role) {
        this.role = role;
    }
    public void connect() {
        if (!role.equals("ADMIN")) {
            System.out.println("Доступ запрещён");
            return;
        }
        if (realDatabase == null) {
            realDatabase = new RealDatabase();
        }
        realDatabase.connect();
    }
}
public class ProxyAccessDemo {
    public static void main(String[] args) {
        System.out.println("=== ADMIN ===");
        Database admin = new DatabaseProxy("ADMIN");
        admin.connect();
        System.out.println("\n=== USER ===");
        Database user = new DatabaseProxy("USER");
        user.connect();
    }
}