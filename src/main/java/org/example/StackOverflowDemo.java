package org.example;

public class StackOverflowDemo {
    public static void main(String[] args) {
        method();
    }

    private static int method() {
        return method();
    }
}
