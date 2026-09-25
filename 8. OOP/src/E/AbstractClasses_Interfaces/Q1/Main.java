package E.AbstractClasses_Interfaces.Q1;

public class Main {
    static void main() {
        Shape[] shapes = {
                new Circle(7),
                new Rectangle(6,5),
                new Triangle(3,4)
        };


        for(Shape s : shapes) {
            s.displayType(s.getClass().getSimpleName());
            System.out.println("Area: " + s.area());
            System.out.println();
        }
    }
}
