# Java Notes: Packages, Static, Inner Classes & Singleton

---

## 1. Packages

### 1.1 What is a Package?
A **package** in Java is simply a **folder/namespace** used to group related classes and interfaces together. It helps organize code, avoid naming collisions, and control access.

- Physically, a package = a directory on disk.
- Logically, a package = a namespace prefix for your classes.

### 1.2 Why Packages Are Needed
- **Two classes can have the same name** as long as they live in different packages (name collision avoidance).
    - Example: `com.company.Greeting` and `com.othercompany.Greeting` can co-exist.
- You **cannot** have two classes with the exact same name inside the **same** package — the compiler will throw an error (duplicate class).
- Packages give structure to large projects — e.g. `com.example.app.controllers`, `com.example.app.services`, etc.

### 1.3 Folder Structure Example
```
src/
 └── com/
      └── xyz/
           └── project/
                ├── Greeting.java
                └── Main.java
```
- Package declaration must be the **first line** of the `.java` file:
```java
package com.xyz.project;
```

### 1.5 Same Class Name, Different Packages
- `com.xyz.folder1.Greeting` and `com.xyz.folder2.Greeting` are **different classes** even though the simple name `Greeting` is identical — because their **fully qualified names** differ.

---

## 2. The `import` Statement

### 2.1 Purpose
`import` lets you use a class from another package **without typing its fully-qualified name every time**.

```java
import com.xyz.folder2.Greeting;

public class Main {
    public static void main(String[] args) {
        Greeting g = new Greeting();
    }
}
```

Without the import, you'd have to write:
```java
com.xyz.folder2.Greeting g = new com.xyz.folder2.Greeting();
```

### 2.2 Default Imports
- Java **automatically imports** the `java.lang` package into every file (this is why you can use `String`, `System`, `Integer`, etc. without importing them explicitly).

### 2.3 What Import Does NOT Do
- Import does **not** bring in nested/sub-packages automatically. Importing `com.xyz.*` does not import `com.xyz.sub.*`.
- Only classes marked `public` (or accessible per access modifier rules) can be imported and used from outside their package.

---

## 3. The `static` Keyword

### 3.1 Core Idea
`static` means a member (variable or method) **belongs to the class itself, not to any individual object**.

- Normal (**instance**) members: each object gets its **own copy**.
- **Static** members: **shared** — only **one copy exists**, no matter how many objects are created.

### 3.2 Static Variables — Classic Example: `population`
Imagine a `Human` class. Every object created (`kunal`, `rahul`, `arpit`, ...) is a distinct human — but "how many humans exist" is **not a property of any one human**; it's a property of the whole population.

```java
class Human {
    String name;
    static int population = 0;   // shared across ALL Human objects

    Human(String name) {
        this.name = name;
        population++;            // increments the single shared copy
    }
}
```

