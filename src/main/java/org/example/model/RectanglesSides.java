package org.example.model;

public class RectanglesSides {
    private int sideX;

    private int sideYDown;

    private int sideYUp;

    private boolean leftX;

    public RectanglesSides() {
    }

    public RectanglesSides(int sideX, int sideYDown, int sideYUp, boolean leftX) {
        this.sideX = sideX;
        this.sideYDown = sideYDown;
        this.sideYUp = sideYUp;
        this.leftX = leftX;
    }

    public int getSideX() {
        return sideX;
    }

    public void setSideX(int sideX) {
        this.sideX = sideX;
    }

    public int getSideYDown() {
        return sideYDown;
    }

    public void setSideYDown(int sideYDown) {
        this.sideYDown = sideYDown;
    }

    public int getSideYUp() {
        return sideYUp;
    }

    public void setSideYUp(int sideYUp) {
        this.sideYUp = sideYUp;
    }

    public boolean isLeftX() {
        return leftX;
    }

    public void setLeftX(boolean leftX) {
        this.leftX = leftX;
    }
}
