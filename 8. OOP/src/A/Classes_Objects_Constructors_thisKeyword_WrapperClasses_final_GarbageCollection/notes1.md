# Java OOP
*(Classes, Objects, Constructors, this keyword, Wrapper Classes, final, Garbage Collection)*

---

## 1. Why Object-Oriented Programming?

Before OOP, imagine you want to store data for a **student**: roll number, name, and marks.

**Problem with primitive/simple variables:**
- If you use separate variables for every student (`rollNo1, name1, marks1, rollNo2, name2, marks2 …`), it becomes extremely difficult to manage as the number of students grows.
- There's no clean way to group **related properties together** for a single real-world entity.

**The solution — create your own data type.**
Just like Java gives you built-in data types (`int`, `float`, `String`), you can define **your own custom data type** that groups multiple properties together. This is done using a **class**.

---

## 2. What is a Class?

> **Class = A group of properties and functions (methods) bundled together, representing a logical template/blueprint.**

- A class lets you define **your own data type**.
- Example: To represent a Student, instead of having 3 separate unrelated variables, you create **one class** `Student` that contains:
    - `rollNumber`
    - `name`
    - `marks`

**Naming Convention:** Class names conventionally start with a **capital letter** (e.g., `Student`, `Car`).

### Class Example — Real World Analogies
- **Car** → Properties: price, number of seats, engine type (petrol/diesel/electric) → Functions: start(), stop()
- **Human** → Properties: 2 hands, 2 legs, mouth, hair, body shape, etc.

Different companies use the **same class template** (blueprint) to make different objects:
- Maruti, Ferrari, Honda all use the "Car" class template but create **different car objects**, each with different property *values* (different price, different engine, different seat count) — but the **structure/blueprint stays the same**.

### Key idea
A class is like a **template / logical construct** — a "form" or "piece of paper" that defines *what properties and functions every object of this type must have*, but the class itself does **not physically exist in memory** as a usable entity — it is only a design/blueprint.

---

## 3. What is an Object?

> **Object = A physical/real instance of a class. It is the actual entity created using the class template, and it occupies space in memory.**

- While a class is a **logical construct**, an object is a **physical reality**.
- An object is also called an **instance** of the class, and the process of creating it is called **instantiation**.
---

## 4. Class vs Object — Key Differences

| Class | Object |
|---|---|
| Logical construct / blueprint | Physical reality |
| Does NOT occupy memory (no space) | Occupies space in memory |
| Only ONE class definition needed | MANY objects can be created from one class |
| Defines *what* properties/functions exist | Holds actual *values* for those properties |

---

## 5. Properties (Characteristics) of an Object

Every object has **three defining characteristics**:

1. **State** — the values currently held by the object's properties (e.g., roll number = 13, name = "Kunal", marks = 92.75)
2. **Identity** — what makes an object unique/distinct from another object, even if they belong to the same class (each object has its own separate identity/memory reference)
3. **Behavior** — the actions/functions the object can perform (e.g., a function like `greeting()` that prints "Hello, my name is …")

### Example
For a `Student` class, every function defined inside the class (like a `greeting()` method) is **shared** by structure, but when called on **different objects**, it behaves according to that specific object's own data (state) — e.g., Kunal's greeting will print "Hello my name is Kunal", Rahul's greeting will print "Hello my name is Rahul".

---

## 6. Defining a Class — Example

```java
class Student {
    int rollNumber;
    String name;
    double marks;
}
```

- `Student` → Class name (capital letter convention)
- `rollNumber`, `name`, `marks` → **Instance variables / properties**
- These properties are called **instance variables** because each **instance (object)** of the class gets its own separate copy of these variables.

**Important Rule:** Instance variables should be declared **inside the class**, but **outside any method or constructor**.

---

## 7. How to Create an Object — the `new` Keyword

To create an object, Java uses the **`new`** keyword.

```java
Student kunal = new Student();
```

Breaking this down:
- `Student` (left side) → Data type of the reference variable (the type is the class name)
- `kunal` → **Reference variable name**
- `new` → Keyword responsible for allocating memory
- `Student()` → Calls the **constructor** of the class
- `new Student()` → Allocates memory in the heap and returns a **reference (address)** to that memory
- This reference (address) is then stored inside the reference variable `kunal`

### Why do we need `new`?
- `new` is the operator responsible for **dynamic memory allocation** — it allocates memory for the object **at runtime** (not at compile time).
- It returns a **reference** pointing to the newly created object in memory.
- Without `new`, an object reference declared alone (`Student kunal;`) is **not initialized** — its value is `null` by default (for object/reference types).

---

