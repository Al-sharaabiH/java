// Strategy 1: Автомобиль
public class CarRouteStrategy implements RouteStrategy {
    @Override
    public void buildRoute(String from, String to) {
        System.out.println("маршрут на машине: " + from + " -> " + to);
        // логика поиска дорог
    }
}

