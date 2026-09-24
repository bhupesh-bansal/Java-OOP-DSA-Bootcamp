# Java OOP: Access Control, Packages & Object Class

---

# PART 1: ACCESS CONTROL (Access Modifiers)

## 1.1 What is access control?
- Controls **who can access** a class's data members (variables), methods and constructors.
- It is a core part of **encapsulation / data hiding**: data is protected against unwanted access or modification.
- Java has **4 access levels**: `private`, *default* (no keyword), `protected`, `public`.
- Two things decide access: **(a)** which package the accessing code is in, and **(b)** whether it is a subclass or not.

## 1.2 The four modifiers

### `private`
- Accessible **only inside the same class**.
- Not accessible from: another class in the same package, a subclass, or another package.
- Even in the **same file**, a different class cannot access it.
- **Use for sensitive data** (e.g. balance, password, age). Prevents direct, insecure modification.
- Standard pattern: make fields `private` and expose them through **getters/setters** (or set via a **constructor**). This gives controlled access, with validation possible in the setter.
- Direct access to internal data structure from outside is bad convention and poor security.

```java
class Account {
    private double balance;                  // hidden
    public Account(double b) { balance = b; }   // set through constructor
    public double getBalance() { return balance; }       // controlled read
    public void setBalance(double b) {                   // controlled write
        if (b >= 0) balance = b;
    }
}
// In another class: acc.balance = 100;  -> COMPILE ERROR
```

### default (package-private)
- **No keyword written.** This is what you get if you don't specify anything.
- Accessible **within the same package only** (any class in that package, not just the same class).
- **Not accessible** from a different package, even by a subclass.
- Use when you want members visible to classes in the same package but hidden from outsiders.
- *default* = accessible in same package; *private* = same class only. Don't mix them up.

### `protected`
- Accessible in: **same class + same package + subclasses (even in a different package)**.
- Not accessible from a **non-subclass in a different package**.
- Key subtlety: in a different package, a subclass can access a protected member **only through inheritance** (i.e. through `this`/its own object), **not** by creating an object of the parent class and accessing `obj.protectedMember`.
- Use when you want a member visible to child classes (extension/inheritance) but hidden from the outside world.

### `public`
- Accessible from **everywhere**: same class, same package, other packages, subclasses, anyone.
- Use for the **API** you want everyone to use (e.g. `main`, getters/setters, service methods).
- Lecture caution: making everything public is **dangerous** because data can be modified from anywhere ("everyone can change it").

## 1.3 Master table (memorize this)

| Modifier | Same class | Same package | Subclass (different package) | Other package (non-subclass) |
|---|:-:|:-:|:-:|:-:|
| `private` | Yes | No | No | No |
| default | Yes | Yes | No | No |
| `protected` | Yes | Yes | Yes (via inheritance) | No |
| `public` | Yes | Yes | Yes | Yes |

**Order of visibility (narrow -> wide):** `private` < default < `protected` < `public`

## 1.4 Which modifier to use when?
- **private** -> sensitive data, internal state you never want changed directly.
- **default** -> things needed only inside the package (package-internal helpers).
- **protected** -> things meant for child classes to use or override, hidden from the rest.
- **public** -> things meant to be used by everyone (the public interface).

## 1.5 Extra rules *(commonly asked)*
- **Top-level classes** can only be `public` or default. They cannot be `private` or `protected`. (Nested/inner classes can be any of the four.)
- **Method overriding:** the overriding method cannot have a **more restrictive** access than the parent method (you can widen `protected` -> `public`, not narrow it).
- `private` members are **not inherited** by subclasses (accessible only via public/protected methods of the parent).
- A `public` class must be in a file with the **same name** as the class; one file has at most one public top-level class.

---

# PART 2: PACKAGES

## 2.1 What is a package?
- A **group of related classes/interfaces** (think: a **folder** that holds Java files).
- Analogy: a folder containing files; a package contains classes.

## 2.2 Why packages? (benefits)
1. **Organization:** related classes grouped together.
2. **Avoid name conflicts:** two classes with the same name can exist in different packages.
3. **Reusability:** import and reuse existing classes.
4. **Maintainability:** easier to manage large projects.

## 2.3 Two types
1. **User-defined packages:** you create them (a folder structure like `travel/tours`).
2. **Built-in packages:** already provided by Java.

