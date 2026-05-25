public class PublicTransportStrategy implements RouteStrategy {
    @Override
    public void buildRoute(String from, String to) {
        System.out.println("маршрут на транспорте: " + from + " -> " + to);
    }
}