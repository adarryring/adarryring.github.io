package io.github.darryring.learn.effective.eq;

public class Point {
    private int x;

    public Point(int x) {
        this.x = x;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) { // getClass()
            return false;
        }
        return ((Point) obj).x == this.x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                '}';
    }
}
