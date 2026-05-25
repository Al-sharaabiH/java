public class TemplateMethodDemo {
    public static void main(String[] args) {
        BeverageMaker tea = new Tea();
        tea.makeBeverage();

        BeverageMaker coffee = new Coffee();
        coffee.makeBeverage();
        
        BeverageMaker[] drinks = {
            new Tea(),
            new Coffee(),
        };
        
        for (int i = 0; i < drinks.length; i++) {
            drinks[i].makeBeverage();
        }
    }
}