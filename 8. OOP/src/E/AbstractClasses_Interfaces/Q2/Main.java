package E.AbstractClasses_Interfaces.Q2;

public class Main {
    static void main() {
        Tablet tab = new Tablet();
        System.out.println("Tablet:");
        tab.play();
        tab.charge();
        tab.showBattery();
        System.out.println();

        Smartphone s = new Smartphone();
        System.out.println("Smartphone:");
        s.play();
        s.charge();
        s.showBattery();
        System.out.println();
    }
}
