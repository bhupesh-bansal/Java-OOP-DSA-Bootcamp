# Quick Sort

Quick Sort is a highly efficient **divide-and-conquer** sorting algorithm. Unlike Merge Sort, which requires additional space for merging, Quick Sort is an **in-place** sorting algorithm.

---

# 1. Core Concept: The Pivot

## What is a Pivot?
A **pivot** is a reference element chosen from the array. It can be:
- The first element
- The last element
- The middle element
- A randomly selected element

## Goal of Quick Sort
The objective of each partitioning step is to place the pivot in its **correct sorted position** such that:

- Every element **smaller than the pivot** lies on its left.
- Every element **greater than the pivot** lies on its right.

Once this condition is achieved:
- The pivot never moves again.
- The same process is recursively applied to the left and right subarrays.

---

# 2. Partitioning Logic

Quick Sort uses a **two-pointer approach** to partition the array without requiring any extra space.

## Steps

1. Initialize:
    - `start` → beginning of the current partition.
    - `end` → end of the current partition.

2. Choose a pivot.
    - Common choice: the middle element.

3. Move the `start` pointer forward while:

   ```java
   nums[start] < pivot
   ```

4. Move the `end` pointer backward while:

   ```java
   nums[end] > pivot
   ```

5. If:

   ```java
   start <= end
   ```

   then:
    - Swap the two elements.
    - Increment `start`.
    - Decrement `end`.

6. Continue until:

   ```java
   start > end
   ```

At this point:
- Every element on the left side is less than or equal to the pivot.
- Every element on the right side is greater than or equal to the pivot.

The algorithm then recursively sorts both partitions.

---

# 3. Algorithm

```
QuickSort(arr, low, high)

If low >= high
    return

Choose pivot

Partition the array

QuickSort(left partition)

QuickSort(right partition)
```

---

# 4. Complexity Analysis

## Time Complexity

### Best Case

Occurs when every pivot divides the array into two nearly equal halves.

```
O(n log n)
```

### Worst Case

Occurs when the pivot is always the smallest or largest element, producing highly unbalanced partitions.

```
O(n²)
```

---

## Space Complexity

Quick Sort is an **in-place** sorting algorithm.

Only the recursion stack consumes extra memory.

```
O(log n)
```

*(Ignoring the space occupied by the original array.)*

---

# 5. Important Observations

### In-Place Algorithm

Quick Sort rearranges elements within the original array.

No additional array is required.

---

### Not Stable

Quick Sort is **not stable** because equal elements may change their relative order after swapping.

---

### Faster in Practice

Quick Sort is often preferred over Merge Sort for arrays because:

- Better cache performance.
- No extra `O(n)` auxiliary array.
- Lower constant factors.

---

### Used in Hybrid Algorithms

Modern programming languages rarely use plain Quick Sort.

Examples include:
- Dual-Pivot Quick Sort
- IntroSort
- Combination with Insertion Sort for small partitions
- Combination with Heap Sort to avoid worst-case performance

---

# 6. Dry Run

Given:

```
[5, 4, 3, 2, 1]
```

Choose pivot:

```
Pivot = 4
```

### Initial State

```
start → 5
end   → 1
```

Array:

```
[5, 4, 3, 2, 1]
```

---

### Step 1

`5` is greater than the pivot.

`1` is smaller than the pivot.

Swap them.

```
[1, 4, 3, 2, 5]
```

Move pointers:

```
start++
end--
```

Now:

```
start → 4
end   → 2
```

---

### Step 2

- `start` stops because `4` is the pivot.
- `end` stops at `2`.

Since:

```
start <= end
```

perform the necessary swap (if required), then move the pointers again.

Continue until:

```
start > end
```

Eventually, the pivot reaches its correct sorted position.

---

### Recursive Calls

After partitioning:

Left partition:

```
[1, 3, 2]
```

Right partition:

```
[5]
```

Quick Sort recursively sorts:

```
QuickSort([1,3,2])

QuickSort([5])
```

After all recursive calls finish:

```
[1,2,3,4,5]
```

---

# Summary

| Property | Value |
|----------|-------|
| Technique | Divide and Conquer |
| In-place | ✅ Yes |
| Stable | ❌ No |
| Best Time | **O(n log n)** |
| Average Time | **O(n log n)** |
| Worst Time | **O(n²)** |
| Space Complexity | **O(log n)** |
| Extra Array Needed | ❌ No |
| Recursion | ✅ Yes |
```