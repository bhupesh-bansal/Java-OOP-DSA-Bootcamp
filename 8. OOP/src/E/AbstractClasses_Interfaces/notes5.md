# Java OOP — Abstract Classes & Interfaces

---

## 0. Quick Primer (skip if already comfortable)

- **Inheritance**: A class (`Child`) can reuse fields/methods of another class (`Parent`) using `extends`. `Child` gets everything `Parent` has, and can add more or change (override) behavior.
- **Overriding**: If `Child` redefines a method that already exists in `Parent` with the *same signature*, calling that method on a `Child` object runs the `Child` version, not the `Parent` version.
- **Runtime polymorphism / Dynamic method dispatch**: If you write `Parent p = new Child();` and call `p.someMethod()`, Java looks at the **actual object type** (`Child`) at runtime to decide which version of `someMethod()` to run — not the reference type (`Parent`). This is why overriding is powerful: the same line of code can behave differently depending on what object is actually stored in the variable.
- **Reference type vs Object type**: `Parent p = new Child();` — here `p`'s *declared/reference type* is `Parent` (this limits **what members you're allowed to call/access** through `p`), but its *actual/object type* is `Child` (this decides **which overridden version runs**).

Keep these three ideas in mind — abstract classes and interfaces are built entirely on top of them.

---

## 1. Why Doesn't Java Allow a Class to Extend Two Classes?

Imagine:
```java
class A { void fun() { System.out.println("A's fun"); } }
class B { void fun() { System.out.println("B's fun"); } }
class C extends A, B { }   // NOT legal in Java
```
If this were allowed, calling `new C().fun()` would be ambiguous — should it run A's version or B's version? Java has no rule to decide, so it simply **disallows a class from extending more than one class**. This is commonly called the **Diamond Problem**.

Keep this problem in mind — it's the reason both abstract classes (partially) and interfaces (fully) exist in a particular form.

---

## 2. Abstract Classes

### 2.1 Core Idea
Sometimes a parent class wants to say: *"Every subclass of mine must have this behavior, but I'm not going to define exactly how — that's your job."*

A method with **no body** is called an **abstract method** — it just declares *what* must exist (name, parameters, return type), not *how* it works.

```java
abstract class Parent {
    abstract void career(String name);   // no {} body at all — just a signature + semicolon
}
```

### 2.2 Rule #1 — Abstract Method ⇒ Abstract Class
> **If a class contains even one abstract method, the class itself must be declared `abstract`.**

```java
abstract class Parent {
    abstract void career(String name);
}
```
You cannot have a plain (non-abstract) class containing an abstract method — Java will give a compile error.

### 2.3 Child Classes Must Override Abstract Methods
Any **concrete** (non-abstract) subclass **must provide a body** for every abstract method it inherits — this is just normal *overriding*, using the exact same rules from the Overriding/Polymorphism primer above.

```java
class Son extends Parent {
    void career(String name) {
        System.out.println("I want to be a " + name);
    }
}
```
If `Son` does NOT override `career()`, then `Son` itself must also be declared `abstract` (and so on, down the chain, until some class finally implements it).

### 2.4 Why Bother with Abstract Methods?
Design clarity: if you already *know* every subclass **must** implement a certain method differently, forcing it via `abstract` is safer than just hoping developers remember to override a normal method.

**Real-world example:** An `Engine` class where a petrol engine, diesel engine, and electric engine each `start()`, `stop()`, and `accelerate()` completely differently — there's no sensible "default" behavior to put in the parent, so make it abstract.

### 2.5 You Cannot Create an Object of an Abstract Class
```java
Parent p = new Parent();   // COMPILE ERROR
```
**Why?** If Java allowed this, calling `p.career("x")` would try to run a method with no body — nothing to execute. So Java blocks object creation for any abstract class.

**But** — you CAN declare a variable of the abstract type and point it at a subclass object:
```java
Parent p = new Son();   // perfectly legal
p.career("Doctor");     // runs Son's version (dynamic dispatch, same as the primer above)
```
This is just polymorphism again: reference type = `Parent` (controls what you can call), object type = `Son` (decides which version actually runs).

