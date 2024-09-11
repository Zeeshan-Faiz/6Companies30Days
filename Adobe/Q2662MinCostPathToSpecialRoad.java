package Adobe;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/*
You are given an array start where start = [startX, startY] represents your initial position 
(startX, startY) in a 2D space. You are also given the array target where target = [targetX, 
targetY] represents your target position (targetX, targetY).
The cost of going from a position (x1, y1) to any other position in the space (x2, y2) is 
|x2 - x1| + |y2 - y1|. There are also some special roads. You are given a 2D array specialRoads 
where specialRoads[i] = [x1i, y1i, x2i, y2i, costi] indicates that the ith special road goes in 
one direction from (x1i, y1i) to (x2i, y2i) with a cost equal to costi. You can use each special 
road any number of times.
Return the minimum cost required to go from (startX, startY) to (targetX, targetY).

Example 1:
Input: start = [1,1], target = [4,5], specialRoads = [[1,2,3,3,2],[3,4,4,5,1]]
Output: 5
Explanation:
    (1,1) to (1,2) with a cost of |1 - 1| + |2 - 1| = 1.
    (1,2) to (3,3). Use specialRoads[0] with the cost 2.
    (3,3) to (3,4) with a cost of |3 - 3| + |4 - 3| = 1.
    (3,4) to (4,5). Use specialRoads[1] with the cost 1.
So the total cost is 1 + 2 + 1 + 1 = 5.

Example 2:
Input: start = [3,2], target = [5,7], specialRoads = [[5,7,3,2,1],[3,2,3,4,4],[3,3,5,5,5],[3,4,5,6,6]]
Output: 7
Explanation:
It is optimal not to use any special edges and go directly from the starting to the ending position with a cost |5 - 3| + |7 - 2| = 7.
Note that the specialRoads[0] is directed from (5,7) to (3,2).

Example 3:
Input: start = [1,1], target = [10,4], specialRoads = [[4,2,1,1,3],[1,2,7,4,4],[10,3,6,1,2],[6,1,1,2,3]]
Output: 8
Explanation:
    (1,1) to (1,2) with a cost of |1 - 1| + |2 - 1| = 1.
    (1,2) to (7,4). Use specialRoads[1] with the cost 4.
    (7,4) to (10,4) with a cost of |10 - 7| + |4 - 4| = 3.
*/

public class Q2662MinCostPathToSpecialRoad {
    
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        int res = dist(start[0], start[1], target[0], target[1]);
        int n = specialRoads.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { n, 0 }); 
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int i = cur[0];
            int costI = cur[1];
            if (costI > dp[i]) {
                continue; // skip costlier path
            }
            // i is index in specialRoads, i == n means start point
            int x2i = (i == n ? start[0] : specialRoads[i][2]);
            int y2i = (i == n ? start[1] : specialRoads[i][3]);
            // check all roads
            for (int j = 0; j < n; j++) {
                int x1j = specialRoads[j][0];
                int y1j = specialRoads[j][1];
                int x2j = specialRoads[j][2];
                int y2j = specialRoads[j][3];
                int costJ = specialRoads[j][4];
                // current cost from start to path i to j's end point
                dp[j] = Math.min(dp[j], costI + dist(x2i, y2i, x2j, y2j));
                int take = costI + costJ + dist(x2i, y2i, x1j, y1j);// take current special road
                if (take < dp[j]) {
                    dp[j] = take;
                    q.offer(new int[] { j, take });
                    res = Math.min(res, take + dist(x2j, y2j, target[0], target[1]));// update
                }
            }
        }
        return res;
    }

    private int dist(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }
}