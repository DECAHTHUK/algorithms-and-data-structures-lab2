package org.example.benchmark;

import org.example.methods.BruteMethod;
import org.example.methods.LazyPersistentSegmentTreeMethod;
import org.example.methods.MapMethod;
import org.example.methods.Method;
import org.example.model.Point;
import org.example.model.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Benchmark {

    public interface Command {
        <T extends Method> void execute(T method, List<Rectangle> rectangles, List<Point> points);
    }

    public static <T extends Method> long benchmark(Command command, T method, List<Rectangle> rectangles, List<Point> points) {
        long timeBefore;
        long timeNow;
        long diff;
        int n = points.size();

        timeBefore = System.nanoTime();

        command.execute(method, rectangles, points);

        timeNow = System.nanoTime();
        diff = timeNow - timeBefore;
        return diff;
    }

    public static class TestFindPointsPrepared implements Command {
        public <T extends Method> void execute(T method, List<Rectangle> rectangles, List<Point> points) {
            for (Point point : points) {
                method.solveForPoint(point);
            }
        }
    }

    public static class TestConstruct implements Command {
        public <T extends Method> void execute(T method, List<Rectangle> rectangles, List<Point> points) {
            method.construct(rectangles);
        }
    }

    public static void runTestCases(Method brute, Method map, Method tree){
        test(new TestConstruct(), brute, map, tree, 50);
        System.gc();
        test(new TestFindPointsPrepared(), brute, map, tree, 50);
        System.gc();
        test(new TestConstruct(), brute, map, tree, 100);
        System.gc();
        test(new TestFindPointsPrepared(), brute, map, tree, 100);
        System.gc();
        test(new TestConstruct(), brute, map, tree, 500);
        System.gc();
        test(new TestFindPointsPrepared(), brute, map, tree, 500);
        System.gc();
        test(new TestConstruct(), brute, map, tree, 1000);
        System.gc();
        test(new TestFindPointsPrepared(), brute, map, tree, 1000);
        System.gc();
        test(new TestConstruct(), brute, map, tree, 5000);
        System.gc();
        test(new TestFindPointsPrepared(), brute, map, tree, 5000);
        System.gc();
        test(new TestConstruct(), brute, map, tree, 10000);
        System.gc();
        test(new TestFindPointsPrepared(), brute, map, tree, 10000);
        System.gc();
        test(new TestConstruct(), brute, map, tree, 20000);
        System.gc();
        test(new TestFindPointsPrepared(), brute, map, tree, 20000);
        System.gc();
        test(new TestConstruct(), brute, map, tree, 50000);
        System.gc();
        test(new TestFindPointsPrepared(), brute, map, tree, 50000);
    }

    public static void test(Command command, Method brute, Method map, Method tree, int N) {
        List<Rectangle> rectangles = Generator.generateRectangles(N);
        List<Point> points = Generator.generatePoints(N);

        long a = Benchmark.benchmark(command, brute, rectangles, points);

        long b = 0;
        if (N <= 5000) b = Benchmark.benchmark(command, map, rectangles, points);

        long c = Benchmark.benchmark(command, tree, rectangles, points);

        if (command instanceof TestFindPointsPrepared) {
            a /= N;
            b /= N;
            c /= N;
        }
        System.out.println(command.getClass() + " for " + N + ":");
        System.out.println("Brute force: " + a);
        if (N <= 5000) System.out.println("Map:" + b);
        System.out.println("Tree: " + c);
        System.out.println("---------------------------------------------------");
    }
}
