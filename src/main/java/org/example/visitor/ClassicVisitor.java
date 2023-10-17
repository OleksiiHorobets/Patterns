package org.example.visitor;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


public class ClassicVisitor {
    interface BaseShape {
        <T> T accept(Visitor<T> visitor);
    }

    @Data
    @AllArgsConstructor
    static class Rectangle implements BaseShape {
        private double width;
        private double height;

        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    @Data
    @AllArgsConstructor
    static class Square implements BaseShape {
        private double edge;

        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    @Data
    @AllArgsConstructor
    static class Circle implements BaseShape {
        private double radius;

        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visit(this);
        }
    }


    interface Visitor<T> {
        T visit(Rectangle rectangle);

        T visit(Square square);

        T visit(Circle circle);
    }

    static class AreaVisitor implements Visitor<Double> {

        @Override
        public Double visit(Rectangle rectangle) {
            return rectangle.getHeight() * rectangle.getWidth();
        }

        @Override
        public Double visit(Square square) {
            return square.getEdge() * square.getEdge();
        }

        @Override
        public Double visit(Circle circle) {
            return circle.getRadius() * circle.getRadius() * Math.PI;
        }
    }

    public static void main(String... args) {
        Visitor<Double> areaVisitor = new AreaVisitor();

        List<BaseShape> shapes = List.of(
                new Circle(5d),
                new Rectangle(10d, 2d),
                new Square(5d)
        );

        var totalArea = shapes.stream()
                .mapToDouble(el -> el.accept(areaVisitor))
                .sum();

        System.out.println(totalArea);
    }
}