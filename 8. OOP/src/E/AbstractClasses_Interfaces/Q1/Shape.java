package E.AbstractClasses_Interfaces.Q1;

public abstract class Shape {
    abstract double area();

    void displayType(String type) {
        System.out.println("The shape is: " + type);
    }
}