## 8. Accessing Instance Variables — the Dot (`.`) Operator

Once an object is created, you access its properties and methods using the **dot operator (`.`)**.

```java
Student kunal = new Student();

kunal.rollNumber = 13;
kunal.name = "Kunal Kushwaha";
kunal.marks = 84.50;

System.out.println(kunal.rollNumber); // prints 13
System.out.println(kunal.name);       // prints Kunal Kushwaha
```

- `referenceVariable.propertyName` → Links the reference variable to a **specific instance variable inside that particular object**.
- The dot operator is formally described as: it allows you to access any instance variable/member (property or method) that belongs to a particular object.

**Important Note:** You can only access properties/functions that are **already defined inside the class template**. You cannot invent a new property on the fly — it must exist in the class first.

---

## 9. Default Values of Instance Variables

If you create an object but **don't assign values**, Java automatically assigns **default values** to instance variables (this does NOT apply to local variables, which have no default value):

| Data Type | Default Value |
|---|---|
| `int` | 0 |
| `double` / `float` | 0.0 |
| `boolean` | false |
| `String` (or any object/reference type) | `null` |

Example:
```java
Student kunal = new Student();
System.out.println(kunal.name);   // prints: null
System.out.println(kunal.marks);  // prints: 0.0
System.out.println(kunal.rollNumber); // prints: 0
```

---

## 10. Multiple Objects — Each is Independent

Each object created from a class is **completely independent** and holds its **own separate copy** of the instance variables.

```java
Student kunal = new Student();
kunal.rollNumber = 13;

Student rahul = new Student();
rahul.rollNumber = 201;
```

- Changing `kunal`'s properties does **not** affect `rahul`'s properties, even though both are of type `Student`.
- Every single object gets a distinct set of property values → hence the term **instance variable** (a variable specific to each instance).

### Reference Assignment — Important Gotcha
```java
Student student1 = new Student();
Student student2 = student1;   // student2 now points to the SAME object as student1
```
- Here, `student2` is **not** a new/independent object.
- `student2` simply points to the **same memory location** as `student1`.
- So, if you change a property using `student1`, the same change will be visible when you access it via `student2` — because **both reference variables point to the exact same object in memory**.
- This is a very important concept and a common source of confusion — assigning one reference variable to another does **not** create a copy of the object.

---

## 11. Dynamic Memory Allocation

- Objects in Java are allocated memory **dynamically at runtime**, not at the time the program is compiled.
- **Compile time**: Your Java source code (`.java` file) is converted into bytecode.
- **Runtime**: When the compiled program is actually executing (running) — this is when objects get created and memory is allocated in the **RAM (heap memory)**.
- The reference variable internally holds the **memory address (location)** of where the actual object is stored — this is conceptually similar to a pointer in C/C++, though Java does not expose raw pointer manipulation to the programmer.

---

## 12. What is a Constructor?

> **Constructor = A special function inside a class, used to initialize an object when it is created.**

