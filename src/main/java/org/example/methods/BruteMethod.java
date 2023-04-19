package org.example.methods;

import org.example.model.Point;
import org.example.model.Rectangle;

import java.util.List;

public class BruteMethod extends Method{
    public BruteMethod(List<Rectangle> rectangles) {
        super(rectangles);
    }

    public BruteMethod() {
        super();
    }

    @Override
    public int solveForPoint(Point p) {
        int cnt = 0;
        for (Rectangle r : rectangles) {
            if (r.getLowerPoint().getY() <= p.getY() && r.getUpperPoint().getY() >= p.getY()
                && r.getLowerPoint().getX() <= p.getX() && r.getUpperPoint().getX() >= p.getX()) {
                cnt++;
            }
        }
        return cnt;
    }

    @Override
    public void addRectangle(Rectangle r) {
        rectangles.add(r);
    }

    @Override
    public void construct(List<Rectangle> rectangles) {
        this.rectangles = rectangles;
    }
}
