package org.example;

import org.example.benchmark.Benchmark;
import org.example.methods.BruteMethod;
import org.example.methods.LazyPersistentSegmentTreeMethod;
import org.example.methods.MapMethod;

public class Main {
    public static void main(String[] args) {
        Benchmark.runTestCases(new BruteMethod(), new MapMethod(), new LazyPersistentSegmentTreeMethod());
    }
}