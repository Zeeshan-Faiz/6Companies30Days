package GoldmanSachs;

import java.util.HashSet;

/*
There is an m x n binary grid matrix with all the values set 0 initially. Design an algorithm to 
randomly pick an index (i, j) where matrix[i][j] == 0 and flips it to 1. All the indices (i, j) 
where matrix[i][j] == 0 should be equally likely to be returned.
Optimize your algorithm to minimize the number of calls made to the built-in random function of 
your language and optimize the time and space complexity.
Implement the Solution class:

    Solution(int m, int n) Initializes the object with the size of the binary matrix m and n.
    int[] flip() Returns a random index [i, j] of the matrix where matrix[i][j] == 0 and flips it to 1.
    void reset() Resets all the values of the matrix to be 0.

Example 1:
Input
["Solution", "flip", "flip", "flip", "reset", "flip"]
[[3, 1], [], [], [], [], []]
Output
[null, [1, 0], [2, 0], [0, 0], null, [2, 0]]

Explanation
Solution solution = new Solution(3, 1);
solution.flip();  // return [1, 0], [0,0], [1,0], and [2,0] should be equally likely to be returned.
solution.flip();  // return [2, 0], Since [1,0] was returned, [2,0] and [0,0]
solution.flip();  // return [0, 0], Based on the previously returned indices, only [0,0] can be returned.
solution.reset(); // All the values are reset to 0 and can be returned.
solution.flip();  // return [2, 0], [0,0], [1,0], and [2,0] should be equally likely to be returned.
*/

public class Q519RandomFlipMatrix {
    
    private int m;
    private int n;
    private HashSet<String> mat;

    public Q519RandomFlipMatrix(int m, int n) {
        mat = new HashSet<>();
        this.m = m;
        this.n = n;
    }

    public int[] flip() {
        int r = (int) (Math.random() * m);
        int c = (int) (Math.random() * n);

        while (mat.contains(r + " " + c)) {
            r = (int) (Math.random() * m);
            c = (int) (Math.random() * n);
        }

        int output[] = { r, c };
        mat.add(r + " " + c);
        return output;
    }

    public void reset() {
        mat.clear();
    }
}