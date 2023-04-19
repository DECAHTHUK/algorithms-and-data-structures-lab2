package org.example.methods;

import org.example.model.Point;
import org.example.model.Rectangle;

import java.util.*;

public class MapMethod extends Method{
    private int[][] map;

    private List<Integer> compressedX = new ArrayList<>();

    private List<Integer> compressedY = new ArrayList<>();

    public MapMethod(List<Rectangle> rectangles) {
        super(rectangles);
        compressSets();
        createMap();
    }

    public MapMethod() {
        super();
    }

    @Override
    public int solveForPoint(Point p) {
        if (p.getX() < compressedX.iterator().next() || p.getY() < compressedY.iterator().next() || p.getX() > compressedX.get(compressedX.size() - 1)
                || p.getY() > compressedY.get(compressedY.size() - 1)) {
            return 0;
        }
        p = new Point(compressPoint(compressedX, p.getX(), false), compressPoint(compressedY, p.getY(), false));
        if (p.getX() == compressedX.size() || p.getY() == compressedY.size()) {
            return 0;
        }
        return map[p.getX()][p.getY()];
    }

    @Override
    public void addRectangle(Rectangle r) {
        rectangles.add(r);
        compressedX.addAll(List.of(r.getLowerPoint().getX(), r.getUpperPoint().getX(), r.getUpperPoint().getX() + 1));
        compressedY.addAll(List.of(r.getLowerPoint().getY(), r.getUpperPoint().getY(), r.getUpperPoint().getY() + 1));
        createMap();
    }

    @Override
    public void construct(List<Rectangle> rectangles) {
        this.rectangles = rectangles;
        this.compressedX = new ArrayList<>();
        this.compressedY = new ArrayList<>();
        compressSets();
        createMap();
    }

    public void compressSets() {
        for (Rectangle r : rectangles) {
            compressedX.addAll(List.of(r.getLowerPoint().getX(), r.getUpperPoint().getX(), r.getUpperPoint().getX() + 1));
            compressedY.addAll(List.of(r.getLowerPoint().getY(), r.getUpperPoint().getY(), r.getUpperPoint().getY() + 1));
        }
        compressedX = new ArrayList<>(new TreeSet<>(compressedX));
        compressedY = new ArrayList<>(new TreeSet<>(compressedY));
    }

    public void createMap() {
        map = new int[compressedX.size()][compressedY.size()];
        for (Rectangle r : rectangles) {
            Point compressedLowerPoint = new Point(compressPoint(compressedX, r.getLowerPoint().getX(), false),
                    compressPoint(compressedY, r.getLowerPoint().getY(), false));
            Point compressedUpperPoint = new Point(compressPoint(compressedX, r.getUpperPoint().getX(), false),
                    compressPoint(compressedY, r.getUpperPoint().getY(), false));
            for (int i = compressedLowerPoint.getX(); i <= compressedUpperPoint.getX(); i++) {
                for (int j = compressedLowerPoint.getY(); j <= compressedUpperPoint.getY(); j++) {
                    map[i][j]++;
                }
            }
        }
    }
}
