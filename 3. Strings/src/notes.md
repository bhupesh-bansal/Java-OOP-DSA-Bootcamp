# Strings and StringBuilder in Java

## 1. Fundamentals of Strings

* A **String** is a sequence of characters and an object of the `String` class in Java.
* Unlike primitive data types, strings are stored as objects.
* Memory structure:

    * Reference variable → stored in **stack memory**
    * Actual String object → stored in **heap memory** (String Pool)

### Key Concepts

* String is a class, and Java may create objects:

    * **Implicitly** (using string literals)
    * **Explicitly** (using `new` keyword)

---

## 2. Internal Storage and Memory Optimization

### String Pool

* The JVM maintains a special area in the heap called the **String Constant Pool**.
* It stores **unique string literals** to optimize memory.
* If two strings have the same value, they point to the **same object**.

### Immutability

* Strings are **immutable** (cannot be changed after creation).
* Any modification creates a **new object**.
* The reference variable is updated to point to the new object.
* Important for:

    * Security (e.g., passwords)
    * Memory efficiency

---

## 3. String Comparison

### `==` Operator

* Compares **memory references** (addresses).
* Returns `true` only if both variables point to the same object.

### `new` Keyword

* `new String("value")` creates a **new object in heap**.
* Does not reuse the String Pool.

### `.equals()` Method

* Compares **actual content (values)** of strings.
* Preferred method for string comparison.

---

## 4. Printing and Output

### `System.out.println()`

* Uses `PrintStream` class internally.
* Calls `String.valueOf()` to convert objects into printable format.

### `toString()`

* Every object has a `toString()` method.
* Default implementation prints:

    * Class name + hashcode
* Can be overridden for custom output.

### Pretty Printing (`printf`)

* Used for formatted output.
* Common placeholders:

    * `%d` → integer
    * `%f` → float
    * `%s` → string

---

## 5. String Concatenation and Type Conversion

### `+` Operator

* Used to concatenate strings.
* If one operand is a string → concatenation occurs.

### Type Conversion Rule

* When a primitive (like `int`) is concatenated with a string:

  ```java
  System.out.println("a" + 1); // Output: a1
  ```
* The primitive is automatically converted into its **string representation**
  (via methods like `String.valueOf()` internally).

---

## 6. String Concatenation and Performance

### Problem with `+` in loops

* Creates **multiple intermediate objects**.
* Leads to:

    * High memory usage
    * Poor performance

### Time Complexity

* Repeated concatenation → **O(n²)**
* Due to repeated copying of characters

---

## 7. StringBuilder Class

### Purpose

* Provides a **mutable alternative** to String.

### Benefits

* Modifies content **without creating new objects**
* Much better performance in loops or heavy operations

---

## 8. Common String Methods

* `toCharArray()` → Converts string to char array
* `length()` → Returns length of string
* `toLowerCase()` / `toUpperCase()` → Returns new string
* `indexOf()` / `lastIndexOf()` → Finds position
* `trim()` / `strip()` → Removes whitespace
* `split()` → Splits string into array

---

## Key Takeaways

* Strings are **immutable and memory-optimized** using the String Pool.
* JVM manages memory and the String Pool.
* Use `.equals()` instead of `==` for value comparison.
* Avoid `+` in loops → use `StringBuilder`.
* Concatenation automatically converts primitives to strings.
