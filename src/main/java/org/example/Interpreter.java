package org.example;

import java.util.Map;
import java.util.Optional;
import java.util.function.IntBinaryOperator;

public class Interpreter {
    private static final Map<String, IntBinaryOperator> mathOperations = Map.of(
            "+", (num1, num2)-> num1 + num2,
            "-", (num1, num2) -> num1 - num2,
            "*", (num1, num2) -> num1 * num2,
            "/", (num1, num2) -> num1 / num2
    );


    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 20;
        String operation = "*";

        Optional.ofNullable(mathOperations.get(operation))
                .map(func -> func.applyAsInt(num1, num2))
                .ifPresentOrElse(System.out::println, ()-> System.out.println("Unsupported operation!"));
    }
}