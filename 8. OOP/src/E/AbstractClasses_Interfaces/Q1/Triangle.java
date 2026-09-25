package E.AbstractClasses_Interfaces.Q1;

public class Triangle extends Shape{

    double base;
    double height;

    Triangle(double b, double h) {
        this.base = b;
        this.height = h;
    }

    @Override
    double area() {
        return 0.5*base*height;
    }
}
