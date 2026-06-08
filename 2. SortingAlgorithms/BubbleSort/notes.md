dry-run:
## Bubble Sort – Step-by-Step Execution

### Initial Array

```
[5, 3, 4, 1, 2]
```

---

## Pass 1 (i = 0)

The inner loop runs from `j = 1` to `arr.length - 1`.

* **j = 1**: Compare `3` and `5` → `3 < 5` → Swap

  ```
  [3, 5, 4, 1, 2]
  ```
* **j = 2**: Compare `4` and `5` → `4 < 5` → Swap

  ```
  [3, 4, 5, 1, 2]
  ```
* **j = 3**: Compare `1` and `5` → `1 < 5` → Swap

  ```
  [3, 4, 1, 5, 2]
  ```
* **j = 4**: Compare `2` and `5` → `2 < 5` → Swap

  ```
  [3, 4, 1, 2, 5]
  ```

**End of Pass 1:** Largest element (`5`) is placed correctly.

---

## Pass 2 (i = 1)

Inner loop runs up to index `3`.

* **j = 1**: Compare `4` and `3` → No swap
* **j = 2**: Compare `1` and `4` → Swap

  ```
  [3, 1, 4, 2, 5]
  ```
* **j = 3**: Compare `2` and `4` → Swap

  ```
  [3, 1, 2, 4, 5]
  ```

**End of Pass 2:** Second largest element (`4`) is placed correctly.

---

## Pass 3 (i = 2)

Inner loop runs up to index `2`.

* **j = 1**: Compare `1` and `3` → Swap

  ```
  [1, 3, 2, 4, 5]
  ```
* **j = 2**: Compare `2` and `3` → Swap

  ```
  [1, 2, 3, 4, 5]
  ```

**End of Pass 3:** Element `3` is placed correctly.

---

## Pass 4 (i = 3)

* **j = 1**: Compare `2` and `1` → No swap

No swaps occurred in this pass.

**Optimization Triggered:**
Since no swaps happened, the algorithm stops early.

---

## Final Sorted Array

```
[1, 2, 3, 4, 5]
```

---

## Key Takeaways

* **Space Complexity:** `O(1)`

    * In-place sorting (no extra memory used)

* **Worst Case Time Complexity:** `O(N²)`

    * When array is sorted in reverse order

* **Best Case Time Complexity:** `O(N)`

    * When array is already sorted (early exit using `swapped` flag)

* **Stability:**

    * Bubble Sort is **stable**
    * Equal elements maintain their relative order because swapping only occurs when:

      ```js
      if (arr[j] < arr[j - 1])
      ```

---

## Stable vs Unstable Sorting Algorithms

### Stable Sorting

A sorting algorithm is **stable** if it preserves the relative order of elements with equal values.

**Example:**

```
(2, A), (1, X), (2, B)
```

After sorting:

```
(1, X), (2, A), (2, B)
```

👉 `A` remains before `B` → order preserved

---

### Unstable Sorting

A sorting algorithm is **unstable** if it may change the relative order of equal elements.

**Example:**

```
(2, A), (1, X), (2, B)
```

After sorting:

```
(1, X), (2, B), (2, A)
```

👉 Order of `A` and `B` changed

---

### Why Stability Matters

* Important when sorting **multiple keys** (e.g., sort by name, then by age)
* Used in **database queries** and **real-world datasets**
* Helps maintain logical consistency in sorted data

---

### Examples

* **Stable Algorithms:** Bubble Sort, Merge Sort, Insertion Sort
* **Unstable Algorithms:** Quick Sort, Selection Sort, Heap Sort

