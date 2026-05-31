# Cyclic Sort

## Overview

Cyclic Sort is a highly efficient sorting pattern. It is designed for arrays containing numbers in a specific range.

---

## The Cyclic Sort Pattern

### When to Use

* Use Cyclic Sort when:

    * The array contains numbers in the range **1 to N** or **0 to N**
    * You need to place elements at their correct indices efficiently

---

### Complexity

* **Time Complexity:** O(n)
* **Space Complexity:** O(1) (in-place sorting)

---

### Core Idea

* Instead of repeatedly swapping adjacent elements (like Bubble Sort), Cyclic Sort:

    * Places each element directly at its **correct index**
* Rule:

    * If the element at index `i` is not at its correct position:

        * Swap it with the element at index `arr[i] - 1`
    * Repeat until the current element is correctly placed
    * Then move to the next index

---

## Algorithm Dry Run

### Example Array:

```
[3, 5, 2, 1, 4]
```

### Step-by-Step Execution

* **Index 0 (Value 3)**

    * Correct index = 2
    * Swap → `[2, 5, 3, 1, 4]`

* **Index 0 (Value 2)**

    * Correct index = 1
    * Swap → `[5, 2, 3, 1, 4]`

* **Index 0 (Value 5)**

    * Correct index = 4
    * Swap → `[4, 2, 3, 1, 5]`

* **Index 0 (Value 4)**

    * Correct index = 3
    * Swap → `[1, 2, 3, 4, 5]`

* **Index 0 (Value 1)**

    * Already correct
    * Move forward → Sorting completes

---

## Key Insight

* Do **not increment index `i`** until the current element is in its correct position
* This ensures each number is placed correctly in **one pass**

---

## Key Takeaways

* Works best for **range-based arrays**
* Eliminates unnecessary comparisons
* Guarantees **linear time complexity**
* Frequently used in **coding interviews**

---
