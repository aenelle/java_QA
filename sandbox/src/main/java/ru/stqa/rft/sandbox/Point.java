package ru.stqa.rft.sandbox;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
        public double distance(Point p0) {
            return Math.sqrt((p0.x - this.x) * (p0.x - this.x) + (p0.y - this.y) * (p0.y - this.y));
    }

}
