# Backtracking Notes

## What is Backtracking?

Backtracking is a refined approach to recursion where you attempt to build a solution incrementally, piece by piece, and remove those choices (backtrack) if they fail to lead to a valid solution.

### Core Idea
1. Make a choice.
2. Explore that choice recursively.
3. Undo the choice (backtrack).
4. Try the next possible choice.

The key idea is to **revert the state** after returning from a recursive call so that other branches of the recursion tree are not affected.

---

## Key Mechanics of Backtracking

### State Management
Whenever you make a change to the current state (such as marking a cell as visited), you must restore the original state before returning from the function.

#### Example
```java
maze[r][c] = false; // Mark as visited

backtrack(...);

maze[r][c] = true;  // Undo the change (Backtrack)