# 📘 Time & Space Complexity — Study Notes

> Comprehensive notes on Asymptotic Analysis, Notations & Recurrence Relations
---

## What is Time Complexity?

❌ **Wrong Idea:**  
Time Complexity = Actual Time Taken

✅ **Correct Definition:**  
Time Complexity is a function that describes how running time grows with input size.

💡 It measures **growth rate**, not exact time.

---

## Comparing Complexities

| Algorithm       | Complexity |
|----------------|-----------|
| Linear Search  | O(n)      |
| Binary Search  | O(log n)  |

👉 For large inputs, **O(log n) is much faster than O(n)**

### Constant Time
- O(1) → Same time regardless of input

---

## Procedure for Analysis

### 4 Rules:

1. Use **Worst Case**
2. Consider **Large n**
3. Drop **Lower Order Terms**
4. Drop **Constants**

### Example
```
f(n) = 3n³ + 4n² + 5n + 6
→ O(n³)
```

---

## Big-O Notation

**Definition:**  
f(n) = O(g(n)) → Upper Bound

```
lim (n → ∞) f(n)/g(n) < ∞
```

### Order of Growth

```
O(1) < O(log n) < O(n) < O(n log n) < O(n²) < O(2ⁿ) < O(n!)
```

---

## Big-Omega Notation

**Definition:**  
f(n) = Ω(g(n)) → Lower Bound

```
lim (n → ∞) f(n)/g(n) > 0
```

---

## Big-Theta Notation

**Definition:**  
f(n) = Θ(g(n)) → Tight Bound

```
0 < lim (n → ∞) f(n)/g(n) < ∞
```

---

## Little-o & Little-ω

| Notation | Meaning |
|----------|--------|
| o(g)     | Strict Upper |
| ω(g)     | Strict Lower |

```
lim f/g = 0 → o(g)
lim f/g = ∞ → ω(g)
```

---

## Nested Loops Analysis

### Example

```
for i = 1 to n
  for j = i to n
```

Total:

```
n(n+1)/2 → O(n²)
```

---

## Recursive Programs

### Time:
```
Time = (work per call) × (number of calls)
```

### Space:
```
Space = height of recursion tree
```

---

### Common Complexities

| Algorithm | Time | Space |
|----------|------|------|
| Linear Search | O(n) | O(1) |
| Binary Search | O(log n) | O(1) |
| Merge Sort | O(n log n) | O(n) |
| Quick Sort | O(n log n) | O(log n) |
| Fibonacci (naive) | O(2ⁿ) | O(n) |

---

### Asymptotic Notations

| Notation | Meaning |
|----------|--------|
| **O(g(n))** | Upper bound (max time) |
| **Ω(g(n))** | Lower bound (min time) |
| **Θ(g(n))** | Exact bound |
| **o(g(n))** | Strict upper bound |
| **ω(g(n))** | Strict lower bound |

---

### Space Complexity

```
Total Space = Input Space + Auxiliary Space
```

👉 For recursion:

```
Space = Height of recursion tree
```

Reason: Only one path exists in the call stack at a time.

---

## Recurrence Relations

General form:

```
T(n) = aT(n/b) + f(n)
```

Types:
- Divide & Conquer
- Linear Recurrence
---

## ⚔️ Divide and Conquer Recurrences

Divide & Conquer algorithms break problems into smaller parts.

Examples:
- Binary Search
- Merge Sort

---

### Standard Form

```
T(x) = a₁T(b₁x) + a₂T(b₂x) + ... + aₖT(bₖx) + g(x)
```

Where:
- `a` → number of subproblems
- `b` → fraction of input size
- `g(x)` → extra work

---

### Akra-Bazzi Formula

```
T(x) = Θ( xᵖ ( 1 + ∫₁ˣ ( g(u) / u^(p+1) ) du ) )
```

---

### Step 1: Find p

Solve:

```
a₁b₁ᵖ + a₂b₂ᵖ + ... + aₖbₖᵖ = 1
```

---

### Example: Binary Search

```
T(n) = T(n/2) + c
```

Step 1:
```
(1/2)ᵖ = 1 → p = 0
```

Step 2:

```
T(n) = Θ( n⁰ (1 + ∫₁ⁿ c/u du ) )
```

Step 3:

```
∫ c/u du = c ln n
```

Final:

```
T(n) = Θ(log n)
```

---

### Shortcut Method (No Integration)

Compare `p` with power of `g(x)`:

1. If `p > power of g(x)` →
   ```
   T(n) = O(nᵖ)
   ```

2. If `p < power of g(x)` →
   ```
   T(n) = O(g(n))
   ```

---

## 🔁 Linear Recurrence Relations

These reduce problem size by subtraction (`n-1`, `n-2`).

---

### A. Homogeneous Recurrence

#### Form

```
f(n) = a₁f(n-1) + a₂f(n-2) + ... + aₖf(n-k)
```

---

### 5-Step Solution

#### 1. Substitute

```
f(n) → αⁿ
```

---

#### 2. Characteristic Equation

Example (Fibonacci):

```
α² = α + 1
→ α² - α - 1 = 0
```

---

#### 3. Find Roots

```
α = (1 ± √5) / 2
```

---

#### 4. General Solution

```
f(n) = C₁r₁ⁿ + C₂r₂ⁿ
```

Special case (repeated root):

```
f(n) = C₁rⁿ + C₂·n·rⁿ
```

---

#### 5. Apply Base Cases

Solve constants using initial values.

---

### Fibonacci Complexity

```
T(n) = Θ(1.618ⁿ) ≈ O(2ⁿ)
```

👉 Exponential → very inefficient

---

### B. Non-Homogeneous Recurrence

#### Form

```
f(n) = a₁f(n-1) + g(n)
```

---

### 5-Step Solution

#### 1. Solve Homogeneous Part

Ignore `g(n)` and solve normally.

---

#### 2. Guess Particular Solution

| g(n) Type | Guess |
|----------|------|
| Constant | A |
| Linear | An + B |
| Quadratic | An² + Bn + C |
| Exponential | A·aⁿ |

---

#### Overlap Rule

If guess overlaps with homogeneous solution:

```
Multiply by n
```

---

#### 3. Solve Constants

Substitute into original equation.

---

#### 4. Combine

```
Total = Homogeneous + Particular
```

---

#### 5. Apply Base Cases

Find remaining constants.

---
## 🧠 Golden Rules

```
1. Use WORST CASE
2. Consider LARGE INPUT
3. Drop LOWER TERMS
4. Drop CONSTANTS
```