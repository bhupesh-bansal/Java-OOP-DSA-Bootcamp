package E.AbstractClasses_Interfaces.Q2;

public class Tablet implements Playable, Chargeable{
    @Override
    public void play() {
        System.out.println("The Media is playing on tablet!");
    }

    @Override
    public void charge() {
        System.out.println("The tablet is on charge!");
    }

    @Override
    public void showBattery() {
        System.out.println("The battery is 79%.");
    }
}