- `Human.population` is accessed via the **class name**, not an object (though Java also *permits* `objectRef.population`, it's discouraged because it's misleading — static doesn't belong to the object).
- Every time a new `Human` object is constructed, the **same** `population` variable is incremented — it is **not reset or duplicated** per object.
- Key property: a static variable's value is **not tied to / not dependent on any single object's state**. It reflects something true about the **class as a whole**.

### 3.3 Static Methods
A **static method**:
- Belongs to the class, can be called **without creating an object**.
- Example: `main()` method is always `public static void main(String[] args)` — this is *why* Java can run your program without you manually creating an object first.

```java
class Demo {
    public static void main(String[] args) {
        System.out.println("Hello World"); // runs without any object of Demo
    }
}
```

### 3.4 Rule: Static Members Cannot Depend on Object State
- A static method/variable **does not depend on / is not associated with** any particular object.
- Therefore: **inside a static context (static method), you cannot directly use non-static (instance) variables or methods** — because at the moment a static method runs, **no object may even exist yet**, so there's nothing for "this instance" to refer to.

```java
class Test {
    int x = 10;          // instance variable

    static void show() {
        // System.out.println(x);  ❌ ERROR — x needs an object to exist
    }
}
```

### 3.5 Rule: Non-static Members CAN Use Static Members
- The reverse is fine: **instance (non-static) methods can freely access static variables/methods**, since by the time an instance method runs, the class (and its static members) is already loaded.

```java
class Test {
    static int count = 0;
    void increment() {
        count++;   // ✅ fine — non-static accessing static
    }
}
```

### 3.6 `this` Keyword Inside Static Context
- `this` refers to **the current object** — but a static method may run with **no object in existence**.
- Hence: **`this` cannot be used inside a static method.** It has no meaningful reference point without an instance.

### 3.7 Static Initialization / Default Values
- Static variables get **default values** automatically if not explicitly initialized (`0` for int, `null` for objects, etc.), just like instance variables.
- You can initialize them directly at declaration:
```java
static int a = 5;
```
- You can also use a **static initializer block** — runs **once**, when the class is **first loaded** (i.e., when the JVM loads the class, typically triggered by the first object creation or first static access) — **not once per object**:
```java
class Block {
    static int a;
    static {
        a = 20;
        System.out.println("Static block ran"); // prints ONLY ONCE
    }
}
```
- Even if you create multiple objects of `Block`, the static block executes **only the first time** the class is loaded — subsequent object creations do **not** re-run it.

---

## 4. Inner Classes

### 4.1 What Is an Inner Class?
A class **defined inside another class**.

```java
class Outer {
    class Inner {
        // ...
    }
}
```

### 4.2 Key Rule: Inner Class Cannot Exist Independently of Outer Class
- A **non-static inner class** cannot be instantiated **without** an instance of the outer class — it is tied to (dependent on) an outer object.
```java
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();
```

### 4.3 Static vs Non-static Inner Classes
- A **static nested class** *can* be created without needing an instance of the outer class (similar logic to static members in general — not dependent on any object).
- A **non-static (inner) class** requires the outer class object to exist first, since it's conceptually "part of" a specific outer instance.

### 4.4 Why This Matters
- This mirrors the static-vs-instance rule from Section 3: static = independent of any object; non-static = tied to a specific object's existence.

---

## 5. Singleton Class Pattern

### 5.1 What Is a Singleton?
A design pattern that ensures **only ONE instance/object of a class can ever be created** for the entire application.

### 5.2 How to Implement It
1. **Make the constructor `private`** — this prevents any code *outside* the class from creating new objects using `new`.
```java
class Singleton {
    private static Singleton instance = null;

    private Singleton() {
        // private constructor — no external instantiation allowed
    }
}
```
2. **Provide a public static method** (commonly `getInstance()`) that:
    - Checks if an instance already exists.
    - If **not**, creates the **one and only** object.
    - If it **does** exist, simply returns the existing object (does NOT create a new one).
```java
class Singleton {
    private static Singleton instance = null;

    private Singleton() { }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```
3. Usage:
```java
Singleton s1 = Singleton.getInstance();
Singleton s2 = Singleton.getInstance();
// s1 and s2 refer to the SAME single object
```

### 5.3 Why `getInstance()` Must Be Static
- It needs to be callable **before** any object exists (that's the whole point — it's what *creates* the first object), so it must belong to the class, not an instance → hence `static`.

### 5.4 Why the Constructor Is Private
- If the constructor were public, any external code could call `new Singleton()` freely and create multiple objects — defeating the entire purpose of the pattern.

---

## Quick-Recall Summary Table

| Concept | Belongs To | Needs Object? | Key Rule |
|---|---|---|---|
| Instance variable/method | Object | Yes | Each object has its own copy |
| Static variable/method | Class | No | Shared, single copy across all objects |
| `this` keyword | Object | Yes | Cannot be used in static context |
| Static block | Class | No | Runs once, at class loading |
| Non-static inner class | Outer object | Yes | Needs `outer.new Inner()` |
| Static nested class | Outer class | No | Can be created independently |
| Singleton | Class | Controlled | Private constructor + static `getInstance()` |

---

# Practice: Coding Questions

**Q1. Object Counter (Static Variable)**
Write a `Student` class that keeps track of the total number of `Student` objects created so far using a static variable. Add a static method `getTotalStudents()` that returns this count. Create 4 objects and print the total.

**Q2. Static Block Execution**
Write a class `Config` with a static variable `appVersion` initialized inside a **static block**. Print a message from within the static block. Create 3 objects of `Config` in `main()` and verify (via console output) that the static block executes only **once**.

**Q3. Implement a Thread-unsafe Singleton (then discuss improvements)**
Implement a `Logger` class as a Singleton (private constructor + static `getInstance()`). In `main()`, get the instance twice via `getInstance()` and use `==` to prove both references point to the same object. *(Bonus: discuss why this naive implementation is not thread-safe, and how you'd fix it — e.g., synchronized method, double-checked locking, or eager initialization.)*

---

# Theory Questions

**Conceptual / Theory**
1. What is the difference between a static and a non-static (instance) member in Java?
2. Why can't you access a non-static variable directly from within a static method?
3. Why can a non-static method access static variables/methods, but not vice versa?
4. Can you use the `this` keyword inside a static method? Why or why not?
5. When exactly does a static initializer block execute, and how many times does it run over the lifetime of a program?
6. What's the difference between a static nested class and a (non-static) inner class?
7. Why must an inner (non-static) class always be tied to an instance of its outer class?
8. Explain what `System.out.println()` actually does internally — what is `out`, and why can you access it without creating a `System` object?
9. What is the default value of a static `int` variable if not explicitly initialized?
10. What is a package in Java, and why is the "reverse domain name" convention used for naming them?
11. Can two classes in *different* packages have the same class name? Can two classes in the *same* package?
12. What does the `import` statement actually do at compile time — does it copy code into your file?
13. Is `java.lang` imported automatically? What classes does this give you access to without an explicit import?

**Design Pattern (Singleton)**
14. What is the Singleton design pattern, and when would you use it in real applications (e.g., logging, configuration, DB connection pool)?
15. Why is the constructor made `private` in a Singleton class?
16. Why must the `getInstance()` method be `static`?
17. Is the classic (lazy) Singleton implementation thread-safe? What problem can occur if two threads call `getInstance()` simultaneously the first time?
18. How would you fix a Singleton implementation to make it thread-safe? (Expected mention: `synchronized` keyword, double-checked locking, or eager instantiation using `static final`.)
19. What happens if you try to clone a Singleton object, or serialize/deserialize it? (Advanced — discuss how these can break the "only one instance" guarantee.)

**Code-Tracing**
20. Given a class with a static counter incremented in the constructor, if you create 5 objects, what is the final value of the counter, and why is it not reset per object?
21. Trace through code where a static block, an instance variable, and a constructor all exist — in what **order** do they execute when the first object is created? *(Expected order: static block → instance variable initializers → constructor body, with static block running only on first class load.)*