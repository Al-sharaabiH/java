abstract class BeverageMaker {
    public final void makeBeverage() {
        System.out.println("\n=== НАЧАЛО ПРИГОТОВЛЕНИЯ ===");
        // Шаг 0: Название напитка - ВАРИАТИВНЫЙ
        name();
        // Шаг 1: Общий для всех напитков
        boilWater();
        
        // Шаг 2: ВАРИАТИВНЫЙ
        brew();
        
        // Шаг 3: Общий для всех напитков
        pourInCup();
            
        System.out.println("=== ПРИГОТОВЛЕНИЕ ЗАВЕРШЕНО ===\n");
    }

    private void boilWater() {
        System.out.println("1. Кипятим воду до 100°C");
    }
    
    private void pourInCup() {
        System.out.println("3. Наливаем напиток в чашку");
    }

    // Шаги, которые зависят от типа напитка
    protected abstract void name();
    protected abstract void brew();

}

class Tea extends BeverageMaker {
    @Override
    protected void name() {
        System.out.println("ЧАЙ");
    }
    protected void brew() {
        System.out.println("2. Завариваем чайный пакетик 3 минуты");
    }
}

class Coffee extends BeverageMaker {
    @Override
    protected void name() {
        System.out.println("КОФЕ");
    }
    protected void brew() {
        System.out.println("2. Завариваем молотый кофе");
    }
}