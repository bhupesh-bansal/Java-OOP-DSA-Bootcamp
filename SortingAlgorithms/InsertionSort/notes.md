# Insertion Sort

## Overview

Insertion Sort is an intuitive sorting algorithm that builds the final sorted array one item at a time. It works by dividing the array into a **sorted** and an **unsorted** part. Elements from the unsorted part are picked and inserted into their correct position in the sorted part.

---

## Key Concepts

### Intuition

* Unlike Bubble Sort (which pushes the largest element to the end) or Selection Sort (which selects the minimum (or maximum) element), Insertion Sort:

    * Focuses on **progressively building a sorted portion on the left side** of the array.

### The Process

* For each index `i`, insert the element at position `i + 1` into its correct position in the sorted sub-array `[0 ... i]`.

### Passes

* After 1st pass → Array sorted till index 1
* After 2nd pass → Array sorted till index 2
* Continues until the entire array is sorted

---

## Algorithm Dry Run

### Example Array:

```
[5, 3, 4, 1, 2]
```

### Pass 1 (i = 0)

* Compare 3 and 5 → swap
* Array: `[3, 5, 4, 1, 2]`

### Pass 2 (i = 1)

* Insert 4 into sorted part `[3, 5]`
* Compare with 5 → swap
* Compare with 3 → stop
* Array: `[3, 4, 5, 1, 2]`

### Pass 3 (i = 2)

* Insert 1 into `[3, 4, 5]`
* Compare with 5, 4, 3 → move left
* Array: `[1, 3, 4, 5, 2]`

### Pass 4 (i = 3)

* Insert 2 into `[1, 3, 4, 5]`
* Compare with 5, 4, 3 → move left
* Compare with 1 → stop
* Array: `[1, 2, 3, 4, 5]`

---

## Crucial Logic

* The inner loop runs **backwards**
* Condition:

    * If `arr[j] < arr[j - 1]` → swap
    * Else → **break early**
* This early break improves performance significantly when the array is partially sorted

---

## Complexity Analysis

| Case       | Time Complexity |
| ---------- | --------------- |
| Worst Case | O(N²)           |
| Best Case  | O(N)            |

### Explanation

* **Worst Case:** Array is in descending order → maximum comparisons & swaps
* **Best Case:** Array is already sorted → inner loop breaks immediately

---

## Why Use Insertion Sort?

* **Adaptive:** More efficient for partially sorted arrays
* **Stable:** Maintains relative order of equal elements
* **Space Efficient:** O(1) in-place sorting
* **Hybrid Usage:** Used in advanced algorithms like **Timsort** (Java’s `Arrays.sort()`) for small subarrays

---

## Key Takeaways

* Builds sorted array incrementally
* Efficient for small or nearly sorted datasets
* Simple and intuitive to implement
* Important foundation for understanding advanced sorting algorithms

---
