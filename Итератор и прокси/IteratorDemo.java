import java.util.Iterator;
import java.util.NoSuchElementException;

class Task {
    String name;
    boolean isUrgent;

    Task(String name, boolean isUrgent) {
        this.name = name;
        this.isUrgent = isUrgent;
    }

    @Override
    public String toString() { return name + (isUrgent ? " (!)" : ""); }
}

class TaskList implements Iterable<Task> {
    private Task[] tasks;
    private int size = 0;
    private boolean isReverse = false;

    public TaskList(int capacity) { tasks = new Task[capacity]; }

    public void addTask(Task task) {
        if (size < tasks.length) tasks[size++] = task;
    }

    public void setReverse(boolean reverse) {
        this.isReverse = reverse;
    }

    @Override
    public Iterator<Task> iterator() {
        return new BasicIterator();
    }

    private class BasicIterator implements Iterator<Task> {
        private int cursor;

        public BasicIterator() {
            this.cursor = isReverse ? (size - 1) : 0;
        }

        @Override
        public boolean hasNext() {
            return isReverse ? cursor >= 0 : cursor < size;
        }

        @Override
        public Task next() {
            if (!hasNext()) throw new NoSuchElementException();
            return tasks[isReverse ? cursor-- : cursor++];
        }
    }

    public Iterator<Task> urgentIterator() {
        return new UrgentIterator();
    }

    private class UrgentIterator implements Iterator<Task> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            while (cursor < size && !tasks[cursor].isUrgent) {
                cursor++;
            }
            return cursor < size;
        }

        @Override
        public Task next() {
            if (!hasNext()) throw new NoSuchElementException();
            return tasks[cursor++];
        }
    }
}

public class IteratorDemo {
    public static void main(String[] args) {
        TaskList myTasks = new TaskList(10);
        myTasks.addTask(new Task("Помыть кота", false));
        myTasks.addTask(new Task("Купить хлеб", true));
        myTasks.addTask(new Task("Сдать проект", true));
        myTasks.addTask(new Task("Поспать", false));

        System.out.println("=== 1. Обычный обход (вперед) ===");
        myTasks.setReverse(false); 
        for (Task t : myTasks) {
            System.out.println(t);
        }

        System.out.println("\n=== 2. Обычный обход (назад) ===");
        myTasks.setReverse(true);
        for (Task t : myTasks) {
            System.out.println(t);
        }

        System.out.println("\n=== 3. Только срочные (всегда вперед) ===");
        Iterator<Task> urgentIt = myTasks.urgentIterator();
        while (urgentIt.hasNext()) {
            System.out.println(urgentIt.next());
        }
    }
}