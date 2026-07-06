# Merge Sort

Merge Sort is a **Divide and Conquer** sorting algorithm. It recursively divides the array into smaller subarrays, sorts them, and then merges the sorted subarrays to produce the final sorted array.

---

# 1. Introduction to Merge Sort

Merge Sort follows the **Divide and Conquer** paradigm.

## Divide

Split the array into two halves repeatedly until each subarray contains only one element.

## Conquer (Sort)

Recursively sort the left and right halves.

## Combine (Merge)

Merge the two sorted halves into a single sorted array.

---

# 2. Algorithm

## Step 1: Base Case

If the array contains only one element, it is already sorted.

```java
if (arr.length == 1)
    return arr;
```

---

## Step 2: Split the Array

Find the middle index and divide the array into two parts.

- Left Half
- Right Half

---

## Step 3: Recursive Calls

Recursively apply Merge Sort on both halves.

```text
left = mergeSort(leftHalf)

right = mergeSort(rightHalf)
```

---

## Step 4: Merge

Create a temporary array.

Compare the current elements of both sorted subarrays.

Whichever element is smaller is inserted into the temporary array.

Continue until one subarray becomes empty.

Finally, copy all remaining elements from the other subarray.

---

# 3. Merge Algorithm

```
Merge(left, right)

Create an empty array result

i = 0
j = 0

While i < left.length AND j < right.length

    If left[i] < right[j]
        result.add(left[i])
        i++
    Else
        result.add(right[j])
        j++

Copy all remaining elements from left

Copy all remaining elements from right

Return result
```

---

# 4. Dry Run

Given:

```
[8, 3, 4, 9, 5, 2, 7, 1]
```

---

## Step 1: Divide

```
                 [8 3 4 9 5 2 7 1]
                /                 \
          [8 3 4 9]           [5 2 7 1]
          /      \            /      \
      [8 3]    [4 9]      [5 2]    [7 1]
      /  \      /  \      /  \      /  \
    [8][3]   [4][9]    [5][2]   [7][1]
```

Each subarray now contains only one element.

The base case is reached.

---

## Step 2: Merge

Merge adjacent sorted arrays.

Example:

```
[8] + [3]

↓

[3, 8]
```

Another example:

```
[4] + [9]

↓

[4, 9]
```

Now merge:

```
[3, 8] + [4, 9]
```

Comparison:

```
3 vs 4 → 3

8 vs 4 → 4

8 vs 9 → 8

Remaining → 9
```

Result:

```
[3, 4, 8, 9]
```

The same process is repeated for the right half.

Finally:

```
[3,4,8,9] + [1,2,5,7]
```

Result:

```
[1,2,3,4,5,7,8,9]
```

---

# 5. Handling Leftover Elements

Sometimes one subarray becomes empty before the other.

Example:

```
Left  = [3, 8]

Right = [4, 9]
```

After comparisons:

```
Result = [3,4,8]
```

The right array still contains:

```
[9]
```

Append the remaining elements directly.

Final result:

```
[3,4,8,9]
```

---

# 6. Complexity Analysis

## Time Complexity

At every level of the recursion tree:

```
O(n)
```

operations are performed during merging.

The recursion tree has:

```
log₂(n)
```

levels.

Therefore,

```
Time Complexity = O(n log n)
```

This remains the same for:

- Best Case
- Average Case
- Worst Case

---

## Space Complexity

Merge Sort creates temporary arrays while merging.

Therefore,

```
Space Complexity = O(n)
```

---

# 7. Recurrence Relation

Merge Sort follows the recurrence:

```
T(n) = 2T(n/2) + O(n)
```

Where:

- `2T(n/2)` represents sorting the two halves.
- `O(n)` represents merging the two sorted halves.

---

# 8. In-Place Merge Sort

Instead of creating new arrays during every merge operation, an in-place implementation modifies the original array directly.

It typically uses three indices:

- `start`
- `mid`
- `end`

These indices help track the current subarrays while merging.

### Advantages

- Reduces object creation.
- Avoids allocating new arrays repeatedly.

### Disadvantages

- Much more difficult to implement.
- Requires careful index manipulation and shifting of elements.

---

# 9. Important Observations

### Divide and Conquer

Merge Sort repeatedly divides the problem into smaller subproblems until each contains a single element.

---

### Stable Sorting Algorithm

Merge Sort is **stable**.

If two elements have the same value, their original relative order is preserved after sorting.

---

### Predictable Performance

Unlike Quick Sort, Merge Sort always performs in:

```
O(n log n)
```

regardless of the input.

---

### Extra Memory Required

Merge Sort requires additional memory proportional to the input size because temporary arrays are used during merging.

---

# Summary

| Property | Value |
|----------|-------|
| Technique | Divide and Conquer |
| Stable | ✅ Yes |
| In-place | ❌ Standard Merge Sort is not in-place |
| Best Time | **O(n log n)** |
| Average Time | **O(n log n)** |
| Worst Time | **O(n log n)** |
| Space Complexity | **O(n)** |
| Extra Array Needed | ✅ Yes |
| Recursion | ✅ Yes |
| Recurrence Relation | **T(n) = 2T(n/2) + O(n)** |