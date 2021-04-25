package by.serhel.shapestask.entity;

public class Point {
    private double x;
    private double y;
    private double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Point point = (Point) o;
        return x == point.x && y == point.y && point.z == z;
    }

    @Override
    public int hashCode() {
        int c = 31;
        int result = c;
        result = c * result + (int)x;
        result = c * result + (int)y;
        result = c * result + (int)z;
        return result;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Point {");
        builder.append("\nx=").append(x);
        builder.append("\ny=").append(y);
        builder.append("\nz=").append(z);
        builder.append("\n}");
        return builder.toString();
    }
}
