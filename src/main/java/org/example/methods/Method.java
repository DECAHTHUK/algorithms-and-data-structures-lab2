package org.example.methods;

import org.example.model.Point;
import org.example.model.Rectangle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Method {
    protected List<Rectangle> rectangles;

    public Method() {
    }

    public Method(List<Rectangle> rectangles) {
        this.rectangles = rectangles;
    }

    public abstract int solveForPoint(Point p);

    public abstract void addRectangle(Rectangle r);

    public abstract void construct(List<Rectangle> rectangles);

    public int compressPoint(List<Integer> list, int target, boolean lower) {
        if (target >= list.get(list.size() - 1)) {
            if (lower && target == list.get(list.size() - 1)) {
                return list.size() - 1;
            }
            return list.size();
        }
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (list.get(middle) > target) {
                right = middle - 1;
            } else if (list.get(middle) < target){
                left = middle + 1;
            } else {
                if (lower) return middle;
                while (list.get(middle) == target) {
                    middle++;
                }
                return middle;
            }
        }
        return left;
    }
}