## 2.4 Creating and using a user-defined package
```java
// File: mypack/Demo.java
package mypack;              // must be the FIRST statement in the file

public class Demo {
    public void show() { System.out.println("Hello"); }
}
```
```java
// File: Main.java (other package/location)
import mypack.Demo;          // import the class

public class Main {
    public static void main(String[] args) {
        Demo d = new Demo();
        d.show();
    }
}
```
- The class must be `public` to be used from another package.
- `import` gives access to classes of another package. Alternative: fully qualified name `mypack.Demo d = new mypack.Demo();`.
- **Wildcard:** `import mypack.*;` imports all classes of `mypack` (but **not sub-packages**).
- "import" line is how you bring a class from a different package.
- Compile *(extra)*: `javac -d . Demo.java` creates the folder structure; run with `java mypack.Demo`.
- Classes in the same package don't need import. Different package = import required *and* member must be accessible (public / protected-via-inheritance).

## 2.5 Built-in (in-built) packages

| Package | Purpose | Examples |
|---|---|---|
| `java.lang` | **Language fundamentals.** Mandatory/core. **Imported automatically**, no import needed | `String`, `Math`, `Object`, `System`, `Integer`, `Thread`, `Exception` |
| `java.io` | **Input/Output**: file reading/writing, streams, readers/writers | `File`, `FileReader`, `BufferedReader`, `InputStream` |
| `java.util` | **Utilities**: the **Collection Framework** (data structures), plus helper classes | `ArrayList`, `HashMap`, `HashSet`, `Scanner`, `Random`, `Arrays`, `Date` |
| `java.applet` | Applets (small programs embedded in web pages), old technology *(deprecated in modern Java)* | `Applet` |
| `java.awt` | **Abstract Window Toolkit**: Graphical User Interface (GUI): buttons, windows, etc. | `Button`, `Frame`, `Label` |
| `java.net` | **Networking**: connecting over the internet/network | `URL`, `Socket`, `ServerSocket` |

Key points:
- `java.lang` = basics (arithmetic ops, core classes); **automatically imported**, which is why `String`/`System` work without import.
- `java.util` = data structures / collections.
- `java.io` = reading and writing data (input/output).
- You must `import java.util.*;` (or specific class) to use `Scanner`, `ArrayList` etc.

---

# PART 3: THE `Object` CLASS

