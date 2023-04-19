package org.example.model;

public class Node {
    private Node left;

    private Node right;

    private int sum;

    public Node() {
        this.left = null;
        this.right = null;
        this.sum = 0;
    }

    public Node(Node left, Node right, int sum) {
        this.left = left;
        this.right = right;
        this.sum = sum;
    }

    public Node(Node node) {
        this.left = node.left;
        this.right = node.right;
        this.sum = node.sum;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int findQuantity(int left, int right, int target) {
        if (right - left == 1) {
            return this.sum;
        }
        int middle = (left + right) / 2;
        if (target < middle) {
            if (this.left == null) {
                return this.sum;
            }
            return this.sum + this.left.findQuantity(left, middle, target);
        } else {
            if (this.right == null) {
                return this.sum;
            }
            return this.sum + this.right.findQuantity(middle, right, target);
        }
    }

    public Node addNode(int left, int right, int start, int end, boolean isLeft) {
        if (left >= end || right <= start) {
            return this;
        }
        if (this.left == null) {
            this.left = new Node();
        }
        if (this.right == null) {
            this.right = new Node();
        }
        if (start <= left && right <= end) {
            Node node = new Node(this);
            node.sum += (isLeft ? 1 : -1);
            return node;
        }

        int mid = (left + right) / 2;
        Node node = new Node(this);

        node.left = this.left.addNode(left, mid, start, end, isLeft);
        node.right = this.right.addNode(mid, right, start, end, isLeft);

        return node;
    }
}
