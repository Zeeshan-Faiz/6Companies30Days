package GoldmanSachs;

import java.util.ArrayDeque;
import java.util.Queue;

/*
You are given an integer matrix isWater of size m x n that represents a map of land and water cells.

    If isWater[i][j] == 0, cell (i, j) is a land cell.
    If isWater[i][j] == 1, cell (i, j) is a water cell.

You must assign each cell a height in a way that follows these rules:
    The height of each cell must be non-negative.
    If the cell is a water cell, its height must be 0.
    Any two adjacent cells must have an absolute height difference of at most 1. A cell is adjacent 
    to another cell if the former is directly north, east, south, or west of the latter (i.e., their 
    sides are touching).

Find an assignment of heights such that the maximum height in the matrix is maximized.
Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height. If there 
are multiple solutions, return any of them.

Example 1:
Input: isWater = [[0,1],[0,0]]
Output: [[1,0],[2,1]]
Explanation: The image shows the assigned heights of each cell.
The blue cell is the water cell, and the green cells are the land cells.

Example 2:
Input: isWater = [[0,0,1],[1,0,0],[0,0,0]]
Output: [[1,1,0],[0,1,1],[1,2,2]]
Explanation: A height of 2 is the maximum possible height of any assignment.
Any height assignment that has a maximum height of 2 while still meeting the rules will also be accepted.
*/

public class Q1765MapOfHighestPeak {

    public int[][] highestPeak(int[][] isWater) {
        
        int m = isWater.length;
        int n = isWater[0].length;
        int[][] grid = new int[m][n];

        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    grid[i][j] = 0;
                    q.add(new int[] { i, j });
                } else {
                    grid[i][j] = -1;
                }
            }
        }

        int[] DIR = new int[] { 0, 1, 0, -1, 0 };
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0];
            int c = cell[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + DIR[i];
                int nc = c + DIR[i + 1];
                if (nr < 0 || nr == m || nc < 0 || nc == n || grid[nr][nc] != -1)
                    continue;
                grid[nr][nc] = grid[r][c] + 1;
                q.offer(new int[] { nr, nc });
            }
        }
        return grid;
    }
}