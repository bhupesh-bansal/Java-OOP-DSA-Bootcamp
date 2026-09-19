# Inheritance, Polymorphism, Encapsulation, Abstraction

---

## 1. The Four Pillars of OOP

Object-Oriented Programming is built on four core principles:

| Pillar | One-line meaning |
|---|---|
| **Inheritance** | A class acquiring properties/behavior of another class |
| **Polymorphism** | One interface, many implementations/forms |
| **Encapsulation** | Bundling data + methods, restricting direct access |
| **Abstraction** | Hiding implementation, exposing only essentials |

These four are almost always asked together in interviews — know the **definition + real-life example + code keyword** for each.

---

## 2. Inheritance

### 2.1 Definition
Inheritance is the mechanism by which one class (**child/derived/sub class**) acquires the properties (fields) and behaviors (methods) of another class (**parent/base/super class**).

**Real-life analogy:** A child inherits traits (money, values, physical features) from parents but can also have properties of their own.

### 2.2 Keyword: `extends`
```java
class Box {
    double length, width, height;
}

class WeighBox extends Box {   // WeighBox is child of Box
    double weight;              // additional property
}
```
- `WeighBox` automatically gets `length`, `width`, `height` **plus** its own `weight`.
- Only **one** class can be extended directly in Java (single inheritance per class — see §2.6 on why multiple class inheritance isn't allowed).

### 2.3 Constructors in Inheritance
- When a child object is created, the **parent's constructor runs first**, then the child's.
- If you want to pass values to the parent's constructor explicitly, use `super(...)`.

```java
class Box {
    double length, width, height;
    Box(double l, double w, double h) {
        length = l; width = w; height = h;
    }
}

class WeighBox extends Box {
    double weight;
    WeighBox(double l, double w, double h, double wt) {
        super(l, w, h);   // must be the FIRST statement in the constructor
        weight = wt;
    }
}
```

### 2.4 The `super` Keyword
`super` refers to the **immediate parent class** object. Three main uses:
1. `super(args)` → calls the parent class constructor (must be first line).
2. `super.variable` → access a parent class field hidden by a child field of the same name.
3. `super.method()` → call a parent class version of an overridden method.

### 2.5 The `private` Keyword & Inheritance
- Members declared `private` belong **only** to the class they're defined in.
- A child class **inherits** private members in memory but **cannot directly access** them (no dot-access) — they can only be reached through public/protected getters or methods defined in the parent.
- This is a core reason **Encapsulation** and **Inheritance** work together.

### 2.6 Types of Inheritance

| Type | Structure | Java Support |
|---|---|---|
| **Single** | One parent → one child | ✅ Yes |
| **Multilevel** | A → B → C (chain) | ✅ Yes |
| **Hierarchical** | One parent → multiple children | ✅ Yes |
| **Multiple** | One child ← multiple parents (classes) | ❌ Not via classes |
| **Hybrid** | Combination of the above | ✅ Only via interfaces |

**Why Java doesn't support multiple inheritance through classes — the Diamond Problem:**
If class `C` extends both `A` and `B`, and both `A` and `B` have a method with the same signature, the compiler cannot decide which version `C` should inherit → ambiguity. Java avoids this entirely for classes.

**How Java achieves multiple/hybrid inheritance instead → Interfaces**
```java
interface Flyable { void fly(); }
interface Swimmable { void swim(); }

class Duck implements Flyable, Swimmable {
    public void fly()  { System.out.println("Duck flies"); }
    public void swim() { System.out.println("Duck swims"); }
}
```
A class can `implement` any number of interfaces — this is how multiple inheritance of *type/behavior* is legally achieved in Java.

---

## 3. Polymorphism

### 3.1 Definition
"Poly" = many, "morph" = forms → **the ability of an object/method/entity to take on many forms.** The same interface (method name) behaves differently depending on context.

**Classic example — Shapes:**
```java
abstract class Shape {
    abstract double area();
}
class Circle extends Shape {
    double radius;
    double area() { return 3.14 * radius * radius; }
}
class Square extends Shape {
    double side;
    double area() { return side * side; }
}
```
Calling `shape.area()` gives a **different result depending on the actual object type** — that's polymorphism in action.

### 3.2 Types of Polymorphism

#### A) Compile-Time (Static) Polymorphism → **Method/Constructor Overloading**
- Same method **name**, but different **number, type, or order** of parameters.
- Return type alone is **not enough** to overload.
- Resolved by the **compiler**, before the program runs.

```java
class Calculator {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
    int add(int a, int b, int c) { return a + b + c; }
}
```
Constructor overloading works the same way — multiple constructors with different parameter lists.

#### B) Runtime (Dynamic) Polymorphism → **Method Overriding**
- A subclass provides its **own specific implementation** of a method already defined in its superclass.
- Requirements: **same name, same parameters, same (or covariant) return type**.
- Resolved at **runtime**, based on the actual object type — this is called **Dynamic Method Dispatch** (also known as **late binding**).

```java
class Shape {
    void draw() { System.out.println("Drawing a shape"); }
}
class Circle extends Shape {
    @Override
    void draw() { System.out.println("Drawing a circle"); }
}

Shape s = new Circle();  // reference type: Shape, object type: Circle
s.draw();                 // Output: "Drawing a circle" (decided at runtime)
```

### 3.3 How Java Decides Which Method to Call
- For **overloading** → decided at **compile time**, based on the arguments passed (early binding).
- For **overriding** → decided at **runtime**, based on the **actual object** the reference points to, NOT the reference's declared type (late binding / dynamic dispatch).

### 3.4 Rules of Overriding
- Access modifier in the child **cannot be more restrictive** than in the parent (e.g., can't override a `public` method as `private`).
- `final` methods **cannot** be overridden.
- `static` methods **cannot** be overridden — they can only be **hidden/redeclared**. Static method calls are resolved by the **reference type at compile time**, not the object type.
- `private` methods are **not inherited**, so they can't be "overridden" either — a same-named private method in a child class is a completely new method.

### 3.5 The `final` Keyword
Three uses:
1. `final` variable → becomes a constant (value can't change).
2. `final` method → **prevents overriding** in child classes.
3. `final` class → **prevents inheritance** entirely (no class can `extends` it), e.g., `String` class.

**Early Binding vs Late Binding**
| | Early Binding | Late Binding |
|---|---|---|
| Also called | Static binding | Dynamic binding |
| When resolved | Compile time | Runtime |
| Applies to | Overloading, `static`, `private`, `final` methods | Overridden (instance) methods |

---

## 4. Encapsulation

### 4.1 Definition
Wrapping **data (fields)** and the **methods** that operate on that data into a **single unit** (a class), while **restricting direct outside access** to the internal data.

### 4.2 How It's Achieved
```java
class Account {
    private double balance;   // hidden from outside

    public double getBalance() {      // getter
        return balance;
    }
    public void deposit(double amt) { // setter/controlled modifier
        if (amt > 0) balance += amt;
    }
}
```
- Fields are marked `private`.
- Access is only possible through `public` **getter/setter** methods.
- This lets you **validate** or **control** how data is changed (e.g., reject a negative deposit).

### 4.3 Purpose
- **Data hiding** — internal state can't be corrupted from outside.
- Increases **security** and **control**.
- Reduces **coupling** — internal implementation can change without breaking external code.

---

## 5. Abstraction

### 5.1 Definition
**Hiding unnecessary implementation details** and showing only the **essential features/functionality** to the user.

### 5.2 How It's Achieved
- **Abstract classes** (`abstract` keyword, may contain abstract + concrete methods).
- **Interfaces** (fully abstract contracts, from Java 8+ can have default/static methods too).

```java
abstract class Payment {
    abstract void pay(double amount);  // WHAT to do, not HOW
}
class CardPayment extends Payment {
    void pay(double amount) {
        System.out.println("Paid via card: " + amount);
        // internal logic hidden from the caller
    }
}
```
The caller only needs to know `pay()` exists — not how card processing actually happens internally.

### 5.3 Real-Life Analogy
Driving a car: you use the steering wheel, accelerator, brake (essential interface) — you don't need to know the internal combustion/engine wiring (implementation detail).

---

## 6. Encapsulation vs Abstraction (Most Common Interview Confusion)

| Aspect | Encapsulation | Abstraction |
|---|---|---|
| Focus | **How** — wraps data + code together | **What** — hides complexity, shows essential behavior |
| Level | Implementation level | Design level |
| Achieved via | `private` fields + getters/setters | `abstract` classes, `interface` |
| Goal | Data hiding & security | Complexity hiding & simplicity |
| Analogy | A capsule/pill — contents wrapped inside | A car's dashboard — you see controls, not the engine |

**One-line distinction to remember:** *Abstraction hides complexity by design (what an object does); Encapsulation hides data by implementation (how it's protected).*

---

## 7. Quick-Revision Summary Sheet

- **Inheritance** → `extends`, `super`, single/multilevel/hierarchical/multiple(via interface)/hybrid, private members not directly inherited-accessible.
- **Polymorphism** → Compile-time (**overloading** — same name, different params, resolved by compiler) vs Runtime (**overriding** — same signature, resolved by object type at runtime via dynamic dispatch).
- **`final`** → constant variable / no override (method) / no inheritance (class).
- **`static`/`private` methods** → cannot be overridden (no runtime polymorphism for them).
- **Encapsulation** → private data + public getters/setters → data hiding.
- **Abstraction** → abstract class/interface → hides implementation, shows essential features.

---

## 8. Coding Practice Questions

**Q1. Shape Area Calculator (Overriding + Runtime Polymorphism)**
Create an abstract class `Shape` with an abstract method `area()`. Create `Circle`, `Rectangle`, and `Triangle` subclasses that override `area()`. In `main`, store them in a `Shape[]` array and print each area using a loop — demonstrate that the correct overridden method runs for each object type.

**Q2. Overloaded Calculator (Compile-Time Polymorphism)**
Create a class `Calculator` with an overloaded method `add()`:
- `add(int, int)`
- `add(double, double)`
- `add(int, int, int)`
  Also add an overloaded constructor that initializes a running total. Call all versions from `main` and explain (in a comment) why each call resolves to a specific version at compile time.

**Q3. Bank Account (Encapsulation + Inheritance + `super`)**
Create a class `Account` with `private` field `balance`, and methods `deposit()`, `withdraw()` (with validation — no negative balance allowed), and `getBalance()`. Create a subclass `SavingsAccount` that adds an `interestRate` field and a method `applyInterest()`. Use `super()` in the subclass constructor to initialize the parent's fields.

**Q4 (Bonus). Multiple Inheritance via Interface**
Create two interfaces `Flyable` (method `fly()`) and `Swimmable` (method `swim()`). Create a class `Duck` that implements both. Then try creating a class that `extends` two classes directly and observe/explain the compiler error — write a short note on why Java disallows this (diamond problem).

---

## 9. Interview Questions

**Conceptual**
1. What are the four pillars of OOP? Give a one-line definition and real-world example for each.
2. What is the difference between method overloading and method overriding?
3. What is dynamic method dispatch? How does it relate to runtime polymorphism?
4. Why doesn't Java support multiple inheritance through classes? How is it achieved instead?
5. What is the difference between abstraction and encapsulation?
6. What is data hiding, and how is it different from abstraction?
7. Can we override a `static` method in Java? Why or why not?
8. Can we override a `final` method? What are the three uses of the `final` keyword?
9. Are constructors inherited in Java? Why or why not?
10. What is the difference between `this` and `super`?
11. Can a `private` method be overridden? What actually happens if a child class defines a method with the same name and signature as a private method in the parent?
12. What is early binding vs late binding? Which type of polymorphism uses which?
13. Can an abstract class have a constructor? Can it have concrete (non-abstract) methods?
14. What's the difference between an abstract class and an interface? When would you use one over the other?
15. What is covariant return type in the context of method overriding?

**Scenario-based**
16. If a superclass reference points to a subclass object and you call an overridden method, which version executes? Why?
17. If both an overloaded and an overridden version of a method could match a call, how does Java decide which to run?
18. You have a `Shape` superclass and `Circle`, `Square` subclasses, each overriding `area()`. What design pattern/principle does this demonstrate, and why is it useful (e.g., for extensibility)?