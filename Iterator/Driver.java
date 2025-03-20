import java.util.function.Predicate;

public class Driver {
    public static void main(String[] args) {
        // --- Demonstration with Integer elements ---
        MyArray<Integer> intArray = new MyArray<>(10);
        for (int i = 1; i <= 10; i++) {
            intArray.add(i);
        }
        System.out.println("Original Integer Array:");
        Iterator<Integer> intIterator = intArray.getIterator();
        for (intIterator.first(); !intIterator.isDone(); intIterator.next()) {
            System.out.print(intIterator.current() + " ");
        }
        System.out.println("\n");

        // FilterIterator: Filter even numbers.
        Predicate<Integer> isEven = x -> x % 2 == 0;
        MyFilterIterator<Integer> evenIterator = new MyFilterIterator<>(intArray.getIterator(), isEven);
        System.out.println("Filtered even numbers:");
        for (evenIterator.first(); !evenIterator.isDone(); evenIterator.next()) {
            System.out.print(evenIterator.current() + " ");
        }
        System.out.println("\n");

        // FilterIterator on top of another FilterIterator:
        // First filter: even numbers, second filter: numbers greater than 4.
        Predicate<Integer> greaterThanFour = x -> x > 4;
        MyFilterIterator<Integer> nestedFilterIterator =
            new MyFilterIterator<>(new MyFilterIterator<>(intArray.getIterator(), isEven), greaterThanFour);
        System.out.println("Filtered even numbers that are greater than 4:");
        for (nestedFilterIterator.first(); !nestedFilterIterator.isDone(); nestedFilterIterator.next()) {
            System.out.print(nestedFilterIterator.current() + " ");
        }
        System.out.println("\n");

        // FilterIterator that filters out everything (predicate always false).
        Predicate<Integer> alwaysFalse = x -> false;
        MyFilterIterator<Integer> emptyIterator = new MyFilterIterator<>(intArray.getIterator(), alwaysFalse);
        System.out.println("FilterIterator that filters out everything:");
        for (emptyIterator.first(); !emptyIterator.isDone(); emptyIterator.next()) {
            System.out.print(emptyIterator.current() + " ");
        }
        System.out.println("(should be empty)\n");

        // ReverseIterator: iterate the array in reverse order.
        ReverseIterator<Integer> reverseIterator = new ReverseIterator<>(intArray.getIterator());
        System.out.println("Reverse iteration of Integer Array:");
        while (!reverseIterator.isDone()) {
            System.out.print(reverseIterator.current() + " ");
            reverseIterator.next();
        }
        System.out.println("\n");

        // ReverseIterator on a FilterIterator:
        // Filter even numbers and then iterate the filtered results in reverse order.
        MyFilterIterator<Integer> filterEvenIterator = new MyFilterIterator<>(intArray.getIterator(), isEven);
        ReverseIterator<Integer> reverseFilteredIterator = new ReverseIterator<>(filterEvenIterator);
        System.out.println("Reverse iteration of filtered even numbers:");
        while (!reverseFilteredIterator.isDone()) {
            System.out.print(reverseFilteredIterator.current() + " ");
            reverseFilteredIterator.next();
        }
        System.out.println("\n");

        // --- Demonstration with String elements ---
        MyArray<String> stringArray = new MyArray<>(5);
        stringArray.add("apple");
        stringArray.add("banana");
        stringArray.add("cherry");
        stringArray.add("date");
        stringArray.add("elderberry");
        System.out.println("Original String Array:");
        Iterator<String> stringIterator = stringArray.getIterator();
        for (stringIterator.first(); !stringIterator.isDone(); stringIterator.next()) {
            System.out.print(stringIterator.current() + " ");
        }
        System.out.println("\n");

        // FilterIterator on Strings: filter those containing the letter 'a'
        Predicate<String> containsA = s -> s.contains("a");
        MyFilterIterator<String> stringFilterIterator = new MyFilterIterator<>(stringArray.getIterator(), containsA);
        System.out.println("Filtered Strings (containing 'a'):");
        for (stringFilterIterator.first(); !stringFilterIterator.isDone(); stringFilterIterator.next()) {
            System.out.print(stringFilterIterator.current() + " ");
        }
        System.out.println();
    }
}
