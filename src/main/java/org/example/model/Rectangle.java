package org.example.model;


public class Rectangle {
    private Point lowerPoint;

    private Point upperPoint;

    public Rectangle(Point lowerPoint, Point upperPoint) {
        this.lowerPoint = lowerPoint;
        this.upperPoint = upperPoint;
    }

    public Rectangle() {
    }

    public Point getLowerPoint() {
        return lowerPoint;
    }

    public void setLowerPoint(Point lowerPoint) {
        this.lowerPoint = lowerPoint;
    }

    public Point getUpperPoint() {
        return upperPoint;
    }

    public void setUpperPoint(Point upperPoint) {
        this.upperPoint = upperPoint;
    }
}
