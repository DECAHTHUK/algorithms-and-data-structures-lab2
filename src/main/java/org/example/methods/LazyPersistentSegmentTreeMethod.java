package org.example.methods;

import org.example.model.Node;
import org.example.model.Point;
import org.example.model.Rectangle;
import org.example.model.RectanglesSides;

import java.util.*;

public class LazyPersistentSegmentTreeMethod extends Method{

    private List<Integer> compressedX = new ArrayList<>();

    private List<Integer> compressedY = new ArrayList<>();

    List<Node> roots = new ArrayList<>();

    List<Integer> compressedRoots = new ArrayList<>();

    public LazyPersistentSegmentTreeMethod(List<Rectangle> rectangles) {
        super(rectangles);
        compressSets();
        build();
    }

    public LazyPersistentSegmentTreeMethod() {
        super();
    }

    @Override
    public int solveForPoint(Point p) {
        if (p.getX() < compressedX.get(0) || p.getY() < compressedY.get(0) || p.getX()  > compressedX.get(compressedX.size() - 1) || p.getY() > compressedY.get(compressedY.size() - 1)) {
            return 0;
        }

        int compressedXPos = compressPoint(compressedX, p.getX(), false) - 1;
        int compressedYPos = compressPoint(compressedY, p.getY(), false) - 1;

        return roots.get(compressPoint(compressedRoots, compressedXPos, false) - 1).findQuantity(0, compressedY.size(), compressedYPos);
    }

    @Override
    public void addRectangle(Rectangle r) {
        rectangles.add(r);
        compressedX.addAll(List.of(r.getLowerPoint().getX(), r.getUpperPoint().getX(), r.getUpperPoint().getX() + 1));
        compressedY.addAll(List.of(r.getLowerPoint().getY(), r.getUpperPoint().getY(), r.getUpperPoint().getY() + 1));
        build();
    }

    @Override
    public void construct(List<Rectangle> rectangles) {
        this.rectangles = rectangles;
        this.compressedX = new ArrayList<>();
        this.compressedY = new ArrayList<>();
        this.roots = new ArrayList<>();
        this.compressedRoots = new ArrayList<>();
        compressSets();
        build();
    }

    public void compressSets() {
        for (Rectangle r : rectangles) {
            compressedX.addAll(List.of(r.getLowerPoint().getX(), r.getUpperPoint().getX(), r.getUpperPoint().getX() + 1));
            compressedY.addAll(List.of(r.getLowerPoint().getY(), r.getUpperPoint().getY(), r.getUpperPoint().getY() + 1));
        }
        compressedX = new ArrayList<>(new TreeSet<>(compressedX));
        compressedY =  new ArrayList<>(new TreeSet<>(compressedY));
    }

    public void build() {
        Node root = new Node();
        List<RectanglesSides> sides = new ArrayList<>();
        for (Rectangle r : rectangles) {
            sides.add(new RectanglesSides(compressPoint(compressedX, r.getLowerPoint().getX(), true),
                    compressPoint(compressedY, r.getLowerPoint().getY(), true),
                    compressPoint(compressedY, r.getUpperPoint().getY() + 1, true), true));
            sides.add(new RectanglesSides(compressPoint(compressedX, r.getUpperPoint().getX() + 1, true),
                    compressPoint(compressedY, r.getLowerPoint().getY(), true),
                    compressPoint(compressedY, r.getUpperPoint().getY() + 1, true), false));
        }

        sides.sort(Comparator.comparingInt(RectanglesSides::getSideX));
        for (RectanglesSides rs : sides) {
            root = root.addNode(0, compressedY.size(), rs.getSideYDown(), rs.getSideYUp(), rs.isLeftX());
            roots.add(root);
            compressedRoots.add(rs.getSideX());
        }
    }
}