### 2.6 Constructors in Abstract Classes
Surprisingly, **yes**, an abstract class CAN have a constructor — even though you can never call `new` on it directly.

**Why is this useful?** To initialize fields (including `final` fields) that every subclass needs. The subclass constructor calls it using `super(...)`:
```java
abstract class Parent {
    int age;
    Parent(int age) { this.age = age; }   // legal constructor
    abstract void career(String name);
}
class Son extends Parent {
    Son(int age) { super(age); }          // calling parent's constructor
    void career(String name) { System.out.println("I'll be a " + name); }
}
```
What is **NOT** allowed: putting `abstract` on a constructor itself (`abstract Parent(...)` — illegal). Constructors can never be abstract, only methods can.

### 2.7 Static Members in Abstract Classes
- **Abstract static methods are illegal.** Reasoning: static methods belong to the *class*, not to objects, so they are never overridden (no dynamic dispatch happens for them). Since "abstract" specifically means *"must be overridden by a subclass,"* it makes no sense to combine it with `static` (which can never be overridden).
- **Normal (non-abstract) static methods ARE allowed** inside an abstract class, and are called the usual way: `Parent.someStaticMethod()`.
- **Static variables are allowed** too — nothing special here.

### 2.8 Normal (Concrete) Methods Are Also Allowed
An abstract class isn't *purely* abstract — it can mix abstract methods with fully-implemented ("normal") methods. Subclasses inherit the normal ones as-is (or can still choose to override them, same as regular inheritance).