## 3.1 Basics
- `java.lang.Object` is the **root (parent) of all classes** in Java.
- **Every class implicitly extends `Object`** (compiler adds `extends Object` automatically if you don't extend anything).
- If your class extends another class, it inherits from `Object` **indirectly** through that chain.
- Because every class extends `Object`, **every object** in Java has the `Object` methods available.
- Multiple inheritance: Java doesn't allow a class to directly extend two classes. Object-inheritance works only because it's **indirect/single chain** (Child -> Parent -> ... -> Object).

```java
class Demo { }                 // same as: class Demo extends Object { }
class Student extends Person { } // Student -> Person -> Object (indirect)
```

## 3.2 Important methods of `Object`
`toString()`, `hashCode()`, `equals()`, `getClass()`.

### (a) `toString()`
- Returns a **String representation** of the object.
- **Default behaviour:** `ClassName@hashcodeInHex` (e.g. `Demo@1b6d3586`). Useless to a reader.
- **Printing an object** with `System.out.println(obj)` internally calls `obj.toString()`.
- **Override** it to print meaningful content. Override `toString` with `@Override` and return a String of the fields.
- `String` class already overrides `toString` (that's why printing a string prints its text).

```java
class Student {
    String name; int roll;
    Student(String n, int r) { name = n; roll = r; }

    @Override
    public String toString() {
        return "Student{name=" + name + ", roll=" + roll + "}";
    }
}
// System.out.println(new Student("Ravi", 5));  ->  Student{name=Ravi, roll=5}
```

### (b) `hashCode()`
- Returns an **integer** representing the object (a numeric representation).
- Default: derived from the object's internal identity/memory address *(conceptually)*; different objects usually give different hash codes.
- Used by **hash-based collections** like `HashMap`, `HashSet`: the hash decides which bucket the object goes in.
- Demo: `Object o1 = new Demo(); System.out.println(o1)` printed the hash-code number; a second object `o2` gave a **different** number; when `o2 = o1` (same object) numbers are **same**.
- **Rule:** if two objects are `equals()`, they **must** have the same `hashCode()`. (Override both together.)

### (c) `equals()`
- `public boolean equals(Object obj)`.
- **Default `equals()` compares references** (same as `==`): true only if both variables point to the **same object**.
- Imp. point: `==` on objects checks whether **two references point to the same object**, not whether contents are equal.
- Two separate objects with identical field values -> default `equals` gives **false**. To compare content, **override `equals()`**.
- **`String.equals()` is already overridden** to compare *content*. That's the classic difference:
    - `==` -> same reference.
    - `equals()` -> same content (for String / wrappers / anything that overrides it).

```java
Demo a = new Demo(5);
Demo b = new Demo(5);
Demo c = a;
a == b        // false  (different objects)
a.equals(b)   // false  (default equals = reference check)
a == c        // true   (same object)
a.equals(c)   // true

String s1 = new String("hi");
String s2 = new String("hi");
s1 == s2       // false
s1.equals(s2)  // true   (String overrides equals)
```

**Overriding equals (and hashCode) properly:**
```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;                 // same reference
    if (!(o instanceof Student)) return false;  // type check
    Student s = (Student) o;
    return roll == s.roll && name.equals(s.name);
}
@Override
public int hashCode() {
    return java.util.Objects.hash(name, roll);
}
```

### (d) `getClass()`
- Returns the **runtime class** of the object (a `Class` object).
- Used to find the class name / class information: `obj.getClass().getName()`.
- Example: `System.out.println(o1.getClass())` -> prints `class Demo`.
- Different from `instanceof`.

### (e) `finalize()` *(mentioned briefly)*
- Called by the garbage collector before object destruction.**Don't go into detail**. It is **deprecated** in newer Java.

## 3.3 The `instanceof` operator
- **Checks if an object is an instance of a class** (or subclass / implementing interface). Returns `boolean`.
- Syntax: `obj instanceof ClassName`.
- Since every object is an instance of `Object`, `anything instanceof Object` -> `true`.
- Returns `true` for the class **and all its parent classes**.
- If `obj` is `null` -> always `false`.
- Used before **type casting** to avoid `ClassCastException`, and inside `equals()`.

```java
Student s = new Student("A", 1);
s instanceof Student   // true
s instanceof Object    // true
Object o = s;
o instanceof Student   // true (runtime type is Student)
```

### `getClass()` vs `instanceof`
| | `instanceof` | `getClass()` |
|---|---|---|
| Checks | class or any subclass (is-a) | **exact** runtime class |
| Child object vs parent type | true | false (if compared with `==` to parent's class) |

## 3.4 Quick facts to remember
- `Object` is in `java.lang`, so no import required.
- `println(obj)` -> calls `toString()`.
- `==` vs `equals()`: reference vs content (when overridden).
- Object is the top of every hierarchy; `Object o = anything;` always valid.

---

# CHEAT SHEET
- **Access:** private (class) < default (package) < protected (package + subclasses) < public (all).
- **Package:** folder of classes; first line `package x;`; use with `import`. `java.lang` auto-imported.
- **Built-ins:** lang (core), io (files), util (collections/utilities), awt (GUI), net (network), applet (old web apps).
- **Object:** root of all classes; methods: `toString`, `equals`, `hashCode`, `getClass`, (`finalize`).
- **`==` vs `equals`:** reference vs content. **`instanceof`:** is-a check.

---

# PRACTICE: CODING QUESTIONS

### Q1. Access modifiers across packages (predict compile errors)
Create two packages `pack1` and `pack2`.
- In `pack1`, class `A` has four fields: `private int a`, `int b` (default), `protected int c`, `public int d`.
- In `pack1`, another class `B` (same package) tries to read all four.
- In `pack2`, class `C extends A` and tries to read all four (a) via `this`, and (b) via `new A()`.
- In `pack2`, non-subclass class `D` creates `new A()` and tries to read all four.

For each access, state whether it compiles. Then fix the code so only valid accesses remain and print the values.

### Q2. Override `toString`, `equals`, `hashCode`
Create `Employee` (`id`, `name`). Override the three methods so that two employees with the same `id` and `name` are equal. Then:
1. Print an `Employee` object directly.
2. Compare two equal-content employees using `==` and `equals`.
3. Add both to a `HashSet<Employee>` and print its size (expected: 1). Then remove your `hashCode` override and observe the change.

### Q3. `getClass()` and `instanceof`
Create `Animal`, `Dog extends Animal`. Write a method `describe(Object o)` that:
- prints whether `o` is an `Animal`, a `Dog`, and an `Object` using `instanceof`,
- prints its exact class name using `getClass().getName()`,
- safely casts to `Dog` only if valid.

Call it with `new Dog()`, `new Animal()`, `"hello"` and `null`. Predict output first.

### Q4. Predict the output:
```java
String s1 = "java", s2 = "java", s3 = new String("java");
System.out.println(s1 == s2);
System.out.println(s1 == s3);
System.out.println(s1.equals(s3));
Object o = new Object();
System.out.println(o);            // format?
```

## Solution hints
**Q1:** B (same package): a -> error; b, c, d OK. C via `this`: a, b -> error; c, d OK. C via `new A().c` -> error (protected not accessible via parent object in another package); only `d` OK. D: only `d` OK.

**Q2 (core):**
```java
class Employee {
    int id; String name;
    Employee(int id, String name) { this.id = id; this.name = name; }
    @Override public String toString() { return "Employee[" + id + "," + name + "]"; }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee e = (Employee) o;
        return id == e.id && name.equals(e.name);
    }
    @Override public int hashCode() { return java.util.Objects.hash(id, name); }
}
```
Without `hashCode()`, the two equal objects usually land in different buckets, so the set size becomes 2.

**Q3 (core):**
```java
static void describe(Object o) {
    System.out.println(o instanceof Animal);
    System.out.println(o instanceof Dog);
    System.out.println(o instanceof Object);
    if (o != null) System.out.println(o.getClass().getName());
    if (o instanceof Dog) { Dog d = (Dog) o; }
}
```
`new Dog()`: true, true, true. `new Animal()`: true, false, true. `"hello"`: false, false, true. `null`: all false (skip `getClass` to avoid `NullPointerException`).

**Q4:** `true`, `false`, `true`, and `java.lang.Object@<hex hash>`. (String literals share one pooled object; `new String` creates a new one.)

---

# THEORY QUESTIONS

### Access control

1. **Name the four access modifiers and their scope.**

   private (class), default (package), protected (package + subclasses), public (everywhere).

2. **Difference between default and protected?**

   Both allow same-package access; protected also allows subclasses in *other* packages (through inheritance).

3. **Can a subclass in a different package access a protected member via a parent-class object?**
   
    No, only through inheritance (its own `this`/subclass reference).

4. **Why make fields private and use getters/setters?**
   
    Encapsulation: hide data, validate changes, keep internal representation changeable.

5. **Can a top-level class be private or protected?**
   
    No; only public or default.

6. **Can an overriding method reduce visibility?**
   
   No, it can only keep or widen it.

7. **Are private members inherited?**
   
    Not accessible in the child; the child can only reach them through non-private methods of the parent.

8. **What is the default access when no modifier is given?**
   
    Package-private.

### Packages

9. **What is a package and why use it?**
   
    A namespace grouping related classes; organization, avoiding name clashes, access control, reusability.

10. **Difference between built-in and user-defined packages?**
    
    Provided by Java (`java.util`) vs created by the developer.

11. **Which package is auto-imported?**
    
    `java.lang`.

12. **Does `import java.util.*;` import `java.util.concurrent`?**
    
    No, wildcards don't include sub-packages.

13. **Two classes with the same name in different packages: how do you use both?**

    Import one and use the fully qualified name for the other.

14. **Where must the `package` statement be?**
    
    First statement in the file (before imports).

15. **What are `java.io`, `java.net`, `java.awt` used for?**
    
    File I/O, networking, GUI.

### Object class

16. **What is the `Object` class?**
    
    Root of the Java class hierarchy; every class inherits from it.

17. **Does Java support multiple inheritance? How does `Object` fit?**
    
    Not for classes; a class has one direct parent and inherits from `Object` indirectly down the chain.

18. **What does the default `toString()` return?**
    
    `ClassName@hexHashCode`.

19. **Difference between `==` and `equals()`?**
    
    `==` compares references (or primitive values); `equals()` compares logical content if overridden (default = reference comparison).

20. **Why override `hashCode()` when overriding `equals()`?**
    
    Equal objects must have equal hash codes or hash-based collections (`HashMap`, `HashSet`) break.

21. **`instanceof` vs `getClass()`?**
    
    `instanceof` accepts subclasses; `getClass()` gives the exact runtime class.

22. **What does `null instanceof X` return?**
    
    `false`.

23. **Why does `System.out.println(obj)` print something like `Demo@15db9742`?**
    
    `toString()` isn't overridden, so `Object.toString()` is used.

24. **Why does `s1 == s2` differ for `"java"` vs `new String("java")`?**
    
    Literals are pooled/shared; `new` always creates a distinct object.
