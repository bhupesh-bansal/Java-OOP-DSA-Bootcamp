package E.AbstractClasses_Interfaces.Q3;

public class Main {
    static void main() {
        Vehicle car = new Vehicle();

        car.engine.start();
        car.engine.stop();

        car.upgradeEngine(new ElectricEngine());

        car.engine.start();
        car.engine.stop();
    }
}
