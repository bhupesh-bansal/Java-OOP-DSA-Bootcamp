package E.AbstractClasses_Interfaces.Q2;

public class Smartphone implements Playable,Chargeable{
    @Override
    public void play() {
        System.out.println("The Media is playing on smartphone!");
    }

    @Override
    public void charge() {
        System.out.println("The smartphone is on charge!");
    }
}
