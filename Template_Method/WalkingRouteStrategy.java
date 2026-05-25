public class WalkingRouteStrategy implements RouteStrategy {
    @Override
    public void buildRoute(String from, String to) {
        System.out.println("пешеходный маршрут: " + from + " -> " + to);
    }
}