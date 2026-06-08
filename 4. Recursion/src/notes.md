# 📘 Recursion

---

## 🔑 What is Recursion?

> **A function that calls itself.**

Every recursive function has exactly **two parts:**

| Part | What it does |
|------|-------------|
| **Base Condition** | Stops the recursion — no new calls made |
| **Recursive Call** | Function calls itself with a smaller/modified input |

```java
static void print(int n) {
    if (n == 5) { System.out.println(n); return; } // Base Condition
    System.out.println(n);
    print(n + 1);                                   // Recursive Call
}
```

---

## 📚 How Function Calls Work (Call Stack)

**Two golden rules — apply to ALL functions, not just recursive ones:**

1. **While a function is NOT finished executing → it stays in the stack**
2. **When a function finishes → it's removed from stack → flow returns to where it was called**

> Each recursive call, even to the same function, gets its **own separate memory block** in the stack.

---

## ⚠️ Base Condition & Stack Overflow

- **No base condition** → infinite calls → stack fills endlessly → 💥 **Stack Overflow Error**
- Stack Overflow = memory of computer is exceeded by recursive calls

---

## 🌳 Recursion Tree

A visual tree of all function calls — each node is one call, each edge is one invocation.

```
         main
           |
        print(1)
           |
        print(2)  ← each node is a separate stack frame
           |
        print(3)
           |
        print(4)
           |
        print(5) ← BASE CONDITION → returns here, then unwinds
```

---

## 🔁 Recurrence Relation

> When a recursive solution is written as a **mathematical formula**, it's called a **Recurrence Relation**.

| Problem | Recurrence Relation |
|---------|-------------------|
| Fibonacci | `F(n) = F(n-1) + F(n-2)` |
| Binary Search | `T(n) = T(n/2) + O(1)` |

---

## 💡 Why Use Recursion?

1. Solves **big/complex problems simply** (Tower of Hanoi, DP, Trees)
2. Any recursion → can be **converted to iteration** (and vice versa)
    - Solve with recursion first → convert to iteration for optimization
3. **Space Complexity is NOT O(1)** — each call takes memory → **O(n)** for n recursive calls

---

## 📋 Best Approach for Any Recursion Problem

```
Step 1 → Identify: Can the problem be broken into smaller sub-problems?
Step 2 → Write the Recurrence Relation (if needed)
Step 3 → Draw the Recursion Tree
Step 4 → Understand the Tree (see section below ↓)
Step 5 → Trace values returned at each step
```

---

## 🧠 Understanding Recursion Tree

> **Point 4 — About the Tree:** These sub-points must be followed when studying a recursion tree.

**4a. See the flow of functions — how they are getting in the stack**
- Watch which call gets pushed and in what order
- Every function stays in the stack until it finishes

**4b. Identify and focus on left and right tree calls**

**4c. Draw the tree and pointers again and again — using pen and paper**

**4d. See how the values are returned at each step**

**4e. See what values are returned and what type of values**

---

## 🔄 Tail Recursion

> **Tail Recursion** = The recursive call is the **last statement** in the function.

```java
// ✅ Tail Recursion — print(n+1) is the LAST thing that happens
static void print(int n) {
    System.out.println(n);
    print(n + 1);
}

// ❌ NOT Tail Recursion — addition happens AFTER both calls return
return fibo(n-1) + fibo(n-2);
```

---

## 🔢 Dry Run for Fibonacci Numbers Implementation using Recursion 

**Recurrence:** `F(n) = F(n-1) + F(n-2)` | **Base:** `if (n < 2) return n`

```java
static int fibo(int n) {
    if (n < 2) return n;
    return fibo(n - 1) + fibo(n - 2);
}
```

### Dry Run — `fibo(4)` → Expected: **3**

```
fibo(4)
├── fibo(3)                    [LEFT executes FIRST]
│   ├── fibo(2)
│   │   ├── fibo(1) → returns 1
│   │   └── fibo(0) → returns 0
│   │   └── fibo(2) = 1+0 = 1 ✅
│   └── fibo(1) → returns 1
│   └── fibo(3) = 1+1 = 2 ✅
└── fibo(2)                    [RIGHT executes AFTER left tree done]
    ├── fibo(1) → returns 1
    └── fibo(0) → returns 0
    └── fibo(2) = 1+0 = 1 ✅

fibo(4) = 2 + 1 = 3 ✅
```

### ⚠️ Fibonacci Inefficiency
- `fibo(2)` is computed **multiple times** in different branches → repeated work
- `fibo(50)` → program hangs (exponential calls)
- **Fix:** Dynamic Programming (Memoization) — store results, don't recompute
- Time complexity = **O(φⁿ)** where φ ≈ 1.618 (Golden Ratio) — NOT O(2ⁿ) as commonly said

---

## 📊 Types of Recurrence Relations

| Type | How size reduces | Example | Efficiency |
|------|-----------------|---------|-----------|
| **Linear** | Subtraction (n-1, n-2) | Fibonacci | Slow — exponential |
| **Divide & Conquer** | Division (n/2, n/3...) | Binary Search, Merge Sort | Fast — logarithmic |

---

## 📦 Working with Variables in Recursion

Three places variables can live:

| Location | When to use |
|----------|------------|
| **Argument** | Variable must be **passed to the next recursive call** (e.g., `start`, `end` in Binary Search) |
| **Body** | Variable is **only useful for the current call** (e.g., `mid` in Binary Search) |
| **Return type** | Whatever value you want to **send back up** the call chain |

> **Tip:** If a variable determines the "size" of the sub-problem → it goes in the argument.

---

## ⚡ Critical Tips

1. **Always `return` recursive calls** when there's a return type — `return search(...)` not just `search(...)`
2. **Identify recursion** by checking if problem breaks into same-type smaller problems