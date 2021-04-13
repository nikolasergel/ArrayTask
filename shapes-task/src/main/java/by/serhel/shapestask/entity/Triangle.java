package by.serhel.shapestask.entity;

import java.util.Objects;

public class Triangle extends Shape{
    private Point A;
    private Point B;
    private Point C;

    public Triangle(int id, Point a, Point b, Point c) {
        super(id);
        A = a;
        B = b;
        C = c;
    }

    public Point getA() {
        return A;
    }

    public Point getB() {
        return B;
    }

    public Point getC() {
        return C;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return A.equals(triangle.A) && B.equals(triangle.B) && C.equals(triangle.C);
    }

    @Override
    public int hashCode() {
        return Objects.hash(A, B, C);
    }
}
