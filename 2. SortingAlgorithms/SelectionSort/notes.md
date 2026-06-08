## Selection Sort Algorithm

Selection Sort is a simple, intuitive sorting algorithm that works by repeatedly selecting the smallest (or largest) element from the unsorted portion of the list and moving it to its correct sorted position.

---

## How it Works

* In every iteration, find the **maximum (or minimum)** element in the unsorted portion
* Swap that element with the element at the **last (or first) position of the unsorted portion**
* Expand the sorted portion by one element
* Repeat until the array is fully sorted

---

## Complexity Analysis

* **Number of Comparisons:**

  ```
  0 + 1 + 2 + ... + (n - 1) = n(n - 1) / 2
  ```

* **Time Complexity:**

    * Best Case: `O(n²)`
    * Worst Case: `O(n²)`
    * Reason: Always performs nested loop comparisons regardless of input order

* **Stability:**

    * Selection Sort is **not stable** by default
    * It may change the relative order of equal elements
  
* **Advantage:**
    * Works well for small lists/arrays.
    * It has a predictable performance.

---

## Algorithm Steps

1. Initialize `last = array.length - 1`
2. Loop while `last > 0`:

    * Find index of the **maximum element** in range `[0, last]`
    * Swap element at `maxIndex` with element at `last`
    * Decrement `last`

---

## Dry Run

### Input Array

```id="j3d0g8"
[4, 5, 1, 2, 3]
```

### Pass 1

* Max in `[0, 4]` → `5` at index `1`
* Swap with index `4`

```
[4, 3, 1, 2, 5]
```

---

### Pass 2

* Max in `[0, 3]` → `4` at index `0`
* Swap with index `3`

```
[2, 3, 1, 4, 5]
```

---

### Pass 3

* Max in `[0, 2]` → `3` at index `1`
* Swap with index `2`

```
[2, 1, 3, 4, 5]
```