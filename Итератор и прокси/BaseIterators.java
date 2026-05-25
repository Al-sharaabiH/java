import java.util.*;

public class BaseIterators {
    public static void main(String[] args) {
        
        System.out.println("1. ОБЫЧНЫЙ ITERATOR (только вперед)");
        
        List<String> fruits = new ArrayList<>(Arrays.asList("Сливы", "Яблоко", "Банан", "Арбуз"));
        
        Iterator<String> simpleIterator = fruits.iterator();
        while (simpleIterator.hasNext()) {
            String fruit = simpleIterator.next();
            System.out.println("  -> " + fruit);
        }
        
        
        System.out.println("2. ДВУНАПРАВЛЕННЫЙ LISTITERATOR (вперед и назад)");
        
        List<String> names = new ArrayList<>(Arrays.asList("Арутр", "Герман", "Виктор", "Толя"));
        ListIterator<String> listIterator = names.listIterator();
        
        System.out.println("  Идем вперед:");
        while (listIterator.hasNext()) {
            System.out.println("    " + listIterator.next());
        }
        
        System.out.println("  Идем назад:");
        while (listIterator.hasPrevious()) {
            System.out.println("    " + listIterator.previous());
        }
        
        
        System.out.println("3. PRIMITIVEITERATOR (для примитивов)");
        int[] numbers = {10, 20, 30, 40, 50};
        PrimitiveIterator.OfInt intIterator = Arrays.stream(numbers).iterator();
        
        System.out.println("  Обходим int-ы без автобоксинга:");
        while (intIterator.hasNext()) {
            int value = intIterator.nextInt();
            System.out.println("    " + value);
        }
        
        System.out.println("4. SCANNER (итератор для токенов)");
        
        String text = "Абонент не в зоне сети, музыка, ты меня посети"; 
        Scanner scanner = new Scanner(text);
        
        System.out.println("  Разбиваем текст на токены (слова):");
        int wordCount = 0;
        while (scanner.hasNext()) {
            String token = scanner.next();
            wordCount++;
            System.out.println("    Токен " + wordCount + ": " + token);
        }
    }
}