### 2.9 `final` + `abstract` Together = Illegal
- `final` on a class means "cannot be subclassed."
- `abstract` on a class means "must be subclassed to ever be used" (since it can't be instantiated directly).
- These two directly contradict each other, so `final abstract class X {}` will not compile.

### 2.10 The Limitation That Abstract Classes Don't Solve
A class can still only `extends` **one** other class (abstract or not) — abstract classes do **not** fix the multiple-inheritance problem from Section 1. If two unrelated abstract classes each provide a normal (bodied) method with the same name, you're right back to the ambiguity problem. This is exactly why **interfaces** exist.

---

## 3. Interfaces

### 3.1 What Is an Interface?
An interface is declared with the `interface` keyword instead of `class` (the file is still `.java`). Think of it as an **extremely pure abstract class**: by default, it can only declare *what* must be done, never *how*.

```java
public interface Engine {
    void start();
    void stop();
    void accelerate();
}
```

### 3.2 Default Rules for Interface Members
| Element | Default (unless you use Java 8+ features below) |
|---|---|
| Methods | `public` and `abstract` automatically |
| Variables | `public static final` automatically |

**Why `static final` for variables?** An interface can never have objects (see 3.3), and it has no constructor to run initialization code — so any variable must be given its final value immediately when declared, and shared at the class (not object) level. Hence `static` + `final`.

```java
public interface Engine {
    int MAX_SPEED = 200;   // actually: public static final int MAX_SPEED = 200;
}
```

### 3.3 You Cannot Create Objects of an Interface Either
Same reasoning as abstract classes — an interface's methods (by default) have no body, so there's nothing to run if you tried `new Engine()`. This is illegal. You can, however, hold an interface-typed reference pointing at an object of a class that implements it:
```java
Engine e = new PowerEngine();  // legal, same reference-type/object-type idea as before
```

### 3.4 Abstract Class vs Interface — Side-by-Side

| Aspect | Abstract Class | Interface |
|---|---|---|
| Keyword to use it | `extends` | `implements` (from a class); `extends` (interface-to-interface) |
| How many can one class use? | Only 1 (`extends` one class) | Many (`implements` multiple interfaces) |
| Method bodies | Mix of abstract + fully implemented methods | Only abstract by default (Java 8+ allows `default`/`static` bodies too) |
| Variables | Can be `final` or not, any modifier | Always `public static final` |
| Access modifiers on members | `private`/`protected`/`public` all allowed | `public` only, by default |
| Constructors | Allowed (used via `super(...)`) | Not allowed at all |
| Can one "implement" the other? | An abstract class **can** implement an interface | An interface **cannot** implement an abstract class |
| Do implementers need to be related? | Subclasses must sit in the same inheritance chain | Any unrelated classes can each implement the same interface |

### 3.5 Why Interfaces Solve the Multiple-Inheritance Problem
Recall the Car example: a `Car` needs `Engine` behavior, `Brake` behavior, and `MediaPlayer` behavior all at once. You can't `extends` three classes. But you CAN do:
```java
class Car implements Engine, Brake, Media {
    // must override every abstract method from all three interfaces
}
```
**Why doesn't this cause the same ambiguity as Section 1?** Because interfaces (by default) only provide method *signatures*, never bodies. There's nothing to be "ambiguous" about — `Car` writes exactly one body for each method name itself. The conflict from Section 1 only happens when two *bodies* compete; here there's just one body, written by `Car`.

### 3.6 Interfaces Don't Care About Class Hierarchy
With plain class inheritance, if a method needs to be shared across multiple classes, it must be pushed *up* into a common ancestor — meaning those classes are forced into the same family tree.

Interfaces remove this restriction: **two totally unrelated classes** (no shared parent at all) can each implement the same interface independently. E.g., a `Bird` class and an `Airplane` class could both implement a `Flyable` interface, even though they share no other relationship.

### 3.7 Performance Note
Calling a method through an interface-typed reference still uses dynamic dispatch (like Section 2.5) to figure out, at runtime, which class's implementation to run. This lookup has a small amount of overhead compared to calling a method directly (non-polymorphically). Not a big deal in most code, but worth knowing for performance-critical paths.

### 3.8 Better Design: Composition Instead of Multiple `implements` (Car/Engine Example)
Directly doing `class Car implements Engine, Media` can create subtle bugs. Example: if both `Engine` and `Media` declare a method called `stop()`, `Car` provides just **one** `stop()` body — but now calling `car.stop()` (meant to control music) might *also* stop the engine, since it's the same method name on the same object. That's confusing and dangerous (imagine stopping your car radio also killing the engine while driving!).

**Better approach — composition:** Instead of the `Car` class implementing the interfaces itself, create **separate small classes** for each capability, and have `Car` just **hold references** to them as fields:

```java
interface Engine { void start(); void stop(); }
class PetrolEngine implements Engine {
    public void start() { System.out.println("Petrol engine starting"); }
    public void stop()  { System.out.println("Petrol engine stopping"); }
}
class ElectricEngine implements Engine {
    public void start() { System.out.println("Electric engine starting"); }
    public void stop()  { System.out.println("Electric engine stopping"); }
}

class Car {
    private Engine engine = new PetrolEngine();   // default engine

    void start()          { engine.start(); }
    void stop()           { engine.stop(); }
    void upgradeEngine(Engine newEngine) {         // swap at runtime!
        this.engine = newEngine;
    }
}
```

**Why this is better:**
- No naming collisions — the `Car`'s `stop()` method for the engine is completely separate from, say, a `Media` object's `stop()` method (they live on different objects/fields).
- You can **swap behavior at runtime** without creating a brand-new `Car` object:
  ```java
  Car myCar = new Car();
  myCar.start();                              // "Petrol engine starting"
  myCar.upgradeEngine(new ElectricEngine());
  myCar.start();                              // "Electric engine starting" — same Car object!
  ```
- This pattern is generally known as **"composition over inheritance"** (sometimes called the **Strategy pattern**): instead of *being* an Engine (via `implements`), the `Car` *has* an Engine (via a field reference).

### 3.9 Interface-to-Interface Inheritance
An interface can extend another interface using `extends` (not `implements` — `implements` is only used when a **class** adopts an interface):
```java
interface A { void fun(); }
interface B extends A { void greet(); }

class MyClass implements B {
    public void fun()   { ... }   // from A
    public void greet() { ... }   // from B
}
```
Whichever class implements `B` must provide bodies for **both** `fun()` (inherited from A) and `greet()` (declared in B).

### 3.10 Side Note: Annotations Are Interfaces Internally
You may have seen `@Override` above method definitions. Custom annotations are created with the `@interface` keyword, and Java's built-in annotations (like `@Override`) are internally just special kinds of interfaces (with their own meta-annotations like `@Target`, `@Retention` attached).

### 3.11 Default Methods (Java 8+)
Starting in Java 8, an interface method CAN have a body, if you mark it `default`:
```java
interface Chargeable {
    void charge();
    default void showBattery() {
        System.out.println("Battery status unavailable");
    }
}
```
**Why was this added?** Imagine a widely-used interface implemented by 100 classes. If you add a brand-new *abstract* method to it, all 100 classes now fail to compile until they each add an implementation. A `default` method lets you add new functionality to an interface **without breaking existing implementers** — they simply inherit the default body if they don't care to override it.

**The conflict this can reintroduce:** If a class implements two interfaces, and **both** provide a `default` method with the *same signature*, Java doesn't know which default body to inherit — this is a compile error (essentially the Diamond Problem again, just for default bodies instead of full class bodies). The fix: the implementing class **must explicitly override** that method itself to resolve the ambiguity.

A class's own method implementation always overrides/wins over any interface default.

**Guideline:** Use `default` methods sparingly — mainly to evolve an existing interface safely, not as a general design tool for everyday interfaces.

### 3.12 Static Methods in Interfaces
Static methods inside interfaces **must have a body** (they cannot be abstract). Reasoning: static methods are never inherited or overridden — they can only be called directly via the interface's name: `Chargeable.someStaticMethod()`. Since there's no override to worry about, there's no reason to leave the body empty.

### 3.13 Rule: Overriding Access Modifiers Can Only Get *Less* Restrictive
> **An overriding method's access modifier must be the same as, or less restrictive than, the original method's.**

Since interface methods are `public` by default, any class overriding them **must also mark the method `public`** — there is nothing less restrictive than `public` to fall back to, so this is the only choice.

More generally (this rule applies to overriding in *any* context, not just interfaces): if a parent method is `protected`, the overriding method in the child can be `protected` or `public`, but **never** `private` or default/package-private (that would be *more* restrictive, which Java disallows).

### 3.14 Nested Interfaces
An interface can be declared **inside** a class:
```java
class A {
    public interface NestedInterface {
        boolean isOdd(int n);
    }
}

class B implements A.NestedInterface {
    public boolean isOdd(int n) { return (n & 1) == 1; }
}
```
**Only real difference from a top-level interface:** a *nested* interface can be declared `public`, `private`, or `protected` (whatever access you want, same as class members). A **top-level** interface (declared directly in a file, not inside a class) can only ever be `public` or default — never `private` or `protected`.

---

## 4. Quick Comparison Table (Cheat Sheet)

| Feature | Regular Class | Abstract Class | Interface |
|---|---|---|---|
| Can create objects? | Yes | No | No |
| Multiple inheritance? | No | No (only 1 `extends`) | Yes (multiple `implements`) |
| Method bodies | All methods have bodies | Mix of abstract + concrete | Abstract by default; `default`/`static` can have bodies (Java 8+) |
| Constructors? | Yes | Yes (though can't `new` it directly) | No |
| Variable rules | Any modifier | Any modifier (final/non-final) | Always `public static final` |
| Member access modifiers | Any | Any | `public` only, by default |

---

## 5. Key One-Liners to Remember Before an Exam/Interview
1. Abstract method = signature only, no body → forces every concrete subclass to override it.
2. One abstract method inside a class ⇒ the whole class must be declared `abstract`.
3. Can't `new` an abstract class or an interface — but you CAN declare a reference variable of that type pointing at a concrete subclass/implementing-class object.
4. Abstract constructors: **illegal**. Normal constructors inside an abstract class: **legal** (called via `super(...)`).
5. Abstract static methods: **illegal** (static ≠ overridable, so "abstract" makes no sense on them). Normal static methods inside abstract classes/interfaces: **legal**, but static methods in interfaces must have a body.
6. `final abstract class`: **illegal** — direct contradiction ("can't be subclassed" vs "must be subclassed").
7. Interface variables are always implicitly `public static final`.
8. Interface methods are `public abstract` by default (before Java 8 features are used).
9. `implements` = class adopting an interface. `extends` = class-to-class inheritance **and** interface-to-interface inheritance.
10. Overriding a method can only keep the same access modifier or make it **less restrictive** — never more restrictive.
11. `default` methods (Java 8+) let interfaces gain new methods without breaking old implementing classes — but two clashing defaults from different interfaces force the implementing class to override and resolve it manually.
12. Prefer composition (a class *holding* an interface-typed field) over a class directly implementing many interfaces, whenever you need swappable behavior at runtime.

---

## 6. Coding Practice Questions

**Q1. Shape Area Calculator (Abstract Class)**
Create an abstract class `Shape` with an abstract method `double area()` and a concrete method `void displayType(String type)` that prints the shape type. Create `Circle`, `Rectangle`, and `Triangle` classes extending `Shape`, each implementing `area()` appropriately. In `main`, create a `Shape[]` array holding different shape objects and print each one's area using dynamic method dispatch.

**Q2. Multiple Interface Implementation — Smart Device**
Create two interfaces: `Chargeable` (with method `void charge()`) and `Playable` (with method `void play()`). Create a class `Smartphone` that implements **both** interfaces. Add a `default` method `void showBattery()` in `Chargeable` that just prints a generic battery message. Then create a `Tablet` class that also implements both interfaces, but overrides `showBattery()` with its own message. Demonstrate calling all methods from `main`.

**Q3. Composition over Implementation — Vehicle Engine Swap**
Create an interface `Engine` with methods `start()` and `stop()`. Create two classes `PetrolEngine` and `ElectricEngine` implementing `Engine` with different print messages. Create a class `Vehicle` that **holds a reference** to an `Engine` (not implementing it directly) with a method `upgradeEngine(Engine newEngine)` to swap engines at runtime. Show in `main` that calling `start()`/`stop()` before and after an upgrade produces different output — **without creating a new `Vehicle` object**.

**Q4. (Interface inheritance + nested interface):**
Create interface `A` with abstract method `greet()`, and interface `B extends A` adding method `farewell()`. Implement both in a class `Greeter`. Separately, create a class `MathUtils` with a **nested interface** `Validator` having method `boolean isValid(int n)`. Implement it via a separate class to check if a number is positive.

---

## 7. Interview Questions

**Conceptual:**
1. Why doesn't Java support multiple inheritance with classes? How do interfaces solve this?
2. What is an abstract method, and what rule governs the class that contains it?
3. Why can't you instantiate an abstract class or an interface, but you *can* create a reference variable of that type?
4. Can an abstract class have a constructor? Why or why not, and how is it used if the class can't be instantiated?
5. Why are abstract *static* methods not allowed, but normal static methods in an abstract class are fine?
6. Why must all variables declared inside a Java interface be `public static final` by default?
7. What is the purpose of `default` methods introduced in Java 8? What problem do they solve, and what new problem can they introduce?
8. If two interfaces implemented by the same class both have a `default` method with the same signature, what happens? How do you resolve it?
9. Explain the rule about access modifiers when overriding a method (e.g., interface method overriding). Why can't an overriding method be more restrictive?
10. What's the practical difference between a class implementing multiple interfaces directly vs. holding interface-typed references internally (composition)? Which is generally better design, and why?
11. Can two unrelated classes (no common parent) implement the same interface? Why does this matter, compared to class inheritance?
12. Why is there a slight performance overhead when using interface-typed references and dynamic dispatch, compared to normal method calls?
13. What is a nested interface, and how does its allowed access modifiers differ from a top-level interface?
14. Explain how annotations like `@Override` relate to interfaces internally.
15. `final abstract class` — is this legal? Why or why not?

**Scenario-Based:**
16. You need a `Car` class to support Engine, Brakes, and Media Player behavior simultaneously. Why can't you use abstract classes here, and how would interfaces solve it cleanly?
17. Design a system where you can swap a vehicle's engine at runtime without creating a new object — what pattern would you use, and why does it avoid the "have to change the whole car" problem?
18. You add a new abstract method to an existing interface used by 50 classes in production. What are your two options to avoid breaking all 50 implementations, and what are the trade-offs?

---