- A constructor defines **what happens when an object is created** (i.e., what initial values the object's properties get).
- Its **main job is to allocate/initialize** the object properly.
- A constructor is called **automatically** the moment you use the `new` keyword.

### Constructor Rules
1. The constructor name **must be exactly the same** as the class name.
2. A constructor **does NOT have a return type** (not even `void`).

```java
class Student {
    int rollNumber;
    String name;
    double marks;

    // Constructor
    Student() {
        System.out.println("New student object created");
    }
}
```

---

## 13. Default Constructor

- If you **do not write any constructor** inside your class, Java automatically provides a **default constructor** behind the scenes.
- This default constructor takes **no arguments** and simply initializes instance variables with their default values (0, 0.0, false, null as applicable).

```java
class Student {
    int rollNumber;
    String name;
    double marks;
    // No constructor written -> Java inserts a default constructor automatically
}

Student kunal = new Student(); // This calls the auto-generated default constructor
```

**Key takeaway:** The moment you explicitly write **any** constructor of your own, Java will **NOT** auto-generate the default no-argument constructor for you anymore.

---

## 14. Parameterized Constructor (Writing Your Own Constructor)

You can define your own constructor that accepts arguments, so that instead of assigning properties one by one via the dot operator, you can set them all at once, during object creation.

```java
class Student {
    int rollNumber;
    String name;
    double marks;

    // Parameterized constructor
    Student(int rNo, String n, double m) {
        rollNumber = rNo;
        name = n;
        marks = m;
    }
}
```

Usage:
```java
Student kunal = new Student(32, "Kunal Kushwaha", 84.50);
```

- Now, while calling the constructor, you must provide the required arguments (roll number, name, marks) in order.
- This is a much cleaner way of creating fully-initialized objects, compared to setting each property manually after creation.

---

## 15. Constructor Overloading

> **Constructor Overloading = Having multiple constructors in the same class, each with a different parameter list (different number/type of parameters).**

This is similar in concept to **function/method overloading**.

```java
class Student {
    int rollNumber;
    String name;
    double marks;

    // Constructor 1: No arguments
    Student() {
        rollNumber = 0;
        name = "default";
        marks = 0.0;
    }

    // Constructor 2: With arguments
    Student(int rNo, String n, double m) {
        rollNumber = rNo;
        name = n;
        marks = m;
    }
}
```

- When you call `new Student()`, the **no-argument constructor** runs.
- When you call `new Student(198, "Random", 31.0)`, the **parameterized constructor** runs.
- Java decides **which constructor to call** based on the number and type of arguments passed — this is constructor overloading.

**Use case mentioned:** Sometimes you may want to create an object using values **copied from another existing object**, e.g., copying roll number, name, and marks from an "other" student object into a new one — this can be done through a custom constructor designed for that purpose (a "copy-style" constructor).

---

## 16. Calling One Constructor from Another — `this()`

You can call **one constructor from another constructor** within the same class using the `this()` keyword/call. This is useful to avoid duplicating initialization code.

```java
class Student {
    int rollNumber;
    String name;
    double marks;

    // No-argument constructor calls the parameterized constructor
    Student() {
        this(198, "Default Name", 31.0);  // Calls Constructor 2
    }

    Student(int rNo, String n, double m) {
        rollNumber = rNo;
        name = n;
        marks = m;
    }
}
```

**Rules:**
- `this(...)` must be the **first statement** inside the constructor.
- It allows default values to be passed automatically to another constructor, so you don't repeat initialization logic.

---

## 17. The `this` Keyword

> **`this` = A reference to the CURRENT object** (the object on which the method/constructor is currently being invoked).

### Why is `this` needed?
When a parameter name is the **same** as the instance variable name, there's ambiguity. `this` is used to distinguish between the **instance variable** and the **local/parameter variable**.

```java
class Student {
    String name;

    void changeName(String name) {
        this.name = name;  
        // this.name -> refers to the INSTANCE variable of the current object
        // name (right side) -> refers to the METHOD PARAMETER
    }
}
```

- Internally, when you call `kunal.changeName("New Name")`, Java replaces `this` with the actual object reference (`kunal`), so `this.name` effectively becomes `kunal.name`.
- Similarly, methods like a `greeting()` function that prints `"Hello, my name is " + this.name` will automatically use **whichever object called it** — so calling `kunal.greeting()` prints "Kunal", while `rahul.greeting()` prints "Rahul" — because `this` always refers to the object that invoked the method.

### `this` Summary
- `this` refers to the current instance/object.
- Every object's methods internally use `this` to access **their own** properties, ensuring each object's data remains separate and correctly linked.

---

## 18. Primitive Data Types — Why We Don't Use `new`

- In Java, **primitive types** (`int`, `double`, `float`, `boolean`, `char`, etc.) are **NOT objects**.
- They were introduced early on for performance/simplicity and exist as a language fundamental — not as a class-based object.
- Because primitives are not objects, they do **not require `new`** for memory allocation; they are lightweight and stored directly (typically on the stack, for local variables).
- Note: In some other languages (e.g., Python), even primitive-like values are internally treated as objects — but that is NOT how Java's primitives work.

```java
int x = 5;      // No "new" required — primitive
Student s = new Student(); // "new" required — it's an object
```

---

## 19. `new` Keyword — Memory Allocation Behavior (Reference Semantics)

```java
Student student1 = new Student();
Student student2 = student1;
```

- `student1` and `student2` are **two different reference variables**, but they **point to the SAME object** in memory.
- This is **NOT** a copy — no new memory is allocated for `student2`. It is simply another name pointing to the exact same memory address as `student1`.
- **Any change made through one reference is visible through the other**, since both point to identical memory.

Example:
```java
student1.name = "A";
System.out.println(student2.name); // prints "A" (NOT the old value)
```

### How to actually create a genuinely separate/second object
```java
Student student1 = new Student();
Student student2 = new Student(); // separate "new" call = separate object in memory
```
Now `student1` and `student2` are independent, and changing one does **not** affect the other.

---

## 20. Wrapper Classes

> **Wrapper Class = A class that "wraps" a primitive data type into an object.**

- Since primitives are not objects, if you need to treat a primitive value **as an object** (for example, when working with Collections, which only store objects), you use its corresponding **Wrapper class**.

| Primitive | Wrapper Class |
|---|---|
| `int` | `Integer` |
| `double` | `Double` |
| `float` | `Float` |
| `boolean` | `Boolean` |
| `char` | `Character` |
| `long` | `Long` |

```java
int a = 5;
Integer b = new Integer(5);   // Wrapper — wraps primitive int inside an object
```

- Wrapper classes allow primitives to be used **wherever an object is required** (e.g., in Collections like `ArrayList`), and provide utility methods (e.g., converting a `String` to a number, converting a character to its numeric equivalent, etc.).
- Difference from a normal class: with wrapper classes, an object is created to essentially just "hold" a primitive value, enabling object-like behavior for that primitive.

---

## 21. The `final` Keyword

> **`final` = A modifier used to prevent modification.** Once a `final` variable is initialized, its value **cannot be changed**.

### Rules
1. A `final` variable **must always be initialized** — either at the time of declaration, or inside the constructor. You cannot leave it uninitialized.
2. Once initialized, you **cannot reassign** a new value to it later.

```java
final int marks = 100;
marks = 90; // ❌ ERROR — cannot modify a final variable
```

### `final` with Primitives vs Objects/References

- **Primitive + final:** The **value itself** can never change.
```java
final int x = 10;
// x = 20; // Not allowed
```

- **Reference type (Object) + final:** The **reference** can never be reassigned to point to a different object — BUT the **internal state/properties of the object it points to CAN still be changed**.

```java
final Student kunal = new Student();
kunal.name = "Changed Name";   // ✅ Allowed — modifying internal property
kunal = new Student();         // ❌ ERROR — cannot reassign a final reference
```

**Key distinction:** `final` prevents **reassignment of the reference**, not modification of the object's internal data.

---

## 22. Garbage Collection

> **Garbage Collection (GC) = Java's automatic process of removing/destroying objects that are no longer needed/referenced, to free up memory.**

### Key Points
- Unlike some other languages where you manually free memory, **Java handles memory cleanup automatically** through the Garbage Collector.
- GC happens automatically at certain points in time, when an object is no longer reachable/referenced by any part of the program.
- **You (the programmer) cannot manually force destruction of an object.** Java does not allow explicit/manual destruction of an object like `delete` in C++.
- You *can*, however, define what should happen **right before** an object is garbage collected, using the `finalize()` method.

### `finalize()` Method
- `finalize()` is a special method that gets called **automatically by the Garbage Collector**, just before an object is actually destroyed/removed from memory.
- You can override `finalize()` to specify cleanup actions (e.g., closing a file, releasing a resource) that should run right before the object disappears.

```java
class Student {
    // ... properties ...

    @Override
    protected void finalize() {
        System.out.println("Object is about to be garbage collected");
    }
}
```

### Why does GC exist?
- Every time you create an object using `new`, memory is allocated on the heap.
- If unwanted/unused objects are never cleared, memory usage keeps growing (memory leaks).
- The Garbage Collector automatically finds objects that are no longer referenced anywhere in the program and reclaims that memory for future use.

---

## 23. Quick Recap / Cheat Sheet

| Concept | One-line Summary |
|---|---|
| **Class** | Logical blueprint/template; no memory occupied; defines properties + functions |
| **Object** | Physical instance of a class; occupies memory; created via `new` |
| **Instance Variable** | Property declared inside class, outside methods/constructors; separate copy per object |
| **Dot Operator (`.`)** | Used to access an object's properties/methods |
| **`new` keyword** | Dynamically allocates memory at runtime and returns a reference to the object |
| **Default values** | int=0, double=0.0, boolean=false, Object/String=null |
| **Reference assignment** (`obj2 = obj1`) | Both variables point to the SAME object (no copy made) |
| **Constructor** | Special method (same name as class, no return type) that runs automatically on object creation |
| **Default constructor** | Auto-provided by Java if you don't write any constructor |
| **Constructor Overloading** | Multiple constructors with different parameter lists in the same class |
| **`this()`** | Calls another constructor of the same class (must be first line) |
| **`this` keyword** | Refers to the current object; resolves naming conflicts between instance variables and parameters |
| **Primitives don't need `new`** | They aren't objects; lightweight, no object-style memory allocation needed |
| **Wrapper Class** | Wraps a primitive inside an object (e.g., `int` → `Integer`) |
| **`final`** | Prevents reassignment; must be initialized once; for objects, only the reference is locked, not internal state |
| **Garbage Collection** | Java's automatic memory cleanup for unreferenced objects; manual destruction not allowed |
| **`finalize()`** | Method automatically called just before an object is garbage collected |
