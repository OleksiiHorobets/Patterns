package org.example;

import java.util.List;
import java.util.function.Predicate;

public class LightweightStrategy {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(totalValues(numbers, num -> num % 2 == 0));
    }

    public static int totalValues(List<Integer> values, Predicate<Integer> selector) {
        return values.stream()
                .filter(selector)
                .reduce(0, Integer::sum);
    }
}