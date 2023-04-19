package org.example;

import org.example.benchmark.Benchmark;
import org.example.benchmark.Generator;
import org.example.methods.BruteMethod;
import org.example.methods.LazyPersistentSegmentTreeMethod;
import org.example.methods.MapMethod;
import org.example.methods.Method;
import org.example.model.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Benchmark.runTestCases(new BruteMethod(), new MapMethod(), new LazyPersistentSegmentTreeMethod());
        System.gc();
        Runtime runtime = Runtime.getRuntime();

        System.out.println("--------------------------------------");
        System.out.println("Memory consumption:");
        long memBefore = runtime.totalMemory() - runtime.freeMemory();
        List<Rectangle> rectangle = Generator.generateRectangles(5000);
        Method method = new LazyPersistentSegmentTreeMethod();
        Benchmark.benchmark(new Benchmark.TestConstruct(), method, rectangle, new ArrayList<>());
        long memAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Tree: " + (double) (memAfter - memBefore) / 1024 / 1024);

        method = new BruteMethod();
        System.gc();
        memBefore = runtime.totalMemory() - runtime.freeMemory();
        Benchmark.benchmark(new Benchmark.TestConstruct(), method, rectangle, new ArrayList<>());
        memAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Brute: " + (double) (memAfter - memBefore) / 1024 / 1024);

        method = new MapMethod();
        System.gc();
        memBefore = runtime.totalMemory() - runtime.freeMemory();
        Benchmark.benchmark(new Benchmark.TestConstruct(), method, rectangle, new ArrayList<>());
        memAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Map: " + (double) (memAfter - memBefore) / 1024 / 1024);
    }
}