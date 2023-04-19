package org.example.methods;

import org.example.model.Point;
import org.example.model.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SolveContest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Rectangle> rectangles = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Point lower = new Point(scanner.nextInt(), scanner.nextInt());
            Point upper = new Point(scanner.nextInt(), scanner.nextInt());
            rectangles.add(new Rectangle(lower, upper));
        }
        List<Point> points = new ArrayList<>();
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            points.add(new Point(scanner.nextInt(), scanner.nextInt()));
        }
        scanner.close();
        Method method = new LazyPersistentSegmentTreeMethod(rectangles);
        for (Point p : points) {
            System.out.print(method.solveForPoint(p) + " ");
        }
    }
}
