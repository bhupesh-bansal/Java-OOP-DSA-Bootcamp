package AccessControl_Packages_ObjectClass.Q2;

import java.util.Objects;

public class Employee {
    int id;
    String name;

    public Employee (int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee id: " + id + ", Employee Name: " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if( obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Employee other = (Employee) obj;

        return id == other.id && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

