package AccessControl_Packages_ObjectClass.Q3;

public class Main {

    public static void describe(Object o) {
        System.out.println("Animal Class: " + (o instanceof Animal));
        System.out.println("Dog Class: " + (o instanceof Dog));
        System.out.println("Object Class: " + (o instanceof Object));

        if(o != null) {
            System.out.println("Class Name: " + o.getClass().getName());
        } else {
            System.out.println("Object is null");
        }

        if(o instanceof Dog) {
            Dog d = (Dog) o;
            System.out.println("Type conversion successful");
        }

        System.out.println();
    }

    static void main() {
        describe(new Dog());
        describe(new Animal());
        describe("Hello World");
        describe(null);
    }
}
