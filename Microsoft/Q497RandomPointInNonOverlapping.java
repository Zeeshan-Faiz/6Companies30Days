package Microsoft;

import java.util.Arrays;
import java.util.Random;

/*
You are given an array of non-overlapping axis-aligned rectangles rects where rects[i] = 
[ai, bi, xi, yi] indicates that (ai, bi) is the bottom-left corner point of the ith rectangle 
and (xi, yi) is the top-right corner point of the ith rectangle. Design an algorithm to pick a 
random integer point inside the space covered by one of the given rectangles. A point on the 
perimeter of a rectangle is included in the space covered by the rectangle.
Any integer point inside the space covered by one of the given rectangles should be equally likely 
to be returned.
Note that an integer point is a point that has integer coordinates.

Implement the Solution class:
    Solution(int[][] rects) Initializes the object with the given rectangles rects.
    int[] pick() Returns a random integer point [u, v] inside the space covered by one of the given rectangles.

Example 1:
Input
["Solution", "pick", "pick", "pick", "pick", "pick"]
[[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
Output
[null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]

Explanation
Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
solution.pick(); // return [1, -2]
solution.pick(); // return [1, -1]
solution.pick(); // return [-1, -2]
solution.pick(); // return [-2, -2]
solution.pick(); // return [0, 0]
*/

public class Q497RandomPointInNonOverlapping {
    
    public Q497RandomPointInNonOverlapping(int[][] rects) {
        this.rects = rects;
        areas = new int[rects.length];
        for (int i = 0; i < rects.length; ++i)
            areas[i] = getArea(rects[i]) + (i > 0 ? areas[i - 1] : 0);
    }

    public int[] pick() {
        final int target = rand.nextInt(areas[areas.length - 1]);
        final int index = firstGreater(areas, target);
        final int[] r = rects[index];
        return new int[] {
                rand.nextInt(r[2] - r[0] + 1) + r[0],
                rand.nextInt(r[3] - r[1] + 1) + r[1],
        };
    }

    private int[][] rects;
    private int[] areas;
    private Random rand = new Random();

    private int getArea(int[] r) {
        return (r[2] - r[0] + 1) * (r[3] - r[1] + 1);
    }

    private int firstGreater(int[] A, int target) {
        final int i = Arrays.binarySearch(A, target + 1);
        return i < 0 ? -i - 1 : i;
    }
}