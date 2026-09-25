package E.AbstractClasses_Interfaces.Q3;

public class Vehicle {
    Engine engine = new PetrolEngine();

    void upgradeEngine(Engine newEngine) {
        this.engine = newEngine;
    }
}
