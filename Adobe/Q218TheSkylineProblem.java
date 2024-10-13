package Adobe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city 
when viewed from a distance. Given the locations and heights of all the buildings, return the 
skyline formed by these buildings collectively.
The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
    lefti is the x coordinate of the left edge of the ith building.
    righti is the x coordinate of the right edge of the ith building.
    heighti is the height of the ith building.

You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at 
height 0. The skyline should be represented as a list of "key points" sorted by their x-coordinate 
in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment 
in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to 
mark the skyline's termination where the rightmost building ends. Any ground between the leftmost 
and rightmost buildings should be part of the skyline's contour.

Note: There must be no consecutive horizontal lines of equal height in the output skyline. For 
instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 
5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]

Example 1:
Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
Explanation:
Figure A shows the buildings of the input.
Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.

Example 2:
Input: buildings = [[0,2,3],[2,5,3]]
Output: [[0,3],[5,0]]
*/

public class Q218TheSkylineProblem {

    public List<List<Integer>> getSkyline(int[][] buildings) {
        LinkedList<int[]> res = merge(buildings, 0, buildings.length - 1);
        List<List<Integer>> finalRes = new ArrayList<>();
        for (int[] point : res) {
            List<Integer> pointList = new ArrayList<>();
            pointList.add(point[0]);
            pointList.add(point[1]);
            finalRes.add(pointList);
        }
        return finalRes;
    }

    private LinkedList<int[]> merge(int[][] buildings, int lo, int hi) {
        LinkedList<int[]> res = new LinkedList<>();
        if (lo > hi) {
            return res;
        } else if (lo == hi) {
            res.add(new int[] { buildings[lo][0], buildings[lo][2] });
            res.add(new int[] { buildings[lo][1], 0 });
            return res;
        }
        int mid = lo + (hi - lo) / 2;
        LinkedList<int[]> left = merge(buildings, lo, mid);
        LinkedList<int[]> right = merge(buildings, mid + 1, hi);
        int leftH = 0, rightH = 0;
        while (!left.isEmpty() && !right.isEmpty()) {
            int x1 = left.peekFirst()[0];
            int x2 = right.peekFirst()[0];
            int x = 0;
            if (x1 < x2) {
                int[] temp = left.pollFirst();
                x = temp[0];
                leftH = temp[1];
            } else if (x1 > x2) {
                int[] temp = right.pollFirst();
                x = temp[0];
                rightH = temp[1];
            } else {
                x = left.peekFirst()[0];
                leftH = left.pollFirst()[1];
                rightH = right.pollFirst()[1];
            }
            int h = Math.max(leftH, rightH);
            if (res.isEmpty() || h != res.peekLast()[1]) {
                res.add(new int[] { x, h });
            }
        }
        res.addAll(left);
        res.addAll(right);
        return res;
    }
}