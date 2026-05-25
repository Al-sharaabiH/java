public class Navigator {
    private RouteStrategy strategy;

    // принимает стратегию сразу
    public Navigator(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    // или стратегию можно поменять во время выполнения
    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void buildRoute(String from, String to) {
        if (strategy == null) {
            System.out.println("стратегии нету");
            return;
        }
        strategy.buildRoute(from, to);
    }
}