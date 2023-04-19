package org.example.benchmark;

import org.example.model.Point;
import org.example.model.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Generator {
    private static final int X_PRIME = 31;

    private static final int Y_PRIME = 47;

    public static List<Rectangle> generateRectangles(int N) {
        List<Rectangle> rectangles = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            rectangles.add(new Rectangle(new Point(10 * i, 10 * i), new Point(10 * (2 * N - i), 10 * (2 * N - i))));
        }
        return rectangles;
    }

    public static List<Point> generatePoints(int N) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            points.add(new Point((int) (Math.pow(X_PRIME * i, 31) % (20 * N)), (int) (Math.pow(Y_PRIME * i, 31) % (20 * N))));
        }
        return points;
    }
}
