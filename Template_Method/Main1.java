public class Main1 {
    public static void main(String[] args) {
        Navigator navigator = new Navigator(new CarRouteStrategy());

        // на машине
        navigator.buildRoute("дом", "работа");

        // пешком (меняем стратегию)
        navigator.setStrategy(new WalkingRouteStrategy());
        navigator.buildRoute("работа", "кафе");

        // на автобусе
        navigator.setStrategy(new PublicTransportStrategy());
        navigator.buildRoute("кафе", "дом");
    }
}