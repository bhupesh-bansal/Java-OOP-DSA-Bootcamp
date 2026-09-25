package E.AbstractClasses_Interfaces.Q4;

public class Greeter implements A,B{

    @Override
    public void greet() {
        System.out.println("Hola");
    }

    @Override
    public void farewell() {
        System.out.println("Adios");
    }
}
