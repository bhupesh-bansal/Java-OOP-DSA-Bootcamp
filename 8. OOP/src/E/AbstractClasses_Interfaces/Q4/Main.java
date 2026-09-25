package E.AbstractClasses_Interfaces.Q4;

public class Main {
    static void main() {
        Greeter greeter = new Greeter();
        greeter.greet();
        greeter.farewell();

        MathUtils.Validator validator = new PositiveValidator();

        System.out.println(validator.isValid(9));
        System.out.println(validator.isValid(0));
        System.out.println(validator.isValid(-9));
    }


}
