interface Image {
    void display();
}
class RealImage implements Image {
    private String fileName;
    public RealImage(String fileName) {
        this.fileName = fileName;
        System.out.println("Загрузка изображения: " + fileName);
    }
    public void display() {
        System.out.println("Показ изображения: " + fileName);
    }
}
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;
    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }
    public void display() {
        if (realImage == null) {
            System.out.println("Proxy: создаём реальный объект...");
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
public class BaseProxyDemo {
    public static void main(String[] args) {
        System.out.println("=== ЛЕНИВАЯ ЗАГРУЗКА ===");
        Image image = new ProxyImage("photo.jpg");
        System.out.println("Первый вызов:");
        image.display();
        System.out.println("\nВторой вызов:");
        image.display();
    }
}