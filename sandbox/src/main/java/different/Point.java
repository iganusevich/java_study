package different;

import static java.lang.Math.sqrt;

public class Point {
    public double a;
    public double b;

    public Point(double a, double b){
        this.a = a;
        this.b = b;
    }

    public double distance(Point p2) {
        return distance(this, p2);
    }

    public static double distance(Point p1, Point p2){
        return sqrt((p1.a - p2.a)*(p1.a - p2.a) + (p1.b - p2.b)*(p1.b - p2.b));
    }



}
