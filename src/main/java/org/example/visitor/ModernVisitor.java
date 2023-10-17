package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public class ModernVisitor {
  sealed interface BaseShape permits Rectangle, Circle, Square {
  }

  @Data
  @AllArgsConstructor
  static final class Rectangle implements BaseShape {
    private double width;
    private double height;
  }

  @Data
  @AllArgsConstructor
  static final class Square implements BaseShape {
    private double edge;

  }

  @Data
  @AllArgsConstructor
  static final class Circle implements BaseShape {
    private double radius;
  }


  public static void main(String[] args) {
    List<BaseShape> shapes = List.of(
        new Circle(5d),
        new Rectangle(10d, 2d),
        new Square(5d)
    );

    var totalArea = shapes.stream()
        .mapToDouble(ModernVisitor::countArea)
        .sum();

    System.out.println(totalArea);
  }

  public static Double countArea(BaseShape shape) {
    return switch (shape) {
      case Circle circle -> circle.getRadius() * circle.getRadius() * Math.PI;
      case Rectangle rectangle -> rectangle.getHeight() * rectangle.getWidth();
      case Square square -> square.getEdge() * square.getEdge();
    };
  }
}
