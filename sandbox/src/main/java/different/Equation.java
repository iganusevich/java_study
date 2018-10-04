package different;

import static java.lang.StrictMath.sqrt;

public class Equation {
    private double a;
    private double b;
    private double c;

    private int num;

    public Equation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;

        double desc = b * b - 4 * a * c;
        if (desc > 0) {
            num = 2;
        } else if (desc < 0) {
            num = 0;
            } else {
            num = 1;

        }


    }

    public int rootNumber(){
        return num;
    }

}
