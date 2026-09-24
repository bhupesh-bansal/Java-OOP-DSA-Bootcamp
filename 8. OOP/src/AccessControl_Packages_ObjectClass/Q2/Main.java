package AccessControl_Packages_ObjectClass.Q2;

import java.util.HashSet;

public class Main {
    static void main() {
        Employee e1 = new Employee(1, "Rahul");
        Employee e2 = new Employee(1, "Rahul");

        System.out.println(e1);

        System.out.println("==: " + (e1 == e2));
        System.out.println("equals: " + e1.equals(e2));

        HashSet<Employee> emp = new HashSet<>();

        emp.add(e1);
        emp.add(e2);

        System.out.println("HashSet Size: " + emp.size());
    }
}
