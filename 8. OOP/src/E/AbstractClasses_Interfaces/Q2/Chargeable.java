package E.AbstractClasses_Interfaces.Q2;

public interface Chargeable {
    void charge();

    default void showBattery() {
        System.out.println("The battery is 69%.");
    }
}
