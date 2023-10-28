package org.example.decorator;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Function;

public class Camera {
    private Function<Color, Color> filter = Function.identity();
    public Color shot(Color input) {
        return filter.apply(input);
    }

    @SafeVarargs
    public Camera(Function<Color, Color>... filters) {
        setFilters(filters);
    }

    @SafeVarargs
    private void setFilters(Function<Color, Color>... filters) {
        filter = Arrays.stream(filters)
                .reduce(Function.identity(), Function::andThen);
    }
}


class Main {
    public static void main(String[] args) throws FileNotFoundException {
        OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(Path.of("123").toFile()));

        printShot(new Camera(Color::brighter));
    }

    private static void printShot(Camera camera) {
        System.out.println(camera.shot(new Color(125, 125, 125)));
    }
